package com.bentaher.youssefbentaher_pset6;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Youssef on 21/09/2017.
 * Deze Requesthelper haalt informatie maakt connectie met de API, en zet de JSondata om
 * in een string.
 */

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... params)  {
        String result = "";
        String chosenTag = params[0];
        Log.i("stad : ", chosenTag);


        URLConnection connection = null;
        BufferedReader reader = null;
        URL url;

        //Connectie met de API aanmaken en de informatie in een String zetten.
        try {

            url = new URL("http://api.openweathermap.org/data/2.5/forecast?q="+chosenTag+"&units=metric&appid=c5b1f6c5367cda67a8575b8fa49b4c44");

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            String line;
            while((line = reader.readLine()) != null){
                result += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }


}
