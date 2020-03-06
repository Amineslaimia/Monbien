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
import com.example.amine.monbien.entities.demandeachat;

import java.util.List;

public class demandeachatAdapter extends ArrayAdapter<demandeachat> {
    public demandeachatAdapter(@NonNull Context context, List<demandeachat> demandeachatlist) {
        super(context,0, demandeachatlist);
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
        demandeachat demandeach = getItem(position);
        demandeachatAdapter.ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listedemandeachat, parent, false);
            vh = new demandeachatAdapter.ViewHolder();
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
            vh = ( demandeachatAdapter.ViewHolder) convertView.getTag();
        }

        vh.numtel.setText(demandeach.getNumtel());
        vh.nom.setText(demandeach.getNom());
        vh.typebien.setText(demandeach.getTypebien());
        vh.nbchambre.setText(demandeach.getNbchambre());
        vh.region.setText(demandeach.getRegion());
        vh.ville.setText(demandeach.getVille());
        vh.remarque.setText(demandeach.getRemarque());
        vh.prix.setText(demandeach.getPrix());
        vh.date.setText(demandeach.getDate());
        vh.nomUtilisateur.setText(demandeach.getNomutilisateur());

        return convertView;
    }

}
