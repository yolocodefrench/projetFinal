package com.pierre.projetfinal;

import android.provider.BaseColumns;

/**
 * Created by Utilisateur on 10/04/2018.
 */

public class BaseContrat {

    // constructeur privé afin de ne pas instancier la classe :
    private BaseContrat() {}
    // contenu de la table "country" :
    public static class PaysContrat implements BaseColumns
    {
        public static final String ID ="id";
        public static final String TABLE_PAYS = "pays";
        public static final String COLONNE_NAME = "name";
        public static final String COLONNE_CAPITAL = "capital";
        public static final String COLONNE_CONTINENT = "continent";
        public static final String COLONNE_DRAPEAU = "flag";
        public static final String COLONNE_DATE = "date";
    }
}
