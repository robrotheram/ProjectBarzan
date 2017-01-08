package com.gamealition.DataHarvestor;

import com.gamealition.DataHarvestor.Listeners.Players.PlayerEvents;

import com.gamealition.DataHarvestor.Services.Events.EventService;
import com.gamealition.DataHarvestor.Services.Stats.PlayerStatService;
import com.gamealition.DataHarvestor.Services.World.WorldChunksService;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.util.logging.Level;


/**
 * Created by robert on 10/05/2016.
 */
public class DataHarvestor extends JavaPlugin{
    private static DataHarvestor instance;
    public static DataStore dataStore = new DataStore();
    private double tps;
    private HttpSend httpsend;
    private FileWriter writer;
    private ConfigLoader cl;


    @Override
    public void onEnable() {
        instance = this;
        httpsend = new HttpSend(instance);
        start();
        getLogger().log(Level.WARNING,"Hello All this is a test plugin");
        final TPSUtil tpsUtil = new TPSUtil(instance,dataStore);
        tpsUtil.run();

        getServer().getPluginManager().registerEvents(new PlayerEvents(dataStore),this);

        cl = new ConfigLoader();
        cl.reloadConfig();
        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, new EventService(this), 50L, 20L);
        scheduler.scheduleSyncRepeatingTask(this, new WorldChunksService(this), 50L, 1200L);
        scheduler.scheduleSyncRepeatingTask(this, new PlayerStatService(this), 50L, 1200L);

    }

    @Override
    public void onDisable() {
        stop();
        Bukkit.getScheduler().cancelTasks(this);
        cl.saveConfig();
        instance = null;
    }

    public void stop(){
        JSONObject jobj = new JSONObject();
        jobj.put("EventType","ServerEvent");
        jobj.put("Message","Monitoring plugin going offline");
        httpsend.sendHTTPData("/server",jobj);
    }
    public void start(){
        JSONObject jobj = new JSONObject();
        jobj.put("EventType","ServerEvent");
        jobj.put("Message","Monitoring plugin enabled");
        httpsend.sendHTTPData("/server",jobj);
    }

    public static DataHarvestor getInstance() {
        return instance;
    }

    public ConfigLoader getConfigData() {
        return cl;
    }
}
