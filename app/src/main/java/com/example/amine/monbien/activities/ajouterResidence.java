package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amine.monbien.DAO.ResidenceDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.residence;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ajouterResidence extends AppCompatActivity {
    EditText nomresidencetxt;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_residence);
        nomresidencetxt = (EditText) findViewById(R.id.editTextnomresidence);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Residences");
    }

    private void ajouterresidence() {
        final String nom = nomresidencetxt.getText().toString().trim();

        if (nom.isEmpty()) {
            nomresidencetxt.setError("Le nom est requis");
            nomresidencetxt.requestFocus();
            return;
        }


        String id = databaseReference.push().getKey();
        residence resi = new residence(id, nom);
        databaseReference.child(id).setValue(resi);


                startActivity(new Intent(ajouterResidence.this, gestionResidence.class));




                }







    public void clickconfirmerajouterresidence (View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(ajouterResidence.this, login.class);
            startActivity(intent);
        }else {

            ajouterresidence();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ResidenceDAO dao = new ResidenceDAO(getApplicationContext());
                    dao.Delete();
                    residence residencedao;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        residencedao = new residence(dataSnapshot1.getValue(residence.class).getNom());
                        dao.insertResidence(residencedao);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    System.out.println("Failed to read value." + error.toException());
                }
            });
        }
    }


    public void clickback(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(ajouterResidence.this, login.class);
            startActivity(intent);
        }else {
        Intent intent = new Intent(ajouterResidence.this, gestionResidence.class);
        startActivity(intent);}
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
