package com.bentaher.youssefbentaher_pset6;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mocro on 21/09/2017.
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    ForecastActivity ForecastAct;

    public TrackAsyncTask(ForecastActivity main){
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

        JSONObject weatherNext = new JSONObject();
        JSONArray weatherArr = new JSONArray();
        try {
            JSONObject weatherDataStream = new JSONObject(result);
            //weatherNext = weatherDataStream.getJSONObject("list");
            //weatherNext = weatherNext.getJSONObject("main");

            weatherArr = weatherDataStream.getJSONArray("list");
            Log.i("value of list", String.valueOf(weatherArr.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Weer> weer = new ArrayList<Weer>();
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
                if(weatherArr.getJSONObject(i).getJSONObject("rain").length() == 0){
                    rain = "0.00";
                }
                else{
                    rain = String.valueOf(weatherArr.getJSONObject(i).getJSONObject("rain").getString("3h"));
                }
                Log.i("value of list", String.valueOf(weatherArr.getJSONObject(i).getString("dt_txt")));

                Weer weren = new Weer(tijd, algemeen, temp, luchtdr, luchtv, winds, rain);
                weer.add(weren);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (Weer woeroe: weer) {
            Log.i("regen: ", woeroe.getTemperatuur());
        }


    }
}
