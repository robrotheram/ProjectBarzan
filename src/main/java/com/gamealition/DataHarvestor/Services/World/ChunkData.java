package com.gamealition.DataHarvestor.Services.World;

import com.gamealition.DataHarvestor.DataHarvestor;
import com.gamealition.DataHarvestor.Utils.BlockUtils;
import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.Chunk;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by robert on 08/01/2017.
 */

public class ChunkData {
    private Location location;
    private int numberOFEnities;
    private DataHarvestor instance;
    private ArrayList<String> block_ids = new ArrayList<>();

    public ChunkData(Chunk c, DataHarvestor instance) {
        this.instance = instance;
        parseChunk(c);
    }

    private void parseChunk(Chunk c){
        location = new Location(c.getWorld().getName(),c.getX(),0,c.getZ());
        numberOFEnities = c.getEntities().length;
        int cx = c.getX() << 4;
        int cz = c.getZ() << 4;
        block_ids.clear();
        for (int x = cx; x < cx + 16; x++) {
            for (int z = cz; z < cz + 16; z++) {
                int in = (z -cz)+((x-cx)*16);
                int Y = c.getWorld().getHighestBlockAt(x,z).getY();
                String name = BlockUtils.getFullName(c.getWorld().getBlockAt(x,(Y-1),z));
                System.out.println("world:    "+c.getWorld().getName()+"X:"+x+"Y:"+Y+"z:"+z+"  NAME:  "+name);
                block_ids.add(in,name);
            }
        }
    }
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("location",location);
        obj.put("NumberOFEnities",numberOFEnities);
        if (!instance.dataStore.loadedChunks.containsKey(block_ids.hashCode())) {
            instance.dataStore.loadedChunks.values().remove(location);
            instance.dataStore.loadedChunks.put(block_ids.hashCode(), location);
            obj.put("map", block_ids);
        }
        return obj;
    }
}
