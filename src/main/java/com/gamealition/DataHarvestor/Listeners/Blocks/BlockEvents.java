package com.gamealition.DataHarvestor.Listeners.Blocks;

import com.gamealition.DataHarvestor.Datastore.BlockData;
import com.gamealition.DataHarvestor.Datastore.DataStore;
import com.gamealition.DataHarvestor.Datastore.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

/**
 * Created by robert on 15/05/2016.
 */
public class BlockEvents implements Listener{

    DataStore dataStore;

    public BlockEvents(DataStore dataStore) {
        this.dataStore = dataStore;
    }


    public void storeBlockEvent(BlockEvent event, String UUID) {

           dataStore.addBlock(new BlockData(
                event.getEventName(),
                UUID,
                new Location(event.getBlock().getLocation().getWorld().getName(), event.getBlock().getLocation().getBlockX(), event.getBlock().getLocation().getBlockY(), event.getBlock().getLocation().getBlockZ()),
                true,
                event.getBlock().getType().name()
            ));
   };
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockBurnEvent(BlockBurnEvent events){
        storeBlockEvent(events, null );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockCanBuildEvent(BlockCanBuildEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockDamageEvent(BlockDamageEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockExpEvent(BlockExpEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockExplodeEvent(BlockExplodeEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockFadeEvent(BlockFadeEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockFromToEvent(BlockFromToEvent events){
        if(events.getBlock().getType()==Material.AIR){return;}
        storeBlockEvent(events , null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockGrowEvent(BlockGrowEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockIgniteEvent(BlockIgniteEvent events){
        events.getCause().name();
        if(events.getPlayer() !=null) {
            storeBlockEvent(events, events.getPlayer().getUniqueId().toString());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        storeBlockEvent(event, event.getPlayer().getUniqueId().toString());
        return;
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        storeBlockEvent(event, event.getPlayer().getUniqueId().toString());
    }




    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockRedstoneEvent(BlockRedstoneEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void BrewEvent(BrewEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void FurnaceBurnEvent(FurnaceBurnEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void FurnaceSmeltEvent(FurnaceSmeltEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void LeavesDecayEvent(LeavesDecayEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void NotePlayEvent(NotePlayEvent events){
        storeBlockEvent(events, null);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void SignChangeEvent(SignChangeEvent events){
        storeBlockEvent(events, null);
    }






}
