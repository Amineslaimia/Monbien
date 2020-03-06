package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.amine.monbien.R;

public class listeDonnees extends AppCompatActivity {
Boolean button1 = false ;
LinearLayout  layoutofferlocation;
LinearLayout  layoutoffervente;
Boolean button2 = false ;
LinearLayout  layoutdemandelocation;
LinearLayout  layoutdemandeachat;
LinearLayout  layoutdemanderesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_donnees);
        layoutofferlocation= (LinearLayout)findViewById(R.id.offerlocation);
        layoutoffervente= (LinearLayout)findViewById(R.id.offervente);

        layoutofferlocation.setVisibility(View.GONE);
        layoutoffervente.setVisibility(View.GONE);

        layoutdemandelocation= (LinearLayout)findViewById(R.id.demandelocation);
        layoutdemandeachat= (LinearLayout)findViewById(R.id.demandeachat);
        layoutdemanderesi= (LinearLayout)findViewById(R.id.demanderesi);

        layoutdemandelocation.setVisibility(View.GONE);
        layoutdemandeachat.setVisibility(View.GONE);
        layoutdemanderesi.setVisibility(View.GONE);

    }

    public void clickhome(View view) {
        Intent intent = new Intent(listeDonnees.this, home.class);
        startActivity(intent);
    }
    public void clickshowoffersoptions(View view) {
        if (button1==false){
            button1=true;
            layoutofferlocation.setVisibility(View.VISIBLE);
            layoutoffervente.setVisibility(View.VISIBLE);

        }else{
            button1=false;
            layoutofferlocation.setVisibility(View.GONE);
            layoutoffervente.setVisibility(View.GONE);




        }
    }
    public void clickshowdemandsoptions(View view) {
        if (button2==false){
            button2=true;
            layoutdemandelocation.setVisibility(View.VISIBLE);
            layoutdemandeachat.setVisibility(View.VISIBLE);
            layoutdemanderesi.setVisibility(View.VISIBLE);


        }else{
            button2=false;
            layoutdemandelocation.setVisibility(View.GONE);
            layoutdemandeachat.setVisibility(View.GONE);
            layoutdemanderesi.setVisibility(View.GONE);






        }
    }
    public void clicklisteofferlocation(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(listeDonnees.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(listeDonnees.this, listeOfferlocation.class);
            startActivity(intent);
        }
    }
    public void clicklisteoffervente(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(listeDonnees.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(listeDonnees.this, listeOffervente.class);
            startActivity(intent);
        }
    }
    public void clicklistedemandelocation(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(listeDonnees.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(listeDonnees.this, listeDemandelocation.class);
            startActivity(intent);
        }
    }
    public void clicklistedemandeachat(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(listeDonnees.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(listeDonnees.this, listeDemandeachat.class);
            startActivity(intent);
        }
    }
    public void clicklistedemanderesidence(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(listeDonnees.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(listeDonnees.this, listeDemanderesidence.class);
            startActivity(intent);
        }
    }
    public static boolean checkInternetConnection(Context context)
    {
        try
        {
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected())
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public void onBackPressed() { }

}
