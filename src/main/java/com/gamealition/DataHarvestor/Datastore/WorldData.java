package com.gamealition.DataHarvestor.Datastore;

import org.json.simple.JSONObject;

/**
 * Created by robert on 22/05/2016.
 */
public class WorldData {


    private boolean loaded;
    private Location location;
    private  String eventName;

    public WorldData(String eventName, Location location, boolean loaded) {
        this.eventName=eventName;
        this.location=location;
        this.loaded=loaded;
    }

    public WorldData(String eventName, Location location) {

        this.eventName=eventName;
        this.location=location;
    }


    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
                loaded+"\n"+
                location;


    }

    public JSONObject getJSON(){
        JSONObject obj = new JSONObject();
        obj.put("eventName", eventName);
        obj.put("isloaded", loaded);
        obj.put("playerLocation", location.getJSON());

        return obj;
    }
}
