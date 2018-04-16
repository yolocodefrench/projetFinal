package com.pierre.projetfinal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Utilisateur on 01/04/2018.
 */

public class PaysEnregistreViewHolder extends RecyclerView.ViewHolder {
    private ImageView imgView = null;
    private TextView txtViewDate = null;
    private TextView txtViewPays = null;
    private TextView txtViewCapitale = null;
    private TextView txtViewContinent = null;
    private ImageView trash = null;

    public PaysEnregistreViewHolder(View itemView) {
        super(itemView);
        this.setImgView((ImageView) itemView.findViewById(R.id.image));
        this.setTxtViewDate((TextView) itemView.findViewById(R.id.date));
        this.setTxtViewPays((TextView) itemView.findViewById(R.id.pays));
        this.setTxtViewContinent((TextView) itemView.findViewById(R.id.continent));
        this.setTxtViewCapitale((TextView) itemView.findViewById(R.id.capitale));
        this.setTrash((ImageView) itemView.findViewById(R.id.trash));
    }

    public ImageView getImgView() {
        return imgView;
    }

    public void setImgView(ImageView imgView) {
        this.imgView = imgView;
    }

    public TextView getTxtViewDate() {
        return txtViewDate;
    }

    public void setTxtViewDate(TextView txtViewDate) {
        this.txtViewDate = txtViewDate;
    }

    public TextView getTxtViewPays() {
        return txtViewPays;
    }

    public void setTxtViewPays(TextView txtViewPays) {
        this.txtViewPays = txtViewPays;
    }

    public TextView getTxtViewCapitale() {
        return txtViewCapitale;
    }

    public void setTxtViewCapitale(TextView txtViewCapitale) {
        this.txtViewCapitale = txtViewCapitale;
    }

    public TextView getTxtViewContinent() {
        return txtViewContinent;
    }

    public void setTxtViewContinent(TextView txtViewContinent) {
        this.txtViewContinent = txtViewContinent;
    }

    public ImageView getTrash() {
        return trash;
    }

    public void setTrash(ImageView trash) {
        this.trash = trash;
    }
}