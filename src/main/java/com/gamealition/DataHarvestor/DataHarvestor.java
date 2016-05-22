package com.gamealition.DataHarvestor;

import com.gamealition.DataHarvestor.Datastore.*;
import com.gamealition.DataHarvestor.Listeners.Blocks.BlockEvents;
import com.gamealition.DataHarvestor.Listeners.Enity.EnityListener;
import com.gamealition.DataHarvestor.Listeners.Players.PlayerEvents;
import com.gamealition.DataHarvestor.Listeners.Worlds.WorldEvents;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

/**
 * Created by robert on 10/05/2016.
 */
public class DataHarvestor extends JavaPlugin{
    private static DataHarvestor instance;
    private DataStore dataStore = new DataStore();
    private double tps;
    private HttpSend httpsend;
    private FileWriter writer;
    private ConfigLoader cl;

    @Override
    public void onEnable() {
        instance = this;
        httpsend = new HttpSend(instance);
        getLogger().log(Level.WARNING,"Hello All this is a test plugin");
        final TPSUtil tpsUtil = new TPSUtil(instance,dataStore);

        getServer().getPluginManager().registerEvents(new PlayerEvents(dataStore),this);
        getServer().getPluginManager().registerEvents(new BlockEvents(dataStore),this);
        getServer().getPluginManager().registerEvents(new EnityListener(dataStore),this);
        getServer().getPluginManager().registerEvents(new WorldEvents(dataStore),this);

        cl = new ConfigLoader();
        cl.reloadConfig()
       ;
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (EnityData object: dataStore.getEnityStore()) {
                    httpsend.sendHTTPData("/server",object.getJSON());
                }
                for (PlayerData object: dataStore.getPlayerstore()) {
                    httpsend.sendHTTPData("/server",object.getJSON());
                }
                for (BlockData object: dataStore.getBlockStore()) {
                    httpsend.sendHTTPData("/server",object.getJSON());
                }
                for (WorldData object: dataStore.getWorldStore()) {
                    httpsend.sendHTTPData("/server",object.getJSON());
                }

                dataStore.getPlayerstore().clear();
                dataStore.getBlockStore().clear();
                dataStore.getEnityStore().clear();
                dataStore.getWorldStore().clear();
            }
        }, 50L, 20L);

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                JSONArray obj = new JSONArray();
                for (World world: instance.getServer().getWorlds()) {
                    JSONObject wObj = new JSONObject();

                    JSONArray loc = new JSONArray();
                    JSONArray ent = new JSONArray();
                    for (Chunk c : world.getLoadedChunks()) {
                        loc.add(new Location(world.getName(),c.getX(),0,c.getZ()));
                    }
                    wObj.put("worldName", world.getName());
                    wObj.put("loadedChunks", loc);
                    obj.add(wObj);
                }
                JSONObject jobj = new JSONObject();
                jobj.put("data",obj);
                httpsend.sendHTTPData("/server",jobj);
            }
        }, 50L, 1200L); //update onece a min;


    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        cl.saveConfig();
        instance = null;
    }

    public static DataHarvestor getInstance() {
        return instance;
    }

    public ConfigLoader getConfigData() {
        return cl;
    }
}
