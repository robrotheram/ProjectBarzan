package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerTPEvnt extends PlayerEvnt {

    protected PlayerTeleportEvent.TeleportCause cause;

    public PlayerTPEvnt(Player player, Location location, EventType eventType, PlayerTeleportEvent.TeleportCause cause) {
        super(player,location,eventType);
        this.cause = cause;
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
        return obj;
    }
}
