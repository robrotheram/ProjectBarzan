package com.gamealition.DataHarvestor.Listeners.Enity;

import com.gamealition.DataHarvestor.Datastore.DataStore;
import com.gamealition.DataHarvestor.Datastore.EnityData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

/**
 * Created by robert on 21/05/2016.
 */
public class EnityListener implements Listener {

    DataStore dataStore;

    public EnityListener(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    private void  processEvent(Event event, Block block, String enityName, org.bukkit.Location location){
        dataStore.addEnity(new EnityData(
                event.getEventName(),
                block.getType().name(),
                enityName,
                new com.gamealition.DataHarvestor.Datastore.Location(
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ()
                )));

    }

    private void processEvent(Event event, String enityName, org.bukkit.Location location, Material type) {
        dataStore.addEnity(new EnityData(
                event.getEventName(),
                type.name(),
                enityName,
                new com.gamealition.DataHarvestor.Datastore.Location(
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ()
                )));
    }

    private void processEvent(Event event, String enityName, org.bukkit.Location location) {
        dataStore.addEnity(new EnityData(
                event.getEventName(),
                enityName,
                new com.gamealition.DataHarvestor.Datastore.Location(
                        location.getWorld().getName(),
                        location.getBlockX(),
                        location.getBlockY(),
                        location.getBlockZ()
                )));
    }



    @EventHandler(priority = EventPriority.MONITOR)
    public void creatureSpawnEvent (CreatureSpawnEvent event){

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void creeperPowerEvent (CreeperPowerEvent event){

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityBreakDoorEvent (EntityBreakDoorEvent event){ processEvent(event, event.getBlock(), event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityChangeBlockEvent (EntityChangeBlockEvent event){ processEvent(event, event.getBlock(), event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityCombustByEntityEvent (EntityCombustByEntityEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityCombustEvent (EntityCombustEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityCreatePortalEvent (EntityCreatePortalEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityDamageByBlockEvent (EntityDamageByBlockEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityDamageByEntityEvent (EntityDamageByEntityEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityDamageEvent (EntityDamageEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityDeathEvent (EntityDeathEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityExplodeEvent (EntityExplodeEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityInteractEvent (EntityInteractEvent event){ processEvent(event, event.getBlock(), event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityPortalEnterEvent (EntityPortalEnterEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityPortalEvent (EntityPortalEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityPortalExitEvent (EntityPortalExitEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityRegainHealthEvent (EntityRegainHealthEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityShootBowEvent (EntityShootBowEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntitySpawnEvent (EntitySpawnEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityTameEvent (EntityTameEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityTargetEvent (EntityTargetEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityTargetLivingEntityEvent (EntityTargetLivingEntityEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityTeleportEvent (EntityTeleportEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }


    @EventHandler(priority = EventPriority.MONITOR)
    public void EntityUnleashEvent (EntityUnleashEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ExpBottleEvent (ExpBottleEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ExplosionPrimeEvent (ExplosionPrimeEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void FireworkExplodeEvent (FireworkExplodeEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void FoodLevelChangeEvent (FoodLevelChangeEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void HorseJumpEvent (HorseJumpEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ItemDespawnEvent (ItemDespawnEvent event){
        processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation(), event.getEntity().getItemStack().getType());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ItemMergeEvent (ItemMergeEvent event){
        processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation(), event.getEntity().getItemStack().getType());
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void ItemSpawnEvent (ItemSpawnEvent event){
        processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation(), event.getEntity().getItemStack().getType());
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void PigZapEvent (PigZapEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerDeathEvent (PlayerDeathEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PlayerLeashEntityEvent (PlayerLeashEntityEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void PotionSplashEvent (PotionSplashEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ProjectileHitEvent (ProjectileHitEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void ProjectileLaunchEvent (ProjectileLaunchEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void SheepDyeWoolEvent (SheepDyeWoolEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void SheepRegrowWoolEvent (SheepRegrowWoolEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void SlimeSplitEvent (SlimeSplitEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void SpawnerSpawnEvent (SpawnerSpawnEvent event){ processEvent(event, event.getEntity().getType().name(), event.getEntity().getLocation()); }


}
