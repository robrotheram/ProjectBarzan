package com.gamealition.DataHarvestor.Services.Stats;

import com.gamealition.DataHarvestor.DataHarvestor;
import com.gamealition.DataHarvestor.Events.EventType;
import com.gamealition.DataHarvestor.Utils.Location;
import com.gamealition.DataHarvestor.Events.PlayerStatsEvnt;
import com.gamealition.DataHarvestor.HttpSend;
import org.bukkit.entity.Player;

/**
 * Created by robert on 03/01/2017.
 */
public class PlayerStatService implements Runnable {
    private DataHarvestor instance;
    private HttpSend httpsend;

    public PlayerStatService(DataHarvestor instance){
        this.instance = instance;
        httpsend = new HttpSend(instance);
    }

    @Override
    public void run() {
        for(Player p : instance.getServer().getOnlinePlayers()){
            PlayerStatsEvnt pse = new PlayerStatsEvnt(p,new Location(p.getLocation()), EventType.PLAYER_STATS);
            httpsend.sendHTTPData("/server",pse.toJSON());
        }
    }
}
