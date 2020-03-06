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
import com.example.amine.monbien.entities.demanderesidence;

import java.util.List;

public class demanderesidenceAdapter extends ArrayAdapter<demanderesidence> {
    public demanderesidenceAdapter(@NonNull Context context, List<demanderesidence> demanderesidencelist) {
        super(context,0, demanderesidencelist);
    }
    public static class ViewHolder{
        TextView numtel;
        TextView nom;
        TextView nomresidence;
        TextView nbchambre;
        TextView prix;
        TextView remarque;
        TextView date;
        TextView nomUtilisateur;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        demanderesidence demanderesi = getItem(position);
        demanderesidenceAdapter.ViewHolder vh;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listedemanderesidence, parent, false);
            vh = new demanderesidenceAdapter.ViewHolder();
            vh.numtel = (TextView) convertView.findViewById(R.id.num_tel);
            vh.nom = (TextView) convertView.findViewById(R.id.nom);
            vh.nomresidence = (TextView) convertView.findViewById(R.id.nom_residence);
            vh.nbchambre = (TextView) convertView.findViewById(R.id.nb_chambre);
            vh.prix = (TextView) convertView.findViewById(R.id.prix);
            vh.remarque = (TextView) convertView.findViewById(R.id.remarque);
            vh.date = (TextView) convertView.findViewById(R.id.dateajout);
            vh.nomUtilisateur = (TextView) convertView.findViewById(R.id.nomutilisateur);
            convertView.setTag(vh);
        } else {
            vh = ( demanderesidenceAdapter.ViewHolder) convertView.getTag();
        }

        vh.numtel.setText(demanderesi.getNumtel());
        vh.nom.setText(demanderesi.getNom());
        vh.nomresidence.setText(demanderesi.getNomresidence());
        vh.nbchambre.setText(demanderesi.getNbchambre());
        vh.remarque.setText(demanderesi.getRemarque());
        vh.prix.setText(demanderesi.getPrix());
        vh.date.setText(demanderesi.getDate());
        vh.nomUtilisateur.setText(demanderesi.getNomutilisateur());

        return convertView;
    }
}
