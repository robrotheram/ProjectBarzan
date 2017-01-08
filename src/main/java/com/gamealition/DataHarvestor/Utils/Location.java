package com.gamealition.DataHarvestor.Utils;

import org.json.simple.JSONObject;

/**
 * Created by robert on 14/05/2016.
 */
public class Location {
    private String world = "";
    private int x, y, z  =  0;

    public Location() {
    }

    public Location(String world, int x, int y, int z) {

        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(org.bukkit.Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
    }



    public String getWorld() {

        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }


    public String toString (){
        return getJSON().toJSONString();
    }

    public JSONObject getJSON(){
        JSONObject object = new JSONObject();
        object.put("world",world);
        object.put("x",x);
        object.put("y",y);
        object.put("z",z);
        return object;
    }

    public boolean compare(Location location) {
        return
                this.getWorld().equals(location.getWorld()) &&
                this.getX()==location.getX() &&
                this.getY()==location.getY() &&
                this.getZ()==location.getZ();
    }
}
