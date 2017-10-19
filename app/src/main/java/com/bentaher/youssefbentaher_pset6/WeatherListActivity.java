package com.bentaher.youssefbentaher_pset6;

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

    weerArray = (ArrayList<Weer>) getIntent().getSerializableExtra("extra");

        for (Weer woeroe: weerArray) {
            Log.i("doorgegeven: ", woeroe.getTijdStip());
        }

        setAdapter();

    }

    public  void setAdapter(){
        adapter = new listAdapter(this, weerArray);
        lstView = (ListView) findViewById(R.id.lstView);
        lstView.setAdapter(adapter);

    }
}
