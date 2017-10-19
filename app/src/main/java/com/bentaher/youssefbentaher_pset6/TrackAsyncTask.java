package com.bentaher.youssefbentaher_pset6;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Youssef on 21/09/2017.
 * Deze Asynctask haalt informatie uit de HttpRequestHelper en filter de
 * juiste elementen uit de JSonbject en stopt het in een modelclass
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    ProfileAcvivity ForecastAct;

    public TrackAsyncTask(ProfileAcvivity main){
        this.ForecastAct = main;
        this.context = this.ForecastAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context,"Opvragen, Effe wachtennnnnnnnnnnnnnn",Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);


    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i("ide", result);

        //Modelclass instantie aanmaken.
        ArrayList<Weer> weer = new ArrayList<>();

        //JSONobject aanmaken en filteren.
        JSONObject weatherNext = new JSONObject();
        JSONArray weatherArr = new JSONArray();
        try {
            JSONObject weatherDataStream = new JSONObject(result);
            weatherArr = weatherDataStream.getJSONArray("list");
            Log.i("value of list", String.valueOf(weatherDataStream.getString("cod")));
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //In de forloop wordt de komende 24 uur aan weersverwachtingen uit de JSON gefilterd
        //en de model class object aangevuld. De modelclass word in een listArray gezet.
        for (int i = 0; i < 9; i++) {
            String tijd, algemeen, temp, luchtdr, luchtv, winds, rain;
            try {
                weatherNext = weatherArr.getJSONObject(i).getJSONObject("main");
                tijd = String.valueOf(weatherArr.getJSONObject(i).getString("dt_txt"));
                algemeen = String.valueOf(weatherArr.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"));
                temp = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("main").getString("temp"));
                luchtdr = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("main").getString("pressure"));
                luchtv = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("main").getString("humidity"));
                winds = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("wind").getString("speed"));
                if(!weatherArr.getJSONObject(i).has("rain")){
                    rain = "0.00";
                }
                else{
                    if(weatherArr.getJSONObject(i).getJSONObject("rain").length() == 0){
                        rain = "0.00";
                    }

                    else{
                        rain = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("rain").getString("3h"));
                    }
                }

                Log.i("value of list", String.valueOf(weatherArr.getJSONObject(i).getString("dt_txt")));

                Weer weren = new Weer(tijd, algemeen, temp, luchtdr, luchtv, winds, rain);
                weer.add(weren);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //De Arraylist word meegegeven aan de listActivity.
        Intent jumppage = new Intent(context, WeatherListActivity.class);
        jumppage.putExtra("extra", weer);
        context.startActivity(jumppage);

    }
}
