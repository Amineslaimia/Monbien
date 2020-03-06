package com.example.amine.monbien.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.amine.monbien.R;
import com.example.amine.monbien.activities.ajouterdemandeoffer;

import static java.security.AccessController.getContext;

public class YourDialog extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Context ctx = this;

        final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        alert.setTitle("Monbien");
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.my_alert_dialog, null);
        Button buttonoui = (Button) view.findViewById(R.id.buttonoui);
        TextView numteltxt=(TextView) view.findViewById(R.id.textViewnumtel);
        numteltxt.setText(PhonecallReceiver.EndNumber);
        alert.setView(view);
        alert.setPositiveButton("NON",new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(DialogInterface dialog, int whichButton)
            {
                dialog.cancel();
                finishAndRemoveTask();
                PhonecallReceiver.fromDialog = false;
            }});
        alert.setCancelable(false);



        alert.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    return true; // Consumed
                }
                else {
                    return false; // Not consumed
                }
            }
        });
        alert.create();
        alert.show();







    }




    public void clickoui(View view){

        Intent myintent = new Intent(YourDialog.this, ajouterdemandeoffer.class);
        startActivity(myintent);
        finish();

    }

    @Override
    public void onBackPressed() { }




}
