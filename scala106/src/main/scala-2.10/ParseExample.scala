//   {"timestamp":1461057523449,"seqnum":194960,"network_id":1484,"operator_id":5,"enterprise_id":2125,"group_site_id":80,"group_id":21,"uarfcn_dl_in_use":1032,"uarfcn_ul_in_use":807,"admin_state":"true","op_state":"true","psc_in_use":6,"num_blacklisted_lai_entries":0,"rf_tx_status":"true","led_status":5,"timing_ref_source":1,"predicted_resolution":2,"num_current_alarms":0,"uptime":5938104,"cpu":8,"rss":0,"ul_interference":-93,"current_temp":37,"num_cs_paging":0,"num_ps_paging":0,"voice_erlangs":0,"num_son_neighbours":2,"num_peer_entries":6,"peer_neighbour_entries":0,"iurh_link_entries":5,"num_tx_bytes":2799641317,"num_rx_bytes":5657172958,"rf_status":0,"ap_tx_pwr_inuse":-100,"ul_tx_pwr_inuse":-21,"active_cs_calls":0,"@timestamp":"2016-04-19T09:18:43.846Z","headers":{"content_type":"application/json","request_method":"PUT","request_path":"/fap_status","request_uri":"/fap_status","http_version":"HTTP/1.1","http_host":"blade17-vm1.zxsdev.ubiquisys.local:4043","http_accept":"*/*","http_keep_alive":"6000","content_length":"738","http_x_ssl_client_cn":"001B67-352639057116427","http_x_forwarded_for":"10.209.99.78"}}

import com.mongodb.util.{JSONParseException, JSON}
import com.mongodb.casbah.Imports._


import kafka.serializer.StringDecoder
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{Logging, SparkConf}
import org.apache.spark.rdd.RDD
import org.apache.log4j._


object KafkaWordCount {

  val log = Logger.getRootLogger

  def jsonStrToMongoObject(jsonStr: String): DBObject = {
    try{
      return JSON.parse(jsonStr).asInstanceOf[DBObject]
    }catch {
      case e : JSONParseException =>{
        log.info("Invalid JSON"+ e)
        log.warn("Invalid JSON returning null")
      }

      return null
    }
  }

  def extractNode(key: String, value:DBObject): DBObject = {
    return value.get(key).asInstanceOf[DBObject]
  }

  def mongo_request_management ( db:MongoDB, coll:String ) {
    db("management").update(DBObject("collection"-> coll), $setOnInsert("collection"-> coll, "state" -> 0), upsert = true)
  }

