package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.json.simple.JSONObject;

import java.util.Objects;

/**
 * Created by robert on 03/01/2017.
 */
public class Evnt {

    protected long time;
    protected Location location;
    protected EventType eventType;

    public Evnt(Location location, EventType eventType) {
        this.time = System.currentTimeMillis()/1000;
        this.location = location;
        this.eventType = eventType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }


    public String toString(){
        return location.toString()+"\n"+
                Objects.toString(getTime(), null)+"\n"+
                eventType.name()+"\n";
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("Evnt", getEventType().name());
        obj.put("Location", location.getJSON());
        obj.put("Time", Objects.toString(getTime(), null));
        return obj;
    }
}
