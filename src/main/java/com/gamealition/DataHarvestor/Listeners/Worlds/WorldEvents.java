package com.gamealition.DataHarvestor.Listeners.Worlds;

import com.gamealition.DataHarvestor.Datastore.DataStore;
import com.gamealition.DataHarvestor.Datastore.WorldData;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.*;

/**
 * Created by robert on 14/05/2016.
 */
public class WorldEvents implements Listener {
    private DataStore dataStore;
    public WorldEvents(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void processEnvent(Event event, String worldName, int x, int z, boolean loaded){

        dataStore.addWorld(new WorldData(
                event.getEventName(),
                new com.gamealition.DataHarvestor.Datastore.Location(worldName,x,0,z),
                loaded
        ));
    }

    private void processEnvent(Event event, String name, Location location) {
        dataStore.addWorld(new WorldData(
                event.getEventName(),
                new com.gamealition.DataHarvestor.Datastore.Location(location.getWorld().getName(),location.getBlockX(),location.getBlockY(),location.getBlockZ())
        ));
    }


/*
    @EventHandler(priority = EventPriority.MONITOR)
    public void ChunkLoadEvent (ChunkLoadEvent event){ processEnvent(event, event.getWorld().getName(), event.getChunk().getX(), event.getChunk().getZ(), event.getChunk().isLoaded()); }


    @EventHandler(priority = EventPriority.MONITOR)
    public void ChunkPopulateEvent (ChunkPopulateEvent event){ processEnvent(event, event.getWorld().getName(), event.getChunk().getX(), event.getChunk().getZ(), event.getChunk().isLoaded()); }



    @EventHandler(priority = EventPriority.MONITOR)
    public void ChunkUnloadEvent (ChunkUnloadEvent event){ processEnvent(event, event.getWorld().getName(), event.getChunk().getX(), event.getChunk().getZ(), event.getChunk().isLoaded()); }
*/

    @EventHandler(priority = EventPriority.MONITOR)
    public void PortalCreateEvent (PortalCreateEvent event){ processEnvent(event, event.getWorld().getName(), event.getBlocks().get(0).getLocation()); }


    @EventHandler(priority = EventPriority.MONITOR)
    public void SpawnChangeEvent (SpawnChangeEvent event){ processEnvent(event, event.getWorld().getName(), event.getPreviousLocation()); }


    @EventHandler(priority = EventPriority.MONITOR)
    public void StructureGrowEvent (StructureGrowEvent event){ processEnvent(event, event.getWorld().getName(), event.getLocation()); }


}
