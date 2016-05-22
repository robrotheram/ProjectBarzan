package com.gamealition.DataHarvestor.Datastore;

import java.util.ArrayList;

/**
 * Created by robert on 14/05/2016.
 */
public class DataStore {
    private ArrayList<PlayerData> playerStore = new ArrayList<>();
    private ArrayList<BlockData>  blockStore = new ArrayList<>();
    private ArrayList<EnityData>  enityStore = new ArrayList<>();
    private ArrayList<WorldData>  worldStore = new ArrayList<>();



    public ArrayList<PlayerData> getPlayerstore() {
        return playerStore;
    }
    public ArrayList<BlockData> getBlockStore() {
        return blockStore;
    }
    public ArrayList<EnityData> getEnityStore() {
        return enityStore;
    }
    public ArrayList<WorldData> getWorldStore() {
        return worldStore;
    }


    public void setEnityStore(ArrayList<EnityData> enityStore) { this.enityStore = enityStore; }
    public void setPlayerstore(ArrayList<PlayerData> playerstore) {
        playerStore = playerstore;
    }
    public void setBlockStore(ArrayList<BlockData> dataStore) { this.blockStore = dataStore;   }
    public void setWorldStore(ArrayList<WorldData> worldStore) { this.worldStore = worldStore; }

    public void addPlayer(PlayerData pd){
        playerStore.add(pd);
    }
    public void addBlock(BlockData bd){
        blockStore.add(bd);
    }
    public void addEnity(EnityData bd){
        enityStore.add(bd);
    }
    public void addWorld(WorldData bd){
        worldStore.add(bd);
    }
}
