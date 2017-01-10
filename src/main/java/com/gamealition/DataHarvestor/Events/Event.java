package com.gamealition.DataHarvestor.Events;

import org.json.simple.JSONObject;

/**
 * Created by robert on 09/01/2017.
 */
public interface Event {
    public JSONObject toJSON();
}
