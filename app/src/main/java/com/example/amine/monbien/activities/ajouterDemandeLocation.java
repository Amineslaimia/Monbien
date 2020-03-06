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
import com.example.amine.monbien.DAO.DemandeLocationDAO;
import com.example.amine.monbien.DAO.UserDAO;
import com.example.amine.monbien.R;
import com.example.amine.monbien.entities.User;
import com.example.amine.monbien.entities.demandeachat;
import com.example.amine.monbien.entities.demandedelocation;
import com.example.amine.monbien.utils.PhonecallReceiver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.DateFormat;
import java.util.Date;

public class ajouterDemandeLocation extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String[] typedebienarray = new String[]{"Appartement", "Maison", "Local Commercial"};
    String[] etagearray = new String[]{"1", "2", "3","4","5"};
    String[] nbchambrearray = new String[]{"s+1", "s+2", "s+3", "s+4","s+5"};
    String[] regionarray = new String[]{"Tunis", "Ariana", "Ben Arous","Manouba","Nabeul","Zaghouan","Bizerte","Béja","Jendouba"
            ,"Kef","Siliana","Sousse","Monastir","Mahdia","Sfax","Kairouan","Kasserine","Sidi Bouzid","Gabès","Mednine","Tataouine"
            ,"Gafsa","Tozeur","Kebili"};
    String[] villearray = new String[]{"Tunis", "Le Bardo", "Le Kram","La Goulette","Carthage"
            ,"Sidi Bou Said","La Marsa","Sidi Hassine","Ariana", "La Soukra", "Raoued"
            ,"Kalâat el-Andalous", "Sidi Thabet","Ettadhamen-Mnihla","Ben Arous"
            ,"El Mourouj", "Hammam Lif","Hammam Chott","Bou Mhel el-Bassatine","Ezzahra"
            ,"Radès","Mégrine","Mohamedia-Fouchana","Mornag","Khalidia","Manouba", "Den Den"
            , "Douar Hicher","Oued Ellil","Mornaguia","Borj El Amri","Djedeida","Tebourba"
            ,"El Battan","Nabeul", "Dar Chaabane", "Béni Khiar","El Maâmoura","Somâa","Korba"
            ,"Tazerka","Menzel Temime","Menzel Horr","El Mida","Kelibia","Azmour","Hammam Ghezèze"
            ,"Dar Allouch","El Haouaria","Takelsa","Soliman","Korbous","Menzel Bouzelfa","Béni Khalled"
            ,"Zaouiet Djedidi","Grombalia","Bou Argoub","Hammamet","Zaghouan", "Zriba", "Bir Mcherga"
            ,"Djebel Oust", "El Fahs", "Nadhour","Bizerte", "Sejnane", "Mateur", "Menzel Bourguiba"
            , "Tinja", "Ghar al Milh","Aousja","Menzel Jemil","Menzel Abderrahmane","El Alia","Ras Jebel"
            ,"Metline","Raf Raf","Béja", "El Maâgoula", "Zahret Medien","Nefza","Téboursouk","Testour"
            ,"Goubellat","Majaz al Bab","Jendouba", "Bou Salem", "Tabarka","Aïn Draham","Fernana","Beni M'Tir"
            ,"Ghardimaou","Oued Melliz","El Kef", "Nebeur", "Touiref","Sakiet Sidi Youssef","Tajerouine"
            ,"Menzel Salem","Kalaat es Senam","Kalâat Khasba","Jérissa","El Ksour","Dahmani","Sers","Siliana"
            ,"Bou Arada","Gaâfour","El Krib","Sidi Bou Rouis","Maktar","Rouhia","Kesra","Bargou","El Aroussa"
            ,"Sousse","Ksibet Thrayet","Ezzouhour","Zaouiet Sousse","Hammam Sousse","Akouda","Kalâa Kebira"
            ,"Sidi Bou Ali","Hergla","Enfidha","Bouficha","Sidi El Hani","M'saken","Kalâa Seghira","Messaadine"
            ,"Kondar","Monastir","Khniss","Ouerdanin","Sahline Moôtmar","Sidi Ameur","Zéramdine","Beni Hassen"
            ,"Ghenada","Jemmal","Menzel Kamel","Zaouiet Kontoch","Bembla-Mnara","Menzel Ennour","El Masdour"
            ,"Moknine","Sidi Bennour","Menzel Farsi","Amiret El Fhoul","Amiret Touazra","Amiret El Hojjaj"
            ,"Cherahil","Bekalta","Téboulba","Ksar Hellal","Ksibet El Mediouni","Benen Bodher","Touza","Sayada"
            ,"Lemta","Bouhjar","Menzel Hayet","Mahdia","Rejiche","Bou Merdes","Ouled Chamekh","Chorbane","Hebira"
            ,"Essouassi","El Djem","Kerker","Chebba","Melloulèche","Sidi Alouane","Ksour Essef","El Bradâa","Sfax"
            ,"Sakiet Ezzit","Chihia","Sakiet Eddaïer","Gremda","El Ain","Thyna","Agareb","Jebiniana","El Hencha"
            ,"Menzel Chaker","Ghraïba, Tunisia","Bir Ali Ben Khélifa","Skhira","Mahares","Kerkennah","Kairouan"
            ,"Kairouan","Sbikha","Oueslatia","Aïn Djeloula","Haffouz","Alaâ","Hajeb El Ayoun","Nasrallah"
            ,"Menzel Mehiri","Echrarda","Bou Hajla","Kasserine","Sbeitla","Sbiba","Jedelienne","Thala","Haïdra"
            ,"Foussana","Fériana","Thélepte","Magel Bel Abbès","SidiBouzid","Jilma","Cebalet","Bir El Hafey"
            ,"Sidi Ali Ben Aoun","Menzel Bouzaiane","Meknassy","Mezzouna","Regueb","Ouled Haffouz","Gabès"
            ,"Chenini Nahal","Ghannouch","Métouia","Oudhref","El Hamma","Matmata","Nouvelle Matmata","Mareth"
            ,"Zarat","Medenine","Beni Khedache","Ben Gardane","Zarzis","Houmt El Souk (Djerba)","Midoun (Djerba)"
            ,"Ajim (Djerba)","Tataouine","Bir Lahmar","Ghomrassen","Dehiba","Remada","Gafsa","El Ksar","Moularès"
            ,"Redeyef","Métlaoui","Mdhila","El Guettar","Sened","Tozeur","Degache","Hamet Jerid","Nafta","Tamerza"
            ,"Kebili","Djemna","Douz","El Golâa","Souk Lahad"};
    EditText numteltxt, nomtxt, prixtxt, remarquetxt;
    DatabaseReference databaseReference;
    public SearchableSpinner nbchambredropdown,typedebiendropdown,etagedropdown,regiondropdown,villedropdown;
    public ArrayAdapter<String> typedebienadapter,nbchambreadapter,etageadapter,regionadapter,villeadapter;
    User currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_demande_location);
        typedebiendropdown = findViewById(R.id.spinnertypebien);
        typedebienadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, typedebienarray);
        typedebiendropdown.setAdapter(typedebienadapter);
        etagedropdown = findViewById(R.id.spinneretage);
        etageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, etagearray);
        etagedropdown.setAdapter(etageadapter);
        nbchambredropdown = findViewById(R.id.spinnernbchambre);
        nbchambreadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nbchambrearray);
        nbchambredropdown.setAdapter(nbchambreadapter);
        regiondropdown = findViewById(R.id.spinnerregion);
        regionadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, regionarray);
        regiondropdown.setAdapter(regionadapter);
        villedropdown = findViewById(R.id.spinnerville);
        villeadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, villearray);
        villedropdown.setAdapter(villeadapter);
        typedebiendropdown.setTitle("Choisir type de bien");
        typedebiendropdown.setPositiveButton("fermer");
        etagedropdown.setTitle("Choisir etage");
        etagedropdown.setPositiveButton("fermer");
        nbchambredropdown.setTitle("Choisir nombre de chambres");
        nbchambredropdown.setPositiveButton("fermer");
        regiondropdown.setTitle("Choisir region");
        regiondropdown.setPositiveButton("fermer");
        villedropdown.setTitle("Choisir ville");
        villedropdown.setPositiveButton("fermer");
        databaseReference = FirebaseDatabase.getInstance().getReference("demandedelocation");
        numteltxt = findViewById(R.id.editTextnumtel);
        nomtxt = findViewById(R.id.editTextnom);
        prixtxt = findViewById(R.id.editTextprix);
        remarquetxt = findViewById(R.id.editTextremarque);
        if (PhonecallReceiver.fromDialog==true){
            numteltxt.setText(PhonecallReceiver.EndNumber);

        }
        UserDAO dao = new UserDAO(this);
        for (User user:
                dao.GetAllUser()) {
            currentuser=user;
            break;
        }
    }
    public void clickback(View view) {
        Intent intent = new Intent(ajouterDemandeLocation.this, ajouterdemandeoffer.class);
        startActivity(intent);
    }
    private void ajouterdemandedelocation() {
        String numtel = numteltxt.getText().toString().trim();
        String nom = nomtxt.getText().toString().trim();
        String typebien = String.valueOf(typedebiendropdown.getSelectedItem());
        String nbchambre = String.valueOf(nbchambredropdown.getSelectedItem());
        String nbetage = String.valueOf(etagedropdown.getSelectedItem());
        String region = String.valueOf(regiondropdown.getSelectedItem());
        String ville = String.valueOf(villedropdown.getSelectedItem());
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
        demandedelocation offreLocation = new demandedelocation(id, numtel, nom,typebien,nbchambre,nbetage,region,ville,prix,remarque,currentDateTimeString,username);
        databaseReference.child(id).setValue(offreLocation);

        Toast.makeText(ajouterDemandeLocation.this,
                "une demande de location est ajouteé ",
                Toast.LENGTH_SHORT).show();
        }else {
            demandedelocation demandelocation = new demandedelocation(numtel, nom,typebien,nbchambre,nbetage,region,ville,prix,remarque,currentDateTimeString,username);
            DemandeLocationDAO dao = new DemandeLocationDAO(ajouterDemandeLocation.this);

            long idInsert = dao.insertDemandeLocation(demandelocation);
            Toast.makeText(ajouterDemandeLocation.this, "une demande de location est ajouteé (HORS_LIGNE) ",Toast.LENGTH_SHORT).show();
        }
        if (PhonecallReceiver.fromDialog==true){
            PhonecallReceiver.fromDialog=false;
            finishAndRemoveTask();
        }else {

            Intent myintent = new Intent(ajouterDemandeLocation.this, ajouterdemandeoffer.class);
            startActivity(myintent);

        }



    }
    public void clickconfirmerajouterdemandedelocation (View view) {
        ajouterdemandedelocation();

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
