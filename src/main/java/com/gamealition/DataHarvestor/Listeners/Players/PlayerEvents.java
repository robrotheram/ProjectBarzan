package com.gamealition.DataHarvestor.Listeners.Players;

import com.gamealition.DataHarvestor.Datastore.DataStore;
import com.gamealition.DataHarvestor.Datastore.Location;
import com.gamealition.DataHarvestor.Datastore.PlayerData;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * Created by robert on 11/05/2016.
 */
public class PlayerEvents implements Listener {
    private DataStore dataStore;
    public PlayerEvents(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void storePlayerEvent(PlayerEvent event){
        PlayerData pd = new PlayerData();
        pd.setUUID(event.getPlayer().getUniqueId().toString());
        pd.setName(event.getPlayer().getDisplayName());
        pd.setGameMode(event.getPlayer().getGameMode().toString());
        pd.setIsflight(event.getPlayer().isFlying());
        pd.setIsvecial(event.getPlayer().isInsideVehicle());
        if (pd.isvecial()){ pd.setVecial(event.getPlayer().getVehicle().getType().name());}
        pd.setHealth(event.getPlayer().getHealth());
        pd.setLevel(event.getPlayer().getLevel());
        pd.setEvent(event.getEventName());

        pd.setLocation(new Location(
                event.getPlayer().getLocation().getWorld().getName(),
                event.getPlayer().getLocation().getBlockX(),
                event.getPlayer().getLocation().getBlockY(),
                event.getPlayer().getLocation().getBlockZ()
        ));
        dataStore.addPlayer(pd);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerUnleashEntityEvent(PlayerUnleashEntityEvent event) {
        PlayerData pd = new PlayerData();
        pd.setUUID(event.getPlayer().getUniqueId().toString());
        pd.setName(event.getPlayer().getDisplayName());
        pd.setGameMode(event.getPlayer().getGameMode().toString());
        pd.setIsflight(event.getPlayer().isFlying());
        pd.setIsvecial(event.getPlayer().isInsideVehicle());
        if (pd.isvecial()){ pd.setVecial(event.getPlayer().getVehicle().getType().name());}
        pd.setHealth(event.getPlayer().getHealth());
        pd.setLevel(event.getPlayer().getLevel());
        pd.setEvent(event.getEventName());

        pd.setLocation(new Location(
                event.getPlayer().getLocation().getWorld().toString(),
                event.getPlayer().getLocation().getBlockX(),
                event.getPlayer().getLocation().getBlockY(),
                event.getPlayer().getLocation().getBlockZ()
        ));

        dataStore.addPlayer(pd);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        PlayerData pd = new PlayerData();
        pd.setUUID(event.getUniqueId().toString());
        pd.setName(event.getName());
        pd.setEvent(event.getEventName());
        dataStore.addPlayer(pd);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBedEnterEvent(PlayerBedEnterEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBedLeaveEvent(PlayerBedLeaveEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBucketEvent(PlayerBucketEmptyEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerBucketFillEvent(PlayerBucketFillEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerChannelEvent(PlayerChannelEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerDropItemEvent(PlayerDropItemEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerEditBookEvent(PlayerEditBookEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerEggThrowEvent(PlayerEggThrowEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerExpChangeEvent(PlayerExpChangeEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerFishEvent(PlayerFishEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerItemBreakEvent(PlayerItemBreakEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerItemConsumeEvent(PlayerItemConsumeEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerItemHeldEvent(PlayerItemHeldEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerKickEvent(PlayerKickEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerLevelChangeEvent(PlayerLevelChangeEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerPickupItemEvent(PlayerPickupItemEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerPortalEvent(PlayerPortalEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerRegisterChannelEvent(PlayerRegisterChannelEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerRespawnEvent(PlayerRespawnEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerShearEntityEvent(PlayerShearEntityEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerTeleportEvent(PlayerTeleportEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerToggleFlightEvent(PlayerToggleFlightEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerToggleSprintEvent(PlayerToggleSprintEvent event) {
        storePlayerEvent(event);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event) {
        storePlayerEvent(event);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerVelocityEvent(PlayerVelocityEvent event) {
        storePlayerEvent(event);
    }


}
