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
import com.example.amine.monbien.entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gestionAgents extends AppCompatActivity {
    ListView lst;
    List<User> userList;
    DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_agents);
        lst = (ListView) findViewById(R.id.agentlistview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);
                    if (!user.getRole().equals("admin"))
                        userList.add(user);
                }
                listeagentAdapter adapter = new listeagentAdapter(gestionAgents.this,userList);
                lst.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println( "Failed to read value."+ error.toException());
            }
        });


    }
    public void clickhome (View view) {
        Intent intent = new Intent(gestionAgents.this, home.class);
        startActivity(intent);
    }
    public void clickajouteragent (View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(gestionAgents.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(gestionAgents.this, ajouterAgent.class);
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
