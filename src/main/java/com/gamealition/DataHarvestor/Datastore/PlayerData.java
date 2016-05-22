package com.gamealition.DataHarvestor.Datastore;

import org.json.simple.JSONObject;

/**
 * Created by robert on 14/05/2016.
 */
public class PlayerData {
    private String cuase = "PLAYER";
    private String event = "";
    private String UUID  = "";
    private String name  = "";
    private String gameMode  =  "";
    private boolean isflight = false;
    private boolean isvecial = false;
    private Location location = new Location();
    private String  vecial = "NA";
    private double  health = 0;
    private double  level = 0;

    public PlayerData(String UUID, String name, String gameMode, boolean isflight, boolean isvecial, String vecial, double health, double level, Location location,String event) {
        this.UUID = UUID;
        this.name = name;
        this.gameMode = gameMode;
        this.isflight = isflight;
        this.isvecial = isvecial;
        this.vecial = vecial;
        this.health = health;
        this.level = level;
        this.location = location;
        this.event = event;
    }

    public PlayerData() {

    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public String getVecial() {
        return vecial;
    }

    public void setVecial(String vecial) {
        this.vecial = vecial;
    }

    public boolean isvecial() {
        return isvecial;
    }

    public void setIsvecial(boolean isvecial) {
        this.isvecial = isvecial;
    }

    public boolean isflight() {
        return isflight;
    }

    public void setIsflight(boolean isflight) {
        this.isflight = isflight;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString(){
        return location.toString()+"\n"+
                event+"\n"+
                UUID+"\n"+
                name+"\n"+
                gameMode+"\n"+
                isflight+"\n"+
                isvecial+"\n"+
                vecial+"\n"+
                health+"\n"+
                level+"\n";

    }

    public JSONObject getJSON(){
        JSONObject obj = new JSONObject();
        obj.put("playerName", name);
        obj.put("playerUUID", UUID);
        obj.put("playerGameMode", gameMode);
        obj.put("playerIsFlight", isflight);
        obj.put("playerIsVehicle", isvecial);
        obj.put("playerVehicle", vecial);
        obj.put("playerHealth", health);
        obj.put("playerLevel", level);
        obj.put("playerEvent", event);
        obj.put("playerLocation", location.getJSON());

        return obj;
    }
}


//if (isvecial){ vecial = event.getPlayer().getVehicle().getType().name();}