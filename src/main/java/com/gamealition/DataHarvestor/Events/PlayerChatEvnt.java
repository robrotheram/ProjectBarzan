package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerChatEvnt extends PlayerEvnt {

    protected String message;

    public PlayerChatEvnt(Player player, Location location, EventType eventType, String message) {
        super(player,location,eventType);
        this.message = message;
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
        obj.put("Data", getData());
        return obj;
    }

    private JSONObject getData() {
        JSONObject obj = new JSONObject();
        obj.put("Message", message);
        return obj;
    }
}
