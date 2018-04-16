package com.pierre.projetfinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pierre.projetfinal.Model.Pays;
import com.pierre.projetfinal.Model.PaysEnregistre;
import com.pierre.projetfinal.PaysViewHolder;
import com.pierre.projetfinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Utilisateur on 31/03/2018.
 */

public class PaysAdapter extends RecyclerView.Adapter<PaysViewHolder>{

    private List<Pays> listPays;
    private List<Pays> listPays2 = new ArrayList<>();

    public PaysAdapter(List<Pays> listPays) {
        this.listPays = listPays;
        this.listPays2.addAll(this.listPays);
    }
    @Override
    public PaysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewPays = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_country, parent, false);
        return new PaysViewHolder(viewPays);

    }

    @Override
    public void onBindViewHolder(PaysViewHolder holder, int position) {
        Context context = holder.getImgView().getContext();
        Picasso.with(context).load(listPays.get(position).getDrapeau())
                .placeholder(R.drawable.down_arrow)
                .error(R.drawable.warning)
                .into(holder.getImgView());
        holder.getTxtViewCapitale().setText("Capitale : "+listPays.get(position).getCapitale());
        holder.getTxtViewPays().setText("Nom : "+listPays.get(position).getName());
        holder.getTxtViewContinent().setText("Continent : "+listPays.get(position).getContinent());
    }

    @Override
    public int getItemCount() {
        Log.i("Test1",""+listPays.size());
        return listPays.size();
    }

    public Pays getItem(int position){
        return this.listPays.get(position);
    }

    public void filter(String text) {
        this.listPays.clear();
        if(text.isEmpty()){
            listPays.addAll(listPays2);
        } else{
            text = text.toLowerCase();
            for(Pays pays: listPays2){
                if(pays.getName().trim().toLowerCase().contains(text)){
                    this.listPays.add(pays);
                }
            }
        }
        notifyDataSetChanged();
    }
}
