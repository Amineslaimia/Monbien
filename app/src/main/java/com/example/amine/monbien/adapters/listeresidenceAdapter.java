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
import com.example.amine.monbien.entities.residence;

import java.util.List;

public class listeresidenceAdapter extends ArrayAdapter <residence> {
    public listeresidenceAdapter(@NonNull Context context, List<residence> residencesList) {
        super(context,0, residencesList);
    }
    public static class ViewHolder{
        TextView nom;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        residence resi = getItem(position);
        ViewHolder vh;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listeresidence, parent, false);
            vh = new ViewHolder();
            vh.nom = (TextView) convertView.findViewById(R.id.nom);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.nom.setText(resi.getNom());


        return convertView;
    }
}
