package com.example.amine.monbien.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.offredevente;

import java.util.List;

public class offreventeAdapter extends ArrayAdapter<offredevente> {
    public offreventeAdapter(@NonNull Context context, List<offredevente> offerdeventelist) {
        super(context,0, offerdeventelist);
    }
    public static class ViewHolder{
        TextView numtel;
        TextView nom;
        TextView typebien;
        TextView nbchambre;
        TextView region;
        TextView ville;
        TextView prix;
        TextView remarque;
        TextView date;
        TextView nomUtilisateur;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        offredevente offerdevent = getItem(position);
        offreventeAdapter.ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listeoffervente, parent, false);
            vh = new offreventeAdapter.ViewHolder();
            vh.numtel = (TextView) convertView.findViewById(R.id.num_tel);
            vh.nom = (TextView) convertView.findViewById(R.id.nom);
            vh.typebien = (TextView) convertView.findViewById(R.id.type_bien);
            vh.nbchambre = (TextView) convertView.findViewById(R.id.nb_chambre);
            vh.region = (TextView) convertView.findViewById(R.id.region);
            vh.ville = (TextView) convertView.findViewById(R.id.ville);
            vh.prix = (TextView) convertView.findViewById(R.id.prix);
            vh.remarque = (TextView) convertView.findViewById(R.id.remarque);
            vh.date = (TextView) convertView.findViewById(R.id.dateajout);
            vh.nomUtilisateur = (TextView) convertView.findViewById(R.id.nomutilisateur);
            convertView.setTag(vh);
        } else {
            vh = (offreventeAdapter.ViewHolder) convertView.getTag();
        }

        vh.numtel.setText(offerdevent.getNumtel());
        vh.nom.setText(offerdevent.getNom());
        vh.typebien.setText(offerdevent.getTypebien());
        vh.nbchambre.setText(offerdevent.getNbchambre());
        vh.region.setText(offerdevent.getRegion());
        vh.ville.setText(offerdevent.getVille());
        vh.remarque.setText(offerdevent.getRemarque());
        vh.prix.setText(offerdevent.getPrix());
        vh.date.setText(offerdevent.getDate());
        vh.nomUtilisateur.setText(offerdevent.getNomutilisateur());

        return convertView;
    }
}
