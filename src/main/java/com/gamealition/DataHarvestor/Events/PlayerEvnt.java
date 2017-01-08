package com.gamealition.DataHarvestor.Events;

import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerEvnt extends Evnt {

    protected Player player;
    protected String chatmessage;

    public PlayerEvnt(Player player, Location location, EventType eventType) {
        super(location, eventType);
        this.player = player;
    }
    protected JSONObject playerToJson(){
        JSONObject obj = new JSONObject();
        obj.put("Name", player.getDisplayName());
        obj.put("UUID", player.getUniqueId().toString());
        return obj;
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

        return obj;
    }
}
