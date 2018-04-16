package com.pierre.projetfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.pierre.projetfinal.Model.Pays;
import com.pierre.projetfinal.Model.PaysEnregistre;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView recyclerView =  findViewById(R.id.recycler);
        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);
        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<PaysEnregistre> listPays = new ArrayList<>();

        DaoPaysEnregistre dao = new DaoPaysEnregistre();
        //dao.deleteAllCountry(this);

        listPays = dao.getAllCountries(this);


        PaysEnregistreAdapter paysAdapter = new PaysEnregistreAdapter(listPays, this);
        recyclerView.setAdapter(paysAdapter);
    }

    public void ajouterPays(View view){
        Intent intent = new Intent(this, ActivityAllCountries.class);
        startActivity(intent);


    }



}
