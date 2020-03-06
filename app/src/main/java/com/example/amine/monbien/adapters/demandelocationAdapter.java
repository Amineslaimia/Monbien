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
import com.example.amine.monbien.entities.demandedelocation;

import java.util.List;

public class demandelocationAdapter extends ArrayAdapter<demandedelocation> {
    public demandelocationAdapter(@NonNull Context context, List<demandedelocation> demandelocationlist) {
        super(context,0, demandelocationlist);
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
        demandedelocation demandeloca = getItem(position);
        demandelocationAdapter.ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listedemandelocation, parent, false);
            vh = new demandelocationAdapter.ViewHolder();
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
            vh = ( demandelocationAdapter.ViewHolder) convertView.getTag();
        }

        vh.numtel.setText(demandeloca.getNumtel());
        vh.nom.setText(demandeloca.getNom());
        vh.typebien.setText(demandeloca.getTypebien());
        vh.nbchambre.setText(demandeloca.getNbchambre());
        vh.region.setText(demandeloca.getRegion());
        vh.ville.setText(demandeloca.getVille());
        vh.remarque.setText(demandeloca.getRemarque());
        vh.prix.setText(demandeloca.getPrix());
        vh.date.setText(demandeloca.getDate());
        vh.nomUtilisateur.setText(demandeloca.getNomutilisateur());

        return convertView;
    }
}
