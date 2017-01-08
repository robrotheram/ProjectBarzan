package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerDeathEvnt extends PlayerEvnt {

    protected EntityDamageEvent.DamageCause cause;
    private Entity entity;
    public PlayerDeathEvnt(Player player, Location location, EventType eventType, EntityDamageEvent.DamageCause cause, Entity entity) {
        super(player,location,eventType);
        this.cause = cause;
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
    
    private JSONObject getMeta() {
        JSONObject obj = new JSONObject();
        obj.put("cause", cause.name());
        if(entity !=null){
            System.out.println(entity.getClass());
            if ((entity.getType().name().equals("ARROW") )) {
                Arrow arrow = (Arrow) entity;
                obj.put("entity", arrow.getType());
            } else {
                obj.put("entity", entity.getType());
            }
        }
        return obj;
    }
}
