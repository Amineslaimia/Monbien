package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.amine.monbien.R;
import com.example.amine.monbien.adapters.listeagentAdapter;
import com.example.amine.monbien.adapters.listeresidenceAdapter;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.residence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gestionResidence extends AppCompatActivity {
    ListView lst;
    List<residence> residencesList;
    DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_residence);
        lst = (ListView) findViewById(R.id.residencelisteview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Residences");
        residencesList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                residencesList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    residence resi = dataSnapshot1.getValue(residence.class);

                        residencesList.add(resi);
                }
                listeresidenceAdapter adapter = new listeresidenceAdapter(gestionResidence.this,residencesList);
                lst.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println( "Failed to read value."+ error.toException());
            }
        });

    }

    public void clickhome(View view) {
        Intent intent = new Intent(gestionResidence.this, home.class);
        startActivity(intent);
    }
    public void clickajouterresidence (View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(gestionResidence.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(gestionResidence.this, ajouterResidence.class);
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
