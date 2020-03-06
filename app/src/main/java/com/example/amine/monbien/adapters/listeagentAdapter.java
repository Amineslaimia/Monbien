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
import com.example.amine.monbien.entities.User;

import java.util.List;

public class listeagentAdapter extends ArrayAdapter <User> {
    public listeagentAdapter(@NonNull Context context, List<User> userList) {
        super(context,0, userList);
    }
    public static class ViewHolder{
        TextView email;
        TextView nom;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);
        ViewHolder vh;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listeagent, parent, false);
            vh = new ViewHolder();
            vh.email = (TextView) convertView.findViewById(R.id.email);
            vh.nom = (TextView) convertView.findViewById(R.id.nom);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        vh.nom.setText(user.getNom());
        vh.email.setText(user.getEmail());

        return convertView;
    }
}

