package com.gamealition.DataHarvestor.Services.World;

import com.gamealition.DataHarvestor.DataHarvestor;
import com.gamealition.DataHarvestor.HttpSend;
import com.gamealition.DataHarvestor.SystemInfo;
import com.gamealition.DataHarvestor.TPSUtil;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class WorldChunksService implements Runnable {
    private DataHarvestor instance;
    private HttpSend httpsend;

    public WorldChunksService(DataHarvestor instance){
        this.instance = instance;
        httpsend = new HttpSend(instance);
    }

    @Override
    public void run() {
        JSONArray obj = new JSONArray();

        for (World world: instance.getServer().getWorlds()) {
            JSONObject wObj = new JSONObject();
            JSONArray chuckList = new JSONArray();
            for (Chunk c : world.getLoadedChunks()) {
                ChunkData cd = new ChunkData(c, instance);
                chuckList.add(cd.toJSON());
            }
            world.getEntities().size();
            wObj.put("worldName", world.getName());
            wObj.put("loadedChunks", chuckList);
            wObj.put("NumberEnities",world.getEntities().size());
            obj.add(wObj);
        }
        JSONObject serverMeta = new JSONObject();
        SystemInfo sysInfo = new SystemInfo();

        serverMeta.put("TotalMemory",sysInfo.totalMem());
        serverMeta.put("UsedMemory",sysInfo.usedMem());
        try {
            serverMeta.put("CPULoad",sysInfo.getProcessCpuLoad());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jobj = new JSONObject();
        jobj.put("Worlds",obj);
        TPSUtil tpsUtil = new TPSUtil(instance, instance.dataStore);
        jobj.put("TPS",tpsUtil.getTPS());
        jobj.put("ServerMeta",serverMeta);

        httpsend.sendHTTPData("/server",jobj);
    }
}
