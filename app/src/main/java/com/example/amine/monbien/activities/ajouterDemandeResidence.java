package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amine.monbien.DAO.DemandeAchatDAO;
import com.example.amine.monbien.DAO.DemandeResidenceDAO;
import com.example.amine.monbien.DAO.ResidenceDAO;
import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.demandeachat;
import com.example.amine.monbien.entities.demanderesidence;
import com.example.amine.monbien.entities.residence;
import com.example.amine.monbien.utils.PhonecallReceiver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ajouterDemandeResidence extends AppCompatActivity {
    String[] etagearray = new String[]{"1", "2", "3","4","5"};
    String[] nbchambrearray = new String[]{"s+1", "s+2", "s+3", "s+4","s+5"};
    public SearchableSpinner nbchambredropdown,residencedropdown,etagedropdown;
    public ArrayAdapter<String> residenceadapter,nbchambreadapter,etageadapter;
    EditText numteltxt, nomtxt, prixtxt, remarquetxt;
    DatabaseReference databaseReference;
    ArrayList<String> residencearray ;
    List<residence> residencesList;
    DatabaseReference databaseResidenceliste ;
    User currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_demande_residence);
        residencedropdown = (SearchableSpinner) findViewById(R.id.spinnerresidence);
        etagedropdown = (SearchableSpinner) findViewById(R.id.spinneretage);
        etageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, etagearray);
        etagedropdown.setAdapter(etageadapter);
        nbchambredropdown = (SearchableSpinner) findViewById(R.id.spinnernbchambre);
        nbchambreadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nbchambrearray);
        nbchambredropdown.setAdapter(nbchambreadapter);
        residencedropdown.setTitle("Choisir la résidence");
        residencedropdown.setPositiveButton("fermer");
        etagedropdown.setTitle("Choisir etage");
        etagedropdown.setPositiveButton("fermer");
        nbchambredropdown.setTitle("Choisir nombre de chambres");
        nbchambredropdown.setPositiveButton("fermer");
        databaseReference = FirebaseDatabase.getInstance().getReference("demanderesidence");
        databaseResidenceliste = FirebaseDatabase.getInstance().getReference("Residences");
        residencearray =new ArrayList<String>();
        numteltxt = (EditText) findViewById(R.id.editTextnumtel);
        nomtxt = (EditText) findViewById(R.id.editTextnom);
        prixtxt = (EditText) findViewById(R.id.editTextprix);
        remarquetxt = (EditText) findViewById(R.id.editTextremarque);
        if (checkInternetConnection(getApplicationContext())) {
            databaseResidenceliste.addValueEventListener(new ValueEventListener() {
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
        if (PhonecallReceiver.fromDialog==true){
            numteltxt.setText(PhonecallReceiver.EndNumber);

        }
        UserDAO dao = new UserDAO(this);
        for (User user:
                dao.GetAllUser()) {
            currentuser=user;
            break;
        }
        ResidenceDAO residao = new  ResidenceDAO(this);
        for (residence resi:
                residao.GetAllResidance()) {
            residencearray.add(resi.getNom());
        }

        residenceadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, residencearray);
        residencedropdown.setAdapter(residenceadapter);
        System.out.println(residencesList);
    }
    public void clickback(View view) {
        Intent intent = new Intent(ajouterDemandeResidence.this, ajouterdemandeoffer.class);
        startActivity(intent);
    }
    private void ajouterdemanderesidence() {
        String numtel = numteltxt.getText().toString().trim();
        String nom = nomtxt.getText().toString().trim();
        String nomresidence = String.valueOf(residencedropdown.getSelectedItem());
        String nbchambre = String.valueOf(nbchambredropdown.getSelectedItem());
        String nbetage = String.valueOf(etagedropdown.getSelectedItem());
        String prix = prixtxt.getText().toString().trim();
        String remarque = remarquetxt.getText().toString().trim();

        String username;
        if (numtel.isEmpty()) {
            numteltxt.setError("Le numéro de téléphone est requis");
            numteltxt.requestFocus();
            return;
        }
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        username= currentuser.getNom();
        if (checkInternetConnection(getApplicationContext())){
        String id = databaseReference.push().getKey();
        demanderesidence demanderesi = new demanderesidence(id, numtel, nom,nomresidence,nbchambre,nbetage,prix,remarque,currentDateTimeString,username);
        databaseReference.child(id).setValue(demanderesi);

        Toast.makeText(ajouterDemandeResidence.this,
                "une demande résidence est ajouteé ",
                Toast.LENGTH_SHORT).show();
    }else {
        demanderesidence demanderesi = new demanderesidence(numtel,nom,nomresidence,nbchambre,nbetage,prix,remarque,currentDateTimeString,username);
        DemandeResidenceDAO dao = new DemandeResidenceDAO(ajouterDemandeResidence.this);

        long idInsert = dao.insertDemandeResidence(demanderesi);
        Toast.makeText(ajouterDemandeResidence.this, "une demande résidence est ajouteé (HORS_LIGNE)",Toast.LENGTH_SHORT).show();
    }
        if (PhonecallReceiver.fromDialog==true){
            PhonecallReceiver.fromDialog=false;
            finishAndRemoveTask();
        }else {

            Intent myintent = new Intent(ajouterDemandeResidence.this, ajouterdemandeoffer.class);
            startActivity(myintent);
        }



    }
    public void clickconfirmerajouterdemanderesidence (View view) {
        ajouterdemanderesidence();

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
