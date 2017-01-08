package com.gamealition.DataHarvestor;

import com.gamealition.DataHarvestor.Events.Evnt;
import com.gamealition.DataHarvestor.Utils.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by robert on 14/05/2016.
 */
public class DataStore {
    private ArrayList<Evnt> evntDatastore = new ArrayList<>();
    public HashMap<Integer,Location> loadedChunks = new HashMap<>();

    public ArrayList<Evnt> getEvntDatastore() {
        return evntDatastore;
    }

    public void setEvntDatastore(ArrayList<Evnt> evntDatastore) {
        this.evntDatastore = evntDatastore;
    }
    public void pushEvent(Evnt evnt){
        evntDatastore.add(evnt);
    }


}
