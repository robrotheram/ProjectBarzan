package com.gamealition.DataHarvestor;

/**
 * Created by robert on 22/05/2016.
 */
public class ConfigLoader {

    public String getAPIURL() {
        return APIURL;
    }

    public void setAPIURL(String APIURL) {
        this.APIURL = APIURL;
    }

    private  String APIURL;







    public  void reloadConfig(){
        //See "Creating you're defaults"
        DataHarvestor.getInstance().getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
        saveConfig();
        //Get data
        setAPIURL(DataHarvestor.getInstance().getConfig().getString("APIURL"));


    }

    public  void saveConfig(){
        //Save the config whenever you manipulate it
        DataHarvestor.getInstance().saveConfig();
    }

}