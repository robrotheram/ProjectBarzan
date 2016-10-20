package com.gamealition.DataHarvestor.Datastore;

import org.json.simple.JSONObject;

/**
 * Created by robert on 14/05/2016.
 */
public class EnityData {


    private Location location;
    private String enityName;
    private String blockName;
    private String eventName;

    public EnityData(String eventName, String blockName, String enityName, Location location) {
        this.eventName = eventName;
        this.blockName = blockName;
        this.enityName = enityName;
        this.location = location;
    }

    public EnityData(String eventName, String enityName, Location location) {
        this.eventName = eventName;
        this.enityName = enityName;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEnityName() {
        return enityName;
    }

    public void setEnityName(String enityName) {
        this.enityName = enityName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String name) {
        this.blockName = name;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String toString(){
        return location.toString()+"\n"+
                eventName+"\n"+
                blockName+"\n"+
                enityName+"\n"+
                location;

    }

    public JSONObject getJSON(){
        JSONObject obj = new JSONObject();
        obj.put("EntityName", enityName);
        obj.put("EventType", eventName);
        obj.put("blockName", blockName);
        obj.put("location", location.getJSON());
        return  obj;
    }
}