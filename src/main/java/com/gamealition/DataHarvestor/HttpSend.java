package com.gamealition.DataHarvestor;

import org.json.simple.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by robert on 22/05/2016.
 */
public class HttpSend {
    DataHarvestor data;

    public HttpSend(DataHarvestor i){
        data = i;
    }

    public String sendHTTPData(String urlpath, JSONObject json) {
        HttpURLConnection connection = null;
        try {
            URL url=new URL(DataHarvestor.getInstance().getConfigData().getAPIURL()+urlpath);
            DataHarvestor.getInstance().getLogger().log(Level.WARNING,"URL: "+ url.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXJ2ZXJOYW1lIjoidGVzdF9zZXJ2ZXIifQ.X9HZSlGAPWiecEyRbIGj689K9CuoNmlf7_LubNj2MkA");

            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            streamWriter.write(json.toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                data.getLogger().log(Level.WARNING, stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                data.getLogger().log(Level.WARNING,  connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            data.getLogger().log(Level.WARNING,  exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }
}
