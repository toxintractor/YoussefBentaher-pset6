package com.bentaher.youssefbentaher_pset6;

import java.io.Serializable;

/**
 * Created by Youssef on 16/10/2017.
 * Deze modelclass bevat de weerinformatie.
 */

public class Weer implements Serializable{

    //Alle elementen die weerinformatie bevatten.
    public String tijdStip;
    public String algemeen;
    public String temperatuur;
    public String luchtDruk;
    public String luchtVochtigHeid;
    public String windSnelheid;
    public String regen;



    //Standaard constuctor voor FireBase.
    public Weer(){}

    //constructor om alle elemten informatie te verkrijgen.
    public Weer(String tijdStip, String algemeen, String temperatuur, String luchtDruk,
                String luchtVochtigHeid, String windSnelheid, String regen){

        this.tijdStip = tijdStip;
        this.algemeen = algemeen;
        this.temperatuur = temperatuur;
        this.luchtDruk = luchtDruk;
        this.luchtVochtigHeid = luchtVochtigHeid;
        this.windSnelheid = windSnelheid;
        this.regen = regen;
    }

    //Methoden om de weerelementen op te vragen.
    public String getTijdStip(){
        return tijdStip;
    }
    public String getAlgemeen(){
        return algemeen;
    }
    public String getTemperatuur(){
        return temperatuur;
    }
    public String getLuchtDruk(){
        return luchtDruk;
    }
    public String getLuchtVochtigHeid(){
        return luchtVochtigHeid;
    }
    public String getWindSnelheid(){
        return windSnelheid;
    }
    public String getRegen(){
        return regen;
    }



}
