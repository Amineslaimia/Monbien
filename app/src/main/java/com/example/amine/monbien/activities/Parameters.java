package com.example.amine.monbien.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.demandeachat;
import com.example.amine.monbien.entities.demandedelocation;
import com.example.amine.monbien.entities.demanderesidence;
import com.example.amine.monbien.entities.offredeLocation;
import com.example.amine.monbien.entities.offredevente;
import com.example.amine.monbien.utils.NavigatorData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parameters extends AppCompatActivity {
    Button btnexpo;
    User currentuser;
    String role ;
    List<demandeachat> demandeachatList;
    List<demandedelocation> demandelocationList;
    List<demanderesidence> demanderesidenceList;
    List<offredeLocation> offerdeLocationList;
    List<offredevente> offerdeventeList;
    DatabaseReference DBRdemandeachat,DBRdemandelocation,DBRdemanderesi,DBRoffrelocation,DBRoffrevente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);
        btnexpo=(Button) findViewById(R.id.buttonexport);
        if(!NavigatorData.getInstance().isGod()) {
            if (checkInternetConnection(getApplicationContext())) {
                UserDAO dao = new UserDAO(this);

                for (User user :
                        dao.GetAllUser()) {
                    currentuser = user;
                    break;
                }
                role = currentuser.getRole();
                if (role.equals("admin")) {
                    DBRdemandeachat = FirebaseDatabase.getInstance().getReference("demandeachat");
                    demandeachatList = new ArrayList<>();
                    DBRdemandeachat.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            demandeachatList.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                demandeachat demandeach = dataSnapshot1.getValue(demandeachat.class);
                                demandeachatList.add(demandeach);

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                    DBRdemandelocation = FirebaseDatabase.getInstance().getReference("demandedelocation");
                    demandelocationList = new ArrayList<>();
                    DBRdemandelocation.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            demandelocationList.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                demandedelocation demandeloca = dataSnapshot1.getValue(demandedelocation.class);
                                demandelocationList.add(demandeloca);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                    DBRdemanderesi = FirebaseDatabase.getInstance().getReference("demanderesidence");
                    demanderesidenceList = new ArrayList<>();
                    DBRdemanderesi.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            demanderesidenceList.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                demanderesidence demanderesi = dataSnapshot1.getValue(demanderesidence.class);
                                demanderesidenceList.add(demanderesi);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                    DBRoffrelocation = FirebaseDatabase.getInstance().getReference("OffredeLocation");
                    offerdeLocationList = new ArrayList<>();
                    DBRoffrelocation.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            offerdeLocationList.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                offredeLocation offlocation = dataSnapshot1.getValue(offredeLocation.class);
                                offerdeLocationList.add(offlocation);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                    DBRoffrevente = FirebaseDatabase.getInstance().getReference("Offredevente");
                    offerdeventeList = new ArrayList<>();
                    DBRoffrevente.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            offerdeventeList.clear();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                offredevente offvente = dataSnapshot1.getValue(offredevente.class);
                                offerdeventeList.add(offvente);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            System.out.println("Failed to read value." + error.toException());
                        }
                    });
                } else {
                    btnexpo.setVisibility(View.GONE);
                }
            } else {
                btnexpo.setVisibility(View.GONE);
            }
        }
    }

    public void clickhome(View view) {
        Intent intent = new Intent(Parameters.this, home.class);
        startActivity(intent);
    }

    public void clicklogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Parameters.this, login.class);
        startActivity(intent);
        finish();

    }


    public void clickexport(View view) {
        String date = DateFormat.getDateTimeInstance().format(new Date());
        saveExcelFile(this,date+".xls");


    }

    public boolean saveExcelFile(Context context, String fileName) {


        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
        System.out.println("false");

            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;


        //New Sheet
        Sheet sheet1 = wb.createSheet("demande d'achat");

        // Generate column headings
        Row row1 = sheet1.createRow(0);

        c = row1.createCell(0);
        c.setCellValue("id");
        c = row1.createCell(1);
        c.setCellValue("numero client");
        c = row1.createCell(2);
        c.setCellValue("nom client");
        c = row1.createCell(3);
        c.setCellValue("type de bien");
        c = row1.createCell(4);
        c.setCellValue("etage");
        c = row1.createCell(5);
        c.setCellValue("nombre de chambre");
        c = row1.createCell(6);
        c.setCellValue("region");
        c = row1.createCell(7);
        c.setCellValue("ville");
        c = row1.createCell(8);
        c.setCellValue("prix");
        c = row1.createCell(9);
        c.setCellValue("remarque");
        c = row1.createCell(10);
        c.setCellValue("date");
        c = row1.createCell(11);
        c.setCellValue("nom utilisateur");



        for (int i=0; i<demandeachatList.size(); i++) {
            row1 = sheet1.createRow(i+1);
                c = row1.createCell(0);
                c.setCellValue(demandeachatList.get(i).getId());
                c = row1.createCell(1);
                c.setCellValue(demandeachatList.get(i).getNumtel());
                c = row1.createCell(2);
                c.setCellValue(demandeachatList.get(i).getNom());
                c = row1.createCell(3);
                c.setCellValue(demandeachatList.get(i).getTypebien());
                c = row1.createCell(4);
                c.setCellValue(demandeachatList.get(i).getNbetage());
                c = row1.createCell(5);
                c.setCellValue(demandeachatList.get(i).getNbchambre());
                c = row1.createCell(6);
                c.setCellValue(demandeachatList.get(i).getRegion());
                c = row1.createCell(7);
                c.setCellValue(demandeachatList.get(i).getVille());
                c = row1.createCell(8);
                c.setCellValue(demandeachatList.get(i).getPrix());
                c = row1.createCell(9);
                c.setCellValue(demandeachatList.get(i).getRemarque());
                c = row1.createCell(10);
                c.setCellValue(demandeachatList.get(i).getDate());
                c = row1.createCell(11);
                c.setCellValue(demandeachatList.get(i).getNomutilisateur());


        }

        //New Sheet
        Sheet sheet2 = wb.createSheet("demande de location");

        // Generate column headings
        Row row2 = sheet2.createRow(0);
        c = row2.createCell(0);
        c.setCellValue("id");
        c = row2.createCell(1);
        c.setCellValue("numero client");
        c = row2.createCell(2);
        c.setCellValue("nom client");
        c = row2.createCell(3);
        c.setCellValue("type de bien");
        c = row2.createCell(4);
        c.setCellValue("etage");
        c = row2.createCell(5);
        c.setCellValue("nombre de chambre");
        c = row2.createCell(6);
        c.setCellValue("region");
        c = row2.createCell(7);
        c.setCellValue("ville");
        c = row2.createCell(8);
        c.setCellValue("prix");
        c = row2.createCell(9);
        c.setCellValue("remarque");
        c = row2.createCell(10);
        c.setCellValue("date");
        c = row2.createCell(11);
        c.setCellValue("nom utilisateur");
        for (int i=0; i<demandelocationList.size(); i++) {
            row2 = sheet2.createRow(i+1);
            c = row2.createCell(0);
            c.setCellValue(demandelocationList.get(i).getId());
            c = row2.createCell(1);
            c.setCellValue(demandelocationList.get(i).getNumtel());
            c = row2.createCell(2);
            c.setCellValue(demandelocationList.get(i).getNom());
            c = row2.createCell(3);
            c.setCellValue(demandelocationList.get(i).getTypebien());
            c = row2.createCell(4);
            c.setCellValue(demandelocationList.get(i).getNbetage());
            c = row2.createCell(5);
            c.setCellValue(demandelocationList.get(i).getNbchambre());
            c = row2.createCell(6);
            c.setCellValue(demandelocationList.get(i).getRegion());
            c = row2.createCell(7);
            c.setCellValue(demandelocationList.get(i).getVille());
            c = row2.createCell(8);
            c.setCellValue(demandelocationList.get(i).getPrix());
            c = row2.createCell(9);
            c.setCellValue(demandelocationList.get(i).getRemarque());
            c = row2.createCell(10);
            c.setCellValue(demandelocationList.get(i).getDate());
            c = row2.createCell(11);
            c.setCellValue(demandelocationList.get(i).getNomutilisateur());


        }
        //New Sheet
        Sheet sheet3 = wb.createSheet("demande résidence");

        // Generate column headings
        Row row3 = sheet3.createRow(0);

        c = row3.createCell(0);
        c.setCellValue("id");


        c = row3.createCell(1);
        c.setCellValue("numero client");
        c = row3.createCell(2);
        c.setCellValue("nom client");

        c = row3.createCell(3);
        c.setCellValue("nom de résidence");
        c = row3.createCell(4);
        c.setCellValue("etage");
        c = row3.createCell(5);
        c.setCellValue("nombre de chambre");
        c = row3.createCell(6);
        c.setCellValue("prix");
        c = row3.createCell(7);
        c.setCellValue("remarque");
        c = row3.createCell(8);
        c.setCellValue("date");
        c = row3.createCell(9);
        c.setCellValue("nom utilisateur");
        for (int i=0; i<demanderesidenceList.size(); i++) {
            row3 = sheet3.createRow(i+1);
            c = row3.createCell(0);
            c.setCellValue(demanderesidenceList.get(i).getId());
            c = row3.createCell(1);
            c.setCellValue(demanderesidenceList.get(i).getNumtel());
            c = row3.createCell(2);
            c.setCellValue(demanderesidenceList.get(i).getNom());
            c = row3.createCell(3);
            c.setCellValue(demanderesidenceList.get(i).getNomresidence());
            c = row3.createCell(4);
            c.setCellValue(demanderesidenceList.get(i).getNbetage());
            c = row3.createCell(5);
            c.setCellValue(demanderesidenceList.get(i).getNbchambre());
            c = row3.createCell(6);
            c.setCellValue(demanderesidenceList.get(i).getPrix());
            c = row3.createCell(7);
            c.setCellValue(demanderesidenceList.get(i).getRemarque());
            c = row3.createCell(8);
            c.setCellValue(demanderesidenceList.get(i).getDate());
            c = row3.createCell(9);
            c.setCellValue(demanderesidenceList.get(i).getNomutilisateur());


        }
        //New Sheet
        Sheet sheet4 = wb.createSheet("offre de location");

        // Generate column headings
        Row row4 = sheet4.createRow(0);
        c = row4.createCell(0);
        c.setCellValue("id");
        c = row4.createCell(1);
        c.setCellValue("numero client");
        c = row4.createCell(2);
        c.setCellValue("nom client");
        c = row4.createCell(3);
        c.setCellValue("type de bien");
        c = row4.createCell(4);
        c.setCellValue("etage");
        c = row4.createCell(5);
        c.setCellValue("nombre de chambre");
        c = row4.createCell(6);
        c.setCellValue("region");
        c = row4.createCell(7);
        c.setCellValue("ville");
        c = row4.createCell(8);
        c.setCellValue("prix");
        c = row4.createCell(9);
        c.setCellValue("remarque");
        c = row4.createCell(10);
        c.setCellValue("date");
        c = row4.createCell(11);
        c.setCellValue("nom utilisateur");
        for (int i=0; i<offerdeLocationList.size(); i++) {
            row4 = sheet4.createRow(i+1);
            c = row4.createCell(0);
            c.setCellValue(offerdeLocationList.get(i).getId());
            c = row4.createCell(1);
            c.setCellValue(offerdeLocationList.get(i).getNumtel());
            c = row4.createCell(2);
            c.setCellValue(offerdeLocationList.get(i).getNom());
            c = row4.createCell(3);
            c.setCellValue(offerdeLocationList.get(i).getTypebien());
            c = row4.createCell(4);
            c.setCellValue(offerdeLocationList.get(i).getNbetage());
            c = row4.createCell(5);
            c.setCellValue(offerdeLocationList.get(i).getNbchambre());
            c = row4.createCell(6);
            c.setCellValue(offerdeLocationList.get(i).getRegion());
            c = row4.createCell(7);
            c.setCellValue(offerdeLocationList.get(i).getVille());
            c = row4.createCell(8);
            c.setCellValue(offerdeLocationList.get(i).getPrix());
            c = row2.createCell(9);
            c.setCellValue(offerdeLocationList.get(i).getRemarque());
            c = row4.createCell(10);
            c.setCellValue(offerdeLocationList.get(i).getDate());
            c = row4.createCell(11);
            c.setCellValue(offerdeLocationList.get(i).getNomutilisateur());


        }
        //New Sheet
        Sheet sheet5 = wb.createSheet("offre de vente");

        // Generate column headings
        Row row5 = sheet5.createRow(0);
        c = row5.createCell(0);
        c.setCellValue("id");
        c = row5.createCell(1);
        c.setCellValue("numero client");
        c = row5.createCell(2);
        c.setCellValue("nom client");
        c = row5.createCell(3);
        c.setCellValue("type de bien");
        c = row5.createCell(4);
        c.setCellValue("etage");
        c = row5.createCell(5);
        c.setCellValue("nombre de chambre");
        c = row5.createCell(6);
        c.setCellValue("region");
        c = row5.createCell(7);
        c.setCellValue("ville");
        c = row5.createCell(8);
        c.setCellValue("prix");
        c = row5.createCell(9);
        c.setCellValue("remarque");
        c = row5.createCell(10);
        c.setCellValue("date");
        c = row5.createCell(11);
        c.setCellValue("nom utilisateur");
        for (int i=0; i<offerdeventeList.size(); i++) {
            row5 = sheet5.createRow(i+1);
            c = row5.createCell(0);
            c.setCellValue(offerdeventeList.get(i).getId());
            c = row5.createCell(1);
            c.setCellValue(offerdeventeList.get(i).getNumtel());
            c = row5.createCell(2);
            c.setCellValue(offerdeventeList.get(i).getNom());
            c = row5.createCell(3);
            c.setCellValue(offerdeventeList.get(i).getTypebien());
            c = row5.createCell(4);
            c.setCellValue(offerdeventeList.get(i).getNbetage());
            c = row5.createCell(5);
            c.setCellValue(offerdeventeList.get(i).getNbchambre());
            c = row5.createCell(6);
            c.setCellValue(offerdeventeList.get(i).getRegion());
            c = row5.createCell(7);
            c.setCellValue(offerdeventeList.get(i).getVille());
            c = row5.createCell(8);
            c.setCellValue(offerdeventeList.get(i).getPrix());
            c = row5.createCell(9);
            c.setCellValue(offerdeventeList.get(i).getRemarque());
            c = row5.createCell(10);
            c.setCellValue(offerdeventeList.get(i).getDate());
            c = row5.createCell(11);
            c.setCellValue(offerdeventeList.get(i).getNomutilisateur());


        }





        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        Toast.makeText(this, "un fichier excel a été créer", Toast.LENGTH_SHORT).show();
        return success;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
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



