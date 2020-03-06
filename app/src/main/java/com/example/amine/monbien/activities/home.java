package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.utils.NavigatorData;

import java.text.DateFormat;
import java.util.Date;

public class home extends AppCompatActivity {
User currentuser;
String role ;
RelativeLayout rlgestionagents , rlgestionresi,rllistedonne;
TextView txv, pagetitle;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rlgestionagents = (RelativeLayout) findViewById(R.id.rlgestionagent);
        rlgestionresi = (RelativeLayout) findViewById(R.id.rlgestionresi);
        rllistedonne = (RelativeLayout) findViewById(R.id.rlliestedonnes);
        txv = (TextView) findViewById(R.id.textView);
        pagetitle = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(!NavigatorData.getInstance().isGod()) {
            if (NavigatorData.getInstance().isUpdate()) {
                Intent intent = new Intent(home.this, login.class);
                startActivity(intent);
                NavigatorData.getInstance().setUpdate(false);

            } else {
                UserDAO dao = new UserDAO(this);

                for (User user :
                        dao.GetAllUser()) {
                    currentuser = user;
                    break;
                }
                role = currentuser.getRole();
                if (!role.equals("admin")) {
                    rlgestionagents.setVisibility(View.GONE);
                    rlgestionresi.setVisibility(View.GONE);
                    rllistedonne.setVisibility(View.GONE);
                    txv.setVisibility(View.GONE);
                    if (!checkInternetConnection(getApplicationContext())) {
                        pagetitle.setText("Accueil(mode hors-ligne)");
                        toolbar.setBackgroundColor(Color.parseColor("#3b3b3b"));
                    }
                } else {
                    if (!checkInternetConnection(getApplicationContext())) {
                        pagetitle.setText("Accueil(mode hors-ligne)");
                        toolbar.setBackgroundColor(Color.parseColor("#3b3b3b"));
                        rlgestionagents.setVisibility(View.GONE);
                        rlgestionresi.setVisibility(View.GONE);
                        rllistedonne.setVisibility(View.GONE);
                        txv.setVisibility(View.GONE);
                    }

                }
            }
        }
    }

    public void clickagents(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(home.this, login.class);
            startActivity(intent);
        }else{
        Intent intent = new Intent(home.this, gestionAgents.class);
        startActivity(intent);
        }
    }


    public void clickpraram(View view) {
        Intent intent = new Intent(home.this, Parameters.class);
        startActivity(intent);
        finish();

    }
    public void clickgesresi(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(home.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(home.this, gestionResidence.class);
            startActivity(intent);
        }
    }

    public void clicklistdonnee(View view) {
        if (!checkInternetConnection(getApplicationContext())) {
            Intent intent = new Intent(home.this, login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(home.this, listeDonnees.class);
            startActivity(intent);
        }
    }

    public void clickajouterdemandeoffer(View view) {
        Intent intent = new Intent(home.this, ajouterdemandeoffer.class);
        startActivity(intent);

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
