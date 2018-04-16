package com.pierre.projetfinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pierre.projetfinal.Model.Pays;
import com.pierre.projetfinal.Model.PaysEnregistre;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Utilisateur on 01/04/2018.
 */

public class PaysEnregistreAdapter extends RecyclerView.Adapter<PaysEnregistreViewHolder>{

    private List<PaysEnregistre> listPays;
    private Context context;

    public PaysEnregistreAdapter(List<PaysEnregistre> listPays , Context context) {
        this.listPays = listPays;
        this.context = context;
    }
    @Override
    public PaysEnregistreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewPays = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new PaysEnregistreViewHolder(viewPays);

    }

    @Override
    public void onBindViewHolder(final PaysEnregistreViewHolder holder, int position) {
        Context context = holder.getImgView().getContext();
        Picasso.with(context).load(listPays.get(position).getChemin())
                .placeholder(R.drawable.down_arrow)
                .error(R.drawable.warning)
                .into(holder.getImgView());
        Log.i("TestDrapeau", listPays.get(position).getChemin());
        holder.getTxtViewCapitale().setText("Capitale : "+listPays.get(position).getCapitale());
        holder.getTxtViewPays().setText("Nom : "+listPays.get(position).getName());
        holder.getTxtViewContinent().setText("Continent : "+listPays.get(position).getContinent());
        holder.getTxtViewDate().setText(listPays.get(position).getDate());

        holder.getTrash().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPays.size();
    }

    public PaysEnregistre getItem(int position){
        return this.listPays.get(position);
    }

    public void deleteItem(int position){
        Log.i("essaiSupp", position+" test");
        try{
            DaoPaysEnregistre dao = new DaoPaysEnregistre();
            Log.i("testSuppr", "id dans la liste : "+this.listPays.get(position).getID());
            this.listPays.remove(position);
            this.notifyItemRemoved(position);

            try{
                dao.deleteCountry(this.context, this.listPays.get(position).getID());
            }catch(Exception e) {
                e.getMessage();
            }
            this.notifyDataSetChanged();
        }catch (Exception e){
            e.getMessage();
        }
    }


}