  def processMessage(json:String): Unit ={
    val mongoClient = MongoClient("blade18-vm2.zxsdev.ubiquisys.local", 27017)
    val db = mongoClient("rrftestdb")


    val result = jsonStrToMongoObject(json)
    if (result == null){
      log.warn("RWL: Invallid Json Ignoring");
      return
    }
    val headers = extractNode("headers", result)
    var fapid:AnyRef = null

    if (headers.exists(_._1 == "http_x_ssl_client_cn")){
      fapid = headers.get("http_x_ssl_client_cn")
    } else{
      println("RWL: Ignored message from unauthenticated unit");
      return
    }

    val req_uri       = headers.get("request_uri")
    val operator_id   = result.get("operator_id")
    val network_id    = result.get("network_id")
    val enterprise_id = result.get("enterprise_id")
    val group_site_id = result.get("group_site_id")
    val group_id      = result.get("group_id")
    val timestamp     = result.get("timestamp")
    val seqnum        = result.get("seqnum")

    val grid_data = result.filter(_._1 != "headers").filter(_._1 != "@version")
    grid_data.put("fap_id", fapid)
    val fap_search = DBObject(  "fap_id" -> fapid,
      "timestamp" -> timestamp,
      "seqnum" -> seqnum
    )
    if (req_uri == "/info") {
      //Registration event from the FAP when it boots//Dictionary for grid parameters( with network)
      val grid_db = DBObject(
        "operator_id "    -> operator_id,
        "network_id "     -> network_id,
        "enterprise_id "  -> enterprise_id,
        "group_site_id "  -> group_site_id,
        "group_id "       -> group_id
      )


      //Simple reference data is just FAP identity and GRID parameters
      val ref_data = fap_search
      ref_data.putAll(grid_db)
      val operator_collection = f"operator_$operator_id%s_fap_info"
      db(operator_collection).update(fap_search, ref_data, true)

      val network_collection = f"network_$network_id%s_fap_info"
      db(network_collection).update(fap_search, ref_data, true)//Store full data in the grid

      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_fap_info"
      db(grid_collection).update(fap_search, grid_data, true)//Register the collections with the management systems

      mongo_request_management(db, operator_collection)
      mongo_request_management(db, network_collection)
      mongo_request_management(db, grid_collection)//Register the grid details for fast lookup from operator

      val operator_grids_collection = f"operator_$operator_id%s_grids"
      db(operator_grids_collection).update(grid_db, grid_db, true)
      mongo_request_management(db, operator_grids_collection)//Register the grid details for fast lookup from network

      val network_grids_collection = f"network_$network_id%s_grids"
      db(network_grids_collection).update(grid_db, grid_db, true)
      mongo_request_management(db, network_grids_collection)//Register the FAP for quick lists of all FAPs in a grid

      val grid_list_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_faps"
      db(grid_list_collection).update(DBObject("fap_id" -> fapid),DBObject("fap_id " -> fapid), true)
      mongo_request_management(db, grid_list_collection)//Track the FAP in the global collection for fast lookup of FAP to GRID (or network or operator)

      db("global_fap_info").update(fap_search, ref_data, true)//Find most recent site definition(if any)

      val grid_site_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_meta"
      val site_cursor = db(grid_site_collection).find().sort(new BasicDBObject("timestamp",-1)).limit(1)

      for (site_doc <- site_cursor){
        val siteID = site_doc.get("site_id")
        val site_collection = f"site_$siteID%s_fap_info"
        db(site_collection).update( fap_search, ref_data, true)
        mongo_request_management( db, site_collection )
      }

    } else if (req_uri == "/fap_status"){
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_fap_status"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )
    } else if (req_uri == "/rf_info") {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_ue_statistics"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else if ( req_uri == "/fap_events") {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s__fap_events"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else if ( req_uri == "/link_table") {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_link_table"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else if ( req_uri == "/son_settings") {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_son_settings"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else if ( req_uri == "/son_neighbours") {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_son_neighbours"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else if ( (req_uri == "/son_peers") || (req_uri == "/son/peers")) {
      val grid_collection = f"grid_$operator_id%s_$enterprise_id%s_$group_site_id%s_$group_id%s_son_peers"
      db(grid_collection).update(fap_search,grid_data,true)
      mongo_request_management( db, grid_collection )

    } else{
      println("RWL: Ignored message on unrecognised path: " + req_uri + " from " + fapid)
    }
  }

  def handleIncomingRDD(rdd:RDD[(String,String)]){
    // Get list of events aka all values from the key values hash _._2
    val rm = rdd.map(_._2)
    rm.foreach(processMessage)
  }

  def main(args: Array[String]) {
    val console = new ConsoleAppender(); //create appender
    //configure the appender
    val PATTERN = "%d [%p|%c|%C{1}] %m%n";
    console.setLayout(new PatternLayout(PATTERN));
    console.setThreshold(Level.WARN);
    console.activateOptions();

    //add appender to any Logger (here is root)
    //val log = LogManager.getRootLogger

    log.addAppender(console);

    log.info("THINGY IS SAAASAS")
    //Logger.getRootLogger.setLevel(Level.WARN)

    if (args.length < 4) {
      System.err.println("Usage: KafkaWordCount <zkQuorum> <group> <topics> <numThreads>")
      System.exit(1)
    }

    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf()
      .setAppName("KafkaWordCount")
      .set("spark.rpc.netty.dispatcher.numThreads","2")

    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val messages = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)
    //val id = messages.map(_._2).map(processMessage)
    messages.foreachRDD{rdd => handleIncomingRDD(rdd)}
    ssc.start()
    ssc.awaitTermination()
    ssc.stop()

  }
}
