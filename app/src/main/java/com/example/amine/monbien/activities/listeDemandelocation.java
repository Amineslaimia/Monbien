package com.example.amine.monbien.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amine.monbien.R;
import com.example.amine.monbien.adapters.demandeachatAdapter;
import com.example.amine.monbien.adapters.demandelocationAdapter;
import com.example.amine.monbien.entities.demandeachat;
import com.example.amine.monbien.entities.demandedelocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listeDemandelocation extends AppCompatActivity {
    ListView lst;
    List<demandedelocation> demandelocationList;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_demandelocation);
        lst = (ListView) findViewById(R.id.demandelocationlistview);
        databaseReference = FirebaseDatabase.getInstance().getReference("demandedelocation");
        demandelocationList = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                demandelocationList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    demandedelocation demandeloca = dataSnapshot1.getValue(demandedelocation.class);
                    demandelocationList.add(demandeloca);
                }
                demandelocationAdapter adapter = new demandelocationAdapter (listeDemandelocation.this,demandelocationList);
                lst.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println( "Failed to read value."+ error.toException());
            }
        });
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = lst.getItemAtPosition(i);
                demandedelocation str = (demandedelocation) o; //As you are using Default String Adapter
                String num =str.getNumtel();
                onCall(num);

            }
        });
    }
    public void clickback(View view) {
        Intent intent = new Intent(listeDemandelocation.this, listeDonnees.class);
        startActivity(intent);
    }
    public void onCall(String num) {

        Intent callIntent = new Intent(Intent.ACTION_DIAL); //use ACTION_CALL class
        callIntent.setData(Uri.parse("tel:"+num));    //this is the phone number calling
        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        }else {     //have got permission
            try{
                startActivity(callIntent);  //call activity and make phone call
            }
            catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onBackPressed() { }
}
