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

import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ajouterAgent extends AppCompatActivity {
    EditText editTextEmail, editTextPassword,nomtxt;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_agent);
        editTextEmail = (EditText) findViewById(R.id.editTextemailagent);
        editTextPassword = (EditText) findViewById(R.id.editTextmotdepasseagent);
        nomtxt = (EditText) findViewById(R.id.editTextnomagent);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    }
    private void ajouteragent() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String nom = nomtxt.getText().toString().trim();

        if (nom.isEmpty()) {
            nomtxt.setError("Le nom est requis");
            nomtxt.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("L'email est requis");
            editTextEmail.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Veuillez entrer un email valide");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("le mot de passe est requis");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("La longueur minimale du mot de passe doit être de 6");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String id =  databaseReference.push().getKey();
                    User user = new User(id,nom,email,password,"Agent");
                    databaseReference.child(id).setValue(user);
                    finish();
                    startActivity(new Intent(ajouterAgent.this, gestionAgents.class));

                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "Cet agent existe déjà", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    public void clickback(View view) {
        Intent intent = new Intent(ajouterAgent.this, gestionAgents.class);
        startActivity(intent);
    }
    public void clickconfirmerajouteragent (View view) {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String nom = nomtxt.getText().toString().trim();
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(ajouterAgent.this, login.class);
            startActivity(intent);
        }else {
            ajouteragent();

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
