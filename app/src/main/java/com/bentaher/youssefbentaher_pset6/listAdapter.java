package com.bentaher.youssefbentaher_pset6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mocro on 27/09/2017.
 */

public class listAdapter extends ArrayAdapter {
    private ArrayList<Weer> weather;
    private Context context;
    private WeatherListActivity listActivity;
    //DBHelper db;



    public listAdapter(Context context, ArrayList<Weer> data) {
        super(context, 0 , data);
        this.weather = data;
        this.listActivity = (WeatherListActivity) context;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        //LayoutInflater theInflater = LayoutInflater.from(getContext());
        //final View theView = theInflater.inflate(R.layout.listlayout, parent, false);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listlayout, parent, false);
        }


        final Weer tk = weather.get(position);
        final String tijd = tk.getTijdStip();
        final String alGemeen = tk.getAlgemeen();
        final String temp = tk.getTemperatuur();
        final String luchtDruk = tk.getLuchtDruk();
        final String luchtVochtigheid = tk.getLuchtVochtigHeid();
        final String windSnelheid = tk.getWindSnelheid();
        final String neerSlag = tk.getRegen();

        final TextView tijdStip = (TextView) view.findViewById(R.id.tijdText);
        final TextView algemeen = (TextView) view.findViewById(R.id.algemeenText);
        final TextView temperatuur = (TextView) view.findViewById(R.id.tempText);
        final TextView luchtdruk = (TextView) view.findViewById(R.id.drukText);
        final TextView luchtvochtigheid = (TextView) view.findViewById(R.id.vochtText);
        final TextView windsnelheid = (TextView) view.findViewById(R.id.snelheidText);
        final TextView neerslag = (TextView) view.findViewById(R.id.regenText);

        tijdStip.setText(tijd);
        algemeen.setText(alGemeen);
        temperatuur.setText("Temperatuur: " + temp);
        luchtdruk.setText("Luchtdruk: " + luchtDruk);
        luchtvochtigheid.setText("luchtvochtogheid: " + luchtVochtigheid);
        windsnelheid.setText("Wind: " + windSnelheid);
        neerslag.setText("Neerslag: " + neerSlag);

        return view;
    }


    public int getCount(){
        return  super.getCount();
    }

    /*
    class keepCHeck implements View.OnClickListener {

        CheckBox ckBox;
        Taak tk1;
        public keepCHeck(CheckBox chBox, Taak tk2){
             ckBox = chBox;
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            db.open();
            if(ckBox.isChecked()){
                Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("true");
                db.update(tk1);
            }
            else{
                Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("false");
                db.update(tk1);
            }

        }
    }

    class goItem implements View.OnClickListener {
        Taak tk1;
        public goItem(Taak tk2){
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            db.open();
            //Toast.makeText(context, tk1, Toast.LENGTH_SHORT).show();
            Intent jumppage = new Intent(context, Main2Activity.class);
            jumppage.putExtra("data", tk1);
            context.startActivity(jumppage);

        }
    }
    */


}

