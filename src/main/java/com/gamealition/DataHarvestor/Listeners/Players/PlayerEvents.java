package com.gamealition.DataHarvestor.Listeners.Players;

import com.gamealition.DataHarvestor.DataStore;
import com.gamealition.DataHarvestor.Events.*;
import com.gamealition.DataHarvestor.Events.PlayerChatEvnt;
import com.gamealition.DataHarvestor.Events.PlayerEvnt;
import com.gamealition.DataHarvestor.Utils.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
public class PlayerEvents implements Listener {

    private DataStore dataStore;
    public PlayerEvents(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerUnleashEntityEvent(PlayerUnleashEntityEvent event) {
        PlayerEnityEvnt pv = new PlayerEnityEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_SPAWNED_ENTITY,
                event.getEntity()
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        PlayerChatEvnt pv = new PlayerChatEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_CHAT,
                event.getMessage()
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_JOIN
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_SNEAK
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBedEnterEvent(PlayerBedEnterEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_ENTER_BED
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBedLeaveEvent(PlayerBedLeaveEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_LEAVE_BED
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_CHANGE_WORLD
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerDropItemEvent(PlayerDropItemEvent event) {
        PlayerItemEvnt pv = new PlayerItemEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_DROP_ITEM,
                event.getItemDrop().getItemStack()
        );
        dataStore.pushEvent( pv );
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_MOVE
        );
        //dataStore.pushEvent( pv ); Disabled player moveevent its to spammy
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerPickupItemEvent(PlayerPickupItemEvent event) {
        PlayerItemEvnt pv = new PlayerItemEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_PICKUP_ITEM,
                event.getItem().getItemStack()
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent nEvent = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
            PlayerDeathEvnt pv = new PlayerDeathEvnt(
                    event.getEntity(),
                    new Location(event.getEntity().getLocation()),
                    EventType.PLAYER_PICKUP_ITEM,
                    nEvent.getCause(),
                    nEvent.getDamager()
            );
            dataStore.pushEvent( pv );
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerPortalEvent(PlayerPortalEvent event) {
        PlayerTPEvnt pv = new PlayerTPEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_PORTAL,
                event.getCause()
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_LEAVE
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        PlayerEvnt pv = new PlayerEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_RESPAWN
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerTeleportEvent(PlayerTeleportEvent event) {
        PlayerTPEvnt pv = new PlayerTPEvnt(
            event.getPlayer(),
            new Location(event.getPlayer().getLocation()),
            EventType.PLAYER_TELEPORT,
            event.getCause()
        );
        dataStore.pushEvent( pv );
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        PlayerBlockEvnt pv = new PlayerBlockEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_BLOCK_PLACE,
                event.getBlock()
        );
        dataStore.pushEvent( pv );
    }
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerBlockEvnt pv = new PlayerBlockEvnt(
                event.getPlayer(),
                new Location(event.getPlayer().getLocation()),
                EventType.PLAYER_BLOCK_BREAK,
                event.getBlock()
        );
        dataStore.pushEvent( pv );
    }
}
