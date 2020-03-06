package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.utils.NavigatorData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Modifprofile extends AppCompatActivity {
    User currentuser;
    EditText nom , email, password;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifprofile);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        nom = (EditText) findViewById(R.id.edittextnom);
        email = (EditText) findViewById(R.id.edittextemail);
        password = (EditText) findViewById(R.id.edittextpassword);
        mAuth = FirebaseAuth.getInstance();
        UserDAO dao = new UserDAO(this);
        for (User user:
                dao.GetAllUser()) {
            currentuser=user;
            break;
        }
        nom.setText(currentuser.getNom());
        email.setText(currentuser.getEmail());
        password.setText(currentuser.getPassword());
    }
    public void clickback(View view) {
        Intent intent = new Intent(Modifprofile.this, profile.class);
        startActivity(intent);

    }
    public void  clickconfirmermodifier(View view) {
        NavigatorData.getInstance().setUpdate(true);
        if (checkInternetConnection(getApplicationContext())) {
            if (!TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(nom.getText())) {
                FirebaseUser userfire = mAuth.getCurrentUser();
                userfire.updateEmail(email.getText().toString());
                userfire.updatePassword(password.getText().toString());
                DatabaseReference taskRef = databaseReference.child(currentuser.getId());
                DatabaseReference emaild = taskRef.child("email");
                DatabaseReference nomd = taskRef.child("nom");
                DatabaseReference passwordd = taskRef.child("password");
                emaild.setValue(email.getText().toString());
                nomd.setValue(nom.getText().toString());
                passwordd.setValue(password.getText().toString());

                Toast.makeText(Modifprofile.this, "profile modifier." ,
                        Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(Modifprofile.this, "verifier si les champs sont vides." ,
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            NavigatorData.getInstance().setUpdate(false);
            Intent intent = new Intent(Modifprofile.this, login.class);
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

