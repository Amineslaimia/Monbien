package com.example.amine.monbien.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.amine.monbien.R;
import com.example.amine.monbien.utils.PhonecallReceiver;

public class ajouterdemandeoffer extends AppCompatActivity {
    Boolean button1 = false ;
    LinearLayout layoutofferlocation;
    LinearLayout  layoutoffervente;

    Boolean button2 = false ;
    LinearLayout  layoutdemandelocation;
    LinearLayout  layoutdemandeachat;
    LinearLayout  layoutdemanderesi;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouterdemandeoffer);
        layoutofferlocation= (LinearLayout)findViewById(R.id.offerlocation);
        layoutoffervente= (LinearLayout)findViewById(R.id.offervente);

        layoutofferlocation.setVisibility(View.GONE);
        layoutoffervente.setVisibility(View.GONE);

        layoutdemandelocation= (LinearLayout)findViewById(R.id.demandelocation);
        layoutdemandeachat= (LinearLayout)findViewById(R.id.demandeachat);
        layoutdemanderesi= (LinearLayout)findViewById(R.id.demanderesi);
        imageView =  (ImageView)findViewById(R.id.imageView);
        layoutdemandelocation.setVisibility(View.GONE);
        layoutdemandeachat.setVisibility(View.GONE);
        layoutdemanderesi.setVisibility(View.GONE);
        if(PhonecallReceiver.fromDialog==true){
            imageView.setVisibility(View.GONE);

        }



    }
    public void clickhome(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, home.class);
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
    public void clickajouterofferlocation(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, ajouterOfferLocation.class);
        startActivity(intent);
        finish();
    }
    public void clickajouteroffervente(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, ajouterOffervente.class);
        startActivity(intent);
        finish();
    }
    public void clickajouterdemandelocation(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, ajouterDemandeLocation.class);
        startActivity(intent);
        finish();
    }
    public void clickajouterdemandeachat(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, ajouterDemandeAchat.class);
        startActivity(intent);
        finish();
    }
    public void clickajouterdemanderesidence(View view) {
        Intent intent = new Intent(ajouterdemandeoffer.this, ajouterDemandeResidence.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() { }
}
