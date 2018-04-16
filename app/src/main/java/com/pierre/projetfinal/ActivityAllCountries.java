package com.pierre.projetfinal;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pierre.projetfinal.Model.Pays;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ActivityAllCountries extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private static final String CLE_FORM = "form";
    private static final String CLE_MEMO = "memo";
    private static final String CLE_POSITION = "position";
    private GestureDetector gestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_countries);

        final EditText editText = (EditText) findViewById(R.id.saisie_memo);
        final Button button = (Button) findViewById(R.id.bouton_valider);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://restcountries.eu/rest/v2/all", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                Toast.makeText(ActivityAllCountries.this, "Chargement des données", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String retour = new String(response);


                try
                {
                    JSONArray jsonArray = new JSONArray(retour);
                    ArrayList<Pays> listPays = new ArrayList<>();
                    final RecyclerView recyclerView = findViewById(R.id.recycler);

                    recyclerView.setHasFixedSize(true);
                    // layout manager, décrivant comment les items sont disposés :
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityAllCountries.this);
                    recyclerView.setLayoutManager(layoutManager);
                    for(int i =0 ; i < jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Log.i("TEST1",jsonObject.getString("name"));
                        Pays pays = new Pays(
                                jsonObject.getString("name"),
                                jsonObject.getString("capital"),
                                jsonObject.getString("region"),
                                jsonObject.getString("alpha2Code")
                        );
                        listPays.add(pays);
                    }

                    final PaysAdapter paysAdapter = new PaysAdapter(listPays);


                    recyclerView.setAdapter(paysAdapter);
                    // listener :
                    recyclerView.addOnItemTouchListener(ActivityAllCountries.this);
                    gestureDetector = new GestureDetector(ActivityAllCountries.this,
                            new GestureDetector.SimpleOnGestureListener()
                            {
                                @Override
                                public boolean onSingleTapUp(MotionEvent event)
                                {
                                    return true;
                                }
                            });

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            paysAdapter.filter(editText.getText().toString());
                            recyclerView.invalidate();
                        }
                    });

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(ActivityAllCountries.this, "Impossible de requêter l'API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Toast.makeText(ActivityAllCountries.this, "Nouvelle tentative de chargement", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent)
    {
        if (gestureDetector.onTouchEvent(motionEvent))
        {
            // récupération de l'item cliqué :
            View child = rv.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

            // position dans la liste d'objets métier (position à partir de zéro !) :
            if (child != null)
            {
                int position = rv.getChildAdapterPosition(child);
                Pays pays =((PaysAdapter) rv.getAdapter()).getItem(position);
                Intent intent = new Intent(this, CalendarActivity.class);
                intent.putExtra (Constantes.EXTRA_NAME, pays.getName());
                intent.putExtra (Constantes.EXTRA_CAPITALE, pays.getCapitale());
                intent.putExtra (Constantes.EXTRA_CONTINENT, pays.getContinent());
                intent.putExtra (Constantes.EXTRA_DRAPEAU, pays.getDrapeau());
                startActivity(intent);
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
