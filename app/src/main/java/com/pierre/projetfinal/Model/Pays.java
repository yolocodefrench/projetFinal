package com.pierre.projetfinal.Model;

import java.util.Date;

/**
 * Created by Utilisateur on 31/03/2018.
 */

public class Pays {
    private String name;
    private String capitale;
    private String Continent;
    private String drapeau ="http://www.geognos.com/api/en/countries/flag/";

    public Pays(){}
    public Pays(String name, String capitale, String continent, String finChemin) {
        this.name = name;
        this.capitale = capitale;
        this.Continent = continent;
        this.drapeau += finChemin+ ".png";
    }
    public Pays(String name, String capitale, String continent) {
        this.name = name;
        this.capitale = capitale;
        this.Continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getDrapeau() {
        return drapeau;
    }

    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }
}
