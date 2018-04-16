package com.pierre.projetfinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.pierre.projetfinal.Model.PaysEnregistre;

/**
 * Created by Utilisateur on 14/04/2018.
 */

public class VisiteDialog extends android.support.v4.app.DialogFragment {

    public PaysEnregistre pays = null;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Ce pays est déjà dans votre liste")
                .setTitle("Voulez-vous l'ajouter?");
// Add the buttons
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DaoPaysEnregistre dao = new DaoPaysEnregistre();
                //
                dao.insertCountry(getActivity(), pays);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return builder.create();
    }

    public void setArguments(PaysEnregistre pays){
        this.pays=pays;
    }

}
