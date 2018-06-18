package com.example.sandun.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class IncomingCallReceiver extends BroadcastReceiver {
    String incomingNumber ="";
    AudioManager audioManager;
    TelephonyManager telephonyManager;
    /*String smsBody = "Sample message";*/
    myDbAdapter helper;


    @Override
    public void onReceive(Context context, Intent intent){

        //Get AudioManager
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        //Get TelephonyManger
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        helper = new myDbAdapter(context.getApplicationContext());


        if(intent.getAction().equals("android.intent.action.PHONE_STATE")){
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                //Get incoming number
                incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Calendar c = Calendar.getInstance();

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                //Turn on the mute
                audioManager.setStreamMute(AudioManager.STREAM_RING, true);

                //reject the call
                rejectCall();

                //send the rejected message to app
                sendSMS(context, incomingNumber, formattedDate);

                //save the number and time to db
                /*saveNumber(context, incomingNumber, formattedDate);*/

                /*helper.insertNumber(incomingNumber,formattedDate);*/

            }
        }

    }

    private  void sendSMS(Context context, String number, String date){
        /*Intent intent = new Intent(context, fetchingNumbers.class);
        intent.setFlags(Intent. FLAG_ACTIVITY_NEW_TASK  );*/

        String sentcode = helper.getRandomCode().toString();
        String smsBody = "Thank you for calling..!\n Code   " + sentcode +  "\n";


        try {

            saveNumber(context, incomingNumber, date, sentcode);

            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,
                    null,
                    smsBody,
                    null,
                    null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }




        /*intent.putExtra("number", "Rejected incoming number: "+number+ "  on "+date);
        intent.putExtra(EXTRA_MESSAGE, number);
        intent.putExtra(EXTRA_MESSAGE, date);

        context.startActivity(intent);*/


    }

    private  void saveNumber(Context context, String number, String date, String code){
        /*Intent intent = new Intent(context, fetchingNumbers.class);
        intent.setFlags(Intent. FLAG_ACTIVITY_NEW_TASK  );*/

        long id = helper.insertNumber(number,date,code);

    }

    private  void rejectCall() {
        try {
            //Get the ITelephony() method
            Class<?> classTelephony = Class.forName(telephonyManager.getClass().getName());
            Method method = classTelephony.getDeclaredMethod("getITelephony");


            method.setAccessible(true);

            // Invoke getITelephony() to get the ITelephony interface
            Object telephonyInterface = method.invoke(telephonyManager);

            //Get the endCall method from ITelephony
            Class<?> telephonyInterfaceClass = Class.forName(telephonyInterface.getClass().getName());
            Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");

            //Invoke endCall
            methodEndCall.invoke(telephonyInterface);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}


