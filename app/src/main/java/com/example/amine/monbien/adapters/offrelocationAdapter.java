package com.example.amine.monbien.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.amine.monbien.entities.offredeLocation;

import com.example.amine.monbien.R;

import java.util.List;

public class offrelocationAdapter extends ArrayAdapter<offredeLocation> {
    public offrelocationAdapter(@NonNull Context context, List<offredeLocation> offerdelocationlist) {
        super(context,0, offerdelocationlist);
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
        offredeLocation offerdelocation = getItem(position);
        ViewHolder vh;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listeofferlocation, parent, false);
            vh = new ViewHolder();
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
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.numtel.setText(offerdelocation.getNumtel());
        vh.nom.setText(offerdelocation.getNom());
        vh.typebien.setText(offerdelocation.getTypebien());
        vh.nbchambre.setText(offerdelocation.getNbchambre());
        vh.region.setText(offerdelocation.getRegion());
        vh.ville.setText(offerdelocation.getVille());
        vh.remarque.setText(offerdelocation.getRemarque());
        vh.prix.setText(offerdelocation.getPrix());
        vh.date.setText(offerdelocation.getDate());
        vh.nomUtilisateur.setText(offerdelocation.getNomutilisateur());

        return convertView;
    }
}

