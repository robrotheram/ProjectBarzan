package com.gamealition.DataHarvestor.Datastore;

import org.json.simple.JSONObject;

/**
 * Created by robert on 14/05/2016.
 */
public class BlockData {
    private String cuase = "PLAYER";
    private String event = "";
    private String UUID  = "";
    private Location location = new Location();
    private boolean breakNaturally;
    private String blockName;
    private int BlockState;


    public BlockData(String event, String UUID, Location location, boolean breakNaturally, String blockName) {
        this.event = event;
        this.UUID = UUID;
        this.location = location;
        this.breakNaturally = breakNaturally;
        this.blockName = blockName;
    }

    public String getCuase() {
        return cuase;
    }

    public void setCuase(String cuase) {
        this.cuase = cuase;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isBreakNaturally() {
        return breakNaturally;
    }

    public void setBreakNaturally(boolean breakNaturally) {
        this.breakNaturally = breakNaturally;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        blockName = blockName;
    }

    public int getBlockState() {
        return BlockState;
    }

    public void setBlockState(int blockState) {
        BlockState = blockState;
    }

    public BlockData() {}

    public String toString(){
        return location.toString()+"\n"+
                event+"\n"+
                UUID+"\n"+
                cuase+"\n"+
                blockName+"\n"+
                breakNaturally+"\n";

    }

    public JSONObject getJSON(){
        JSONObject obj = new JSONObject();
        obj.put("playerUUID", UUID);
        obj.put("event", event);
        obj.put("cuase", cuase);
        obj.put("blockName", blockName);
        obj.put("breakNaturally", breakNaturally);
        obj.put("playerLocation", location.getJSON());
        return  obj;
    }
}