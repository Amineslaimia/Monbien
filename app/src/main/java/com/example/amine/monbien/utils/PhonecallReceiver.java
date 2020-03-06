package com.example.amine.monbien.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.amine.monbien.activities.login;

import java.util.Date;

import static com.example.amine.monbien.activities.login.PREFS_USER;

public abstract class PhonecallReceiver extends BroadcastReceiver {
    private static final String TAG ="broadcast_intent";
    public static String incoming_number,firstnumber,secondnumber;
    private String current_state,previus_state,event;
    public static Boolean dialog= false;
    private Context context;
    private SharedPreferences sp,sp1;
    private SharedPreferences.Editor spEditor,spEditor1;
    public static String EndNumber;
    public static boolean fromDialog = false;
    public void onReceive(Context context, Intent intent) {
        //Log.d("intent_log", "Intent" + intent);
        dialog = true;
        this.context = context;
        event = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        incoming_number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.d(TAG, "The received event : " + event + ", incoming_number : " + incoming_number);
        if (event != null) {
            previus_state = getCallState(context);
            current_state = "IDLE";
            if (incoming_number != null) {
                updateIncomingNumber(incoming_number, context);
            } else {
                incoming_number = getIncomingNumber(context);
            }
            switch (event) {
                case "RINGING":
                    Log.d(TAG, "State : Ringing, incoming_number : " + incoming_number);
                    if ((previus_state.equals("IDLE")) || (previus_state.equals("FIRST_CALL_RINGING"))) {
                        current_state = "FIRST_CALL_RINGING";
                    }
                    if ((previus_state.equals("FIRST_CALL_OFFHOOK")) || (previus_state.equals("SECOND_CALL_RINGING"))) {
                        current_state = "SECOND_CALL_RINGING";
                    }

                    break;
                case "OFFHOOK":
                    Log.d(TAG, "State : offhook, incoming_number : " + incoming_number);
                    if ((previus_state.equals("FIRST_CALL_RINGING")) || (previus_state.equals("FIRST_CALL_OFFHOOK"))) {
                        current_state = "FIRST_CALL_OFFHOOK";
                        firstnumber = incoming_number;
                        Log.d(TAG, "State : firstoffhook, incoming_number : " + firstnumber);
                    }
                    if ((previus_state.equals("SECOND_CALL_RINGING"))|| (previus_state.equals("SECOND_CALL_OFFHOOK"))) {
                        current_state = "SECOND_CALL_OFFHOOK";
                        secondnumber=incoming_number;
                        Log.d(TAG, "State : secondoffhook, incoming_number : " + secondnumber);

                    }
                    break;
                case "IDLE":
                    Log.d(TAG, "State : idle and  incoming_number : " + incoming_number);
                    if ((previus_state.equals("FIRST_CALL_OFFHOOK"))||(previus_state.equals("SECOND_CALL_RINGING"))) {
                        current_state = "IDLE";
                        EndNumber = firstnumber;
                        Log.d(TAG, "starting dialog with the number: " + EndNumber);
                        startDialog(context);
                    }
                    if (previus_state.equals("SECOND_CALL_OFFHOOK")) {
                        current_state = "IDLE";
                        EndNumber = firstnumber+secondnumber;
                        Log.d(TAG, "starting dialog with the number: " + EndNumber);
                        startDialog(context);


                    }
                    updateIncomingNumber("no_number", context);
                    Log.d(TAG, "stored incoming number flushed");
                    break;
            }
            if (!current_state.equals(previus_state)) {
                Log.d(TAG, "Updating  state from " + previus_state + " to " + current_state);
                updateCallState(current_state, context);

            }
        }
    }
    public void startDialog(Context context) {
        Log.d(TAG,"Starting Dialog box");
        fromDialog = true;



        final SharedPreferences preferences= context.getSharedPreferences(PREFS_USER,context.MODE_PRIVATE);
        String userConnections =preferences.getString("Username",null);
        String userConnections1 =preferences.getString("Password",null);
        if(userConnections!=null && userConnections1!=null) {
            Intent trIntent = new Intent("android.intent.action.MAIN");
            trIntent.setClass(context, YourDialog.class);
            trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(trIntent);
        }else {
            Intent trIntent = new Intent("android.intent.action.MAIN");
            trIntent.setClass(context, login.class);
            trIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(trIntent);
        }
    }
    public void updateCallState(String state,Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        spEditor = sp.edit();
        spEditor.putString("call_state", state);
        spEditor.commit();
        Log.d(TAG, "state updated");

    }
    public void updateIncomingNumber(String inc_num,Context context){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        spEditor = sp.edit();
        spEditor.putString("inc_num", inc_num);
        spEditor.commit();
        Log.d(TAG, "incoming number updated");
    }
    public String getCallState(Context context){
        sp1 = PreferenceManager.getDefaultSharedPreferences(context);
        String st =sp1.getString("call_state", "IDLE");
        Log.d(TAG,"get previous state as :"+st);
        return st;
    }
    public String getIncomingNumber(Context context){
        sp1 = PreferenceManager.getDefaultSharedPreferences(context);
        String st =sp1.getString("inc_num", "no_num");
        Log.d(TAG,"get incoming number as :"+st);
        return st;
    }


    //Derived classes should override these to respond to specific events of interest
    protected abstract void onIncomingCallStarted(Context ctx, String number, Date start);
    protected abstract void onOutgoingCallStarted(Context ctx, String number, Date start);
    protected abstract void onIncomingCallEnded(Context ctx, String number, Date start, Date end);

    protected abstract void onOutgoingCallEnded(Context ctx, String number, Date start, Date end);
    protected abstract void onMissedCall(Context ctx, String number, Date start);


}