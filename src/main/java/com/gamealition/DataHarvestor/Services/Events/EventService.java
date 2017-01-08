package com.gamealition.DataHarvestor.Services.Events;

import com.gamealition.DataHarvestor.DataHarvestor;
import com.gamealition.DataHarvestor.Events.Evnt;
import com.gamealition.DataHarvestor.HttpSend;

/**
 * Created by robert on 03/01/2017.
 */
public class EventService implements Runnable {
    private DataHarvestor instance;
    private HttpSend httpsend;

    public EventService(DataHarvestor instance){
        this.instance = instance;
        httpsend = new HttpSend(instance);
    }

    @Override
    public void run() {
        for (Evnt object: instance.dataStore.getEvntDatastore()) {
            httpsend.sendHTTPData("/server",object.toJSON());
            System.out.print(object.toJSON());
        }
        instance.dataStore.getEvntDatastore().clear();
    }
}
