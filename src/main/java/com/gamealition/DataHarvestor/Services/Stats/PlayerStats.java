package com.gamealition.DataHarvestor.Services.Stats;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerStats {

    private Player player;
    public PlayerStats(Player player){
        this.player = player;
    }


    public JSONObject toJSON(){
        JSONObject obj = new  JSONObject();
        obj.put("DEATHS",player.getStatistic(Statistic.DEATHS));
        obj.put("WALK",player.getStatistic(Statistic.WALK_ONE_CM));
        obj.put("FLY",player.getStatistic(Statistic.FLY_ONE_CM));
        obj.put("FALL",player.getStatistic(Statistic.FALL_ONE_CM));
        obj.put("DAMGE_DELT",player.getStatistic(Statistic.DAMAGE_DEALT));
        obj.put("DAMGE_TAKEN",player.getStatistic(Statistic.DAMAGE_TAKEN));
        return obj;
    }

}
