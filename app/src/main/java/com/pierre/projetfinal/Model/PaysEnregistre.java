package com.pierre.projetfinal.Model;

import org.parceler.Parcel;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Utilisateur on 01/04/2018.
 */
@Parcel
public class PaysEnregistre extends Pays {
    private String date;
    private String ID;
    private String chemin;

    public PaysEnregistre(String name, String capitale, String continent, String finChemin,  String dateVisite, String ID) {
        super(name, capitale, continent);
        this.chemin = finChemin;
        this.date = dateVisite;
        this.ID = ID;
    }
    public PaysEnregistre(String name, String capitale, String continent, String chemin,  String dateVisite) {
        super(name, capitale, continent);
        this.date = dateVisite;
        this.chemin = chemin;
    }
    public PaysEnregistre(String name, String capitale, String continent, String chemin){
        super(name, capitale, continent);
        this.chemin = chemin;
    }
    public PaysEnregistre(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
