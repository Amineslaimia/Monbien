package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;

public class profile extends AppCompatActivity {
User currentuser;
TextView nom , email, role;
ImageView modifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nom = (TextView) findViewById(R.id.editTextnom);
        email = (TextView) findViewById(R.id.editTextemail);
        role = (TextView) findViewById(R.id.editTextrole);
        modifier=(ImageView)findViewById(R.id.imagemodif);
        if (!checkInternetConnection(getApplicationContext())) {
            modifier.setVisibility(View.GONE);
        }
        UserDAO dao = new UserDAO(this);
        for (User user:
                dao.GetAllUser()) {
            currentuser=user;
            break;
        }
        nom.setText(currentuser.getNom());
        email.setText(currentuser.getEmail());
        role.setText(currentuser.getRole());

    }
    public void clickhome(View view) {
        Intent intent = new Intent(profile.this, home.class);
        startActivity(intent);
    }
    public void clickmodifier(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(profile.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(profile.this, Modifprofile.class);
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
