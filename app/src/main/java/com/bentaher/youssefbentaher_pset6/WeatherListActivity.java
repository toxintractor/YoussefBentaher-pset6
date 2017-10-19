package com.bentaher.youssefbentaher_pset6;

/**
 * Created by Youssef on 21/09/2017.
 * In deze activity word de listview geplaatst met alle weer informatie van de komende 24uur.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity {

    private ArrayList<Weer> weerArray;
    private listAdapter adapter;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        //Arraylist word aangemaakt.
        weerArray = (ArrayList<Weer>) getIntent().getSerializableExtra("extra");
        //Adapter met de weerinformatie wordt aangeroepen.
        setAdapter();

    }

    //Functie om de adapter aan te roepen en te laten zien.
    public  void setAdapter(){
        adapter = new listAdapter(this, weerArray);
        lstView = (ListView) findViewById(R.id.lstView);
        lstView.setAdapter(adapter);

    }
}
