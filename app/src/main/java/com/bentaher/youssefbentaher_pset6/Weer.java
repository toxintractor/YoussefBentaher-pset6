package com.bentaher.youssefbentaher_pset6;

/**
 * Created by mocro on 16/10/2017.
 */

public class Weer {

    public String tijdStip;
    public String algemeen;
    public String temperatuur;
    public String luchtDruk;
    public String luchtVochtigHeid;
    public String windSnelheid;
    public String regen;



    //Standaard constuctor voor FireBase.
    public Weer(){}

    public Weer(String tijdStip, String algemeen, String temperatuur, String luchtDruk, String luchtVochtigHeid, String windSnelheid, String regen){

        this.tijdStip = tijdStip;
        this.algemeen = algemeen;
        this.temperatuur = temperatuur;
        this.luchtDruk = luchtDruk;
        this.luchtVochtigHeid = luchtVochtigHeid;
        this.windSnelheid = windSnelheid;
        this.regen = regen;
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
