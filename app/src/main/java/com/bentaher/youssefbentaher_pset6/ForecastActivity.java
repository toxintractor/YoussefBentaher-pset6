package com.bentaher.youssefbentaher_pset6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        String adam = "Amsterdam";

        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(adam);
    }
}
