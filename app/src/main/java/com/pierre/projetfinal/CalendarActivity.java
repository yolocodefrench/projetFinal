package com.pierre.projetfinal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.pierre.projetfinal.Model.Pays;
import com.pierre.projetfinal.Model.PaysEnregistre;

import org.parceler.Parcels;

import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        final DatePicker datePicker = (DatePicker) findViewById(R.id.date_picker);
        Button button = (Button) findViewById(R.id.buttonValider);
        final PaysEnregistre pays = new PaysEnregistre(
            getIntent().getStringExtra(Constantes.EXTRA_NAME),
            getIntent().getStringExtra(Constantes.EXTRA_CAPITALE),
            getIntent().getStringExtra(Constantes.EXTRA_CONTINENT),
            getIntent().getStringExtra(Constantes.EXTRA_DRAPEAU));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DaoPaysEnregistre dao = new DaoPaysEnregistre();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year =  datePicker.getYear();

                String monthString ="";

                switch (month) {
                    case 1:  monthString = "Jan.";
                        break;
                    case 2:  monthString = "Feb.";
                        break;
                    case 3:  monthString = "Mar.";
                        break;
                    case 4:  monthString = "Apr.";
                        break;
                    case 5:  monthString = "May";
                        break;
                    case 6:  monthString = "June";
                        break;
                    case 7:  monthString = "July";
                        break;
                    case 8:  monthString = "Aug.";
                        break;
                    case 9:  monthString = "Sept.";
                        break;
                    case 10: monthString = "Oct.";
                        break;
                    case 11: monthString = "Nov.";
                        break;
                    case 12: monthString = "Dec.";
                        break;
                    default: monthString = "Invalid month";
                        break;
                }

                String dateVoyage= day+" "+monthString+" "+year;
                Log.i("Drapeau", pays.getChemin());
                final PaysEnregistre paysEnregistre = new PaysEnregistre(
                        pays.getName(),
                        pays.getCapitale(),
                        pays.getContinent(),
                        pays.getChemin(),
                        dateVoyage);
                if(dao.getPaysByName(CalendarActivity.this, pays.getName())){
                    Log.i("getPays", "Il y a déjà un pays");

                    VisiteDialog visiteDialog = new VisiteDialog();
                    visiteDialog.setArguments(paysEnregistre);
                    visiteDialog.show(getSupportFragmentManager(), "visite");

                }
                else{
                    dao.insertCountry(CalendarActivity.this, paysEnregistre);
                    Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }






            }
        });
    }
}
