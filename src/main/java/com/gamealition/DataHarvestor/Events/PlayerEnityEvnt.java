package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerEnityEvnt extends PlayerEvnt {

    protected Entity entity;

    public PlayerEnityEvnt(Player player, Location location, EventType eventType, Entity entity) {
        super(player,location,eventType);
        this.entity = entity;
    }

    @Override
    public String toString(){
        return location.toString()+"\n"+
                time+"\n"+
                eventType.name()+"\n"+
                player.getDisplayName()+"\n"+
                player.getUniqueId().toString()+"\n";

    }
    @Override
    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("Evnt", getEventType().name());
        obj.put("Location", location.getJSON());
        obj.put("Time", getTime());
        obj.put("Player", playerToJson());
        obj.put("Meta", getMeta());
        return obj;
    }

    private JSONObject enityToJSON() {
        JSONObject obj = new JSONObject();
        obj.put("Name", entity.getName());
        obj.put("lived", entity.getTicksLived());
        return obj;
    }
    private JSONObject getMeta() {
        JSONObject obj = new JSONObject();
        obj.put("Entity", enityToJSON());
        return obj;
    }
}
