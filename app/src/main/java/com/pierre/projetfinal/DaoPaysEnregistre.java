package com.pierre.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.pierre.projetfinal.Model.PaysEnregistre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Utilisateur on 10/04/2018.
 */

public class DaoPaysEnregistre {

    public List<PaysEnregistre> getAllCountries(Context context){
        // classe qui étend SQLiteOpenHelper :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                BaseContrat.PaysContrat.ID,
                BaseContrat.PaysContrat.COLONNE_NAME,
                BaseContrat.PaysContrat.COLONNE_DRAPEAU,
                BaseContrat.PaysContrat.COLONNE_CAPITAL,
                BaseContrat.PaysContrat.COLONNE_CONTINENT,
                BaseContrat.PaysContrat.COLONNE_DATE
        };
        String tri = BaseContrat.PaysContrat.COLONNE_DATE+ " DESC " ;

        Cursor cursor = dbRead.query(
                BaseContrat.PaysContrat.TABLE_PAYS,

                projection,  // colonnes à retourner

                null,  // colonnes pour la clause WHERE (inactif)

                null, // valeurs pour la clause WHERE (inactif)

                null, // GROUP BY (inactif)

                null,     // HAVING (inactif)

                tri,     // ordre de tri

                null); // LIMIT (inactif)

        List<PaysEnregistre> listCountry = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.ID)));
                    // conversion des données remontées en un objet métier :
                    listCountry.add(new PaysEnregistre(
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.COLONNE_NAME)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.COLONNE_CAPITAL)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.COLONNE_CONTINENT)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.COLONNE_DRAPEAU)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.COLONNE_DATE)),
                            cursor.getString(cursor.getColumnIndex(BaseContrat.PaysContrat.ID))

                    ));
                    Log.i("Test1", listCountry.size()+" ");
                    cursor.moveToNext();
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }
        return listCountry;
    }

    public boolean getPaysByName(Context context, String name){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en lecture (query) :
        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();

        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                BaseContrat.PaysContrat.ID
        };

        String selection = BaseContrat.PaysContrat.COLONNE_NAME + " = ? ";
        String[] selectionArgs = {name};

        Cursor cursor = dbRead.query(
                BaseContrat.PaysContrat.TABLE_PAYS,

                projection,  // colonnes à retourner

                selection,  // colonnes pour la clause WHERE (inactif)

                selectionArgs, // valeurs pour la clause WHERE (inactif)

                null, // GROUP BY (inactif)

                null,     // HAVING (inactif)

                null,     // ordre de tri

                null); // LIMIT (inactif)
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    cursor.moveToNext();
                    return true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    public void insertCountry(Context context, PaysEnregistre pays){

        // classe qui étend SQLiteOpenHelper :
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en écriture (insert, update, delete) :
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // objet de valeurs :
        ContentValues values = new ContentValues();
        values.put(BaseContrat.PaysContrat.COLONNE_NAME, pays.getName());
        values.put(BaseContrat.PaysContrat.COLONNE_CAPITAL, pays.getCapitale());
        values.put(BaseContrat.PaysContrat.COLONNE_CONTINENT, pays.getContinent());
        values.put(BaseContrat.PaysContrat.COLONNE_DRAPEAU, pays.getChemin());
        values.put(BaseContrat.PaysContrat.COLONNE_DATE, pays.getDate() );
        db.beginTransaction();

        try
        {
            // ajout :
            db.insert(BaseContrat.PaysContrat.TABLE_PAYS, null, values);
            // validation de la transaction :
            db.setTransactionSuccessful() ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // fin transaction :
            db.endTransaction();
            db.close();
        }



    }
    public void deleteCountry(Context context, String countryId){

        // classe qui étend SQLiteOpenHelper :
        Log.i("testSuppr", countryId+"");
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        // accès en écriture (insert, update, delete) :
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String countryIdStr = countryId;
        db.beginTransaction();
        try {
            String selection = BaseContrat.PaysContrat.ID + " = ? ";
            String[] selectionArgs = {countryIdStr};
            // filtre (clause WHERE) :
            db.delete(BaseContrat.PaysContrat.TABLE_PAYS, selection, selectionArgs);
            // validation de la transaction :
            db.setTransactionSuccessful() ;
        }
        catch (Exception e) {e.printStackTrace();}
        finally {// fin transaction :
            db.endTransaction();
            db.close();}

    }
    public void deleteAllCountry(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+BaseContrat.PaysContrat.TABLE_PAYS); //delete all rows in a table
        db.close();
    }
}
