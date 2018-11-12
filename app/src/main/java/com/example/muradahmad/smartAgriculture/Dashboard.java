package com.example.muradahmad.smartAgriculture;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by muradahmad on 07/08/2018.
 */

public class Dashboard extends Fragment {


    private class LeScanResult {
        BluetoothDevice device;
        int rssi;
        byte[] scanData;
    }






    private ArrayList<LeScanResult> scanResults;

    private BluetoothAdapter bluetoothAdapter;
    private boolean scanning;


    private ScheduledExecutorService scheduler;
    private ScheduledExecutorService alertScheduler;

    SharedPreferences settings;

    RuuviTag ruuviTag;


    Database handler;
    SQLiteDatabase db;
    Cursor cursor;

    private Timer timer;
    private Handler scanTimerHandler;
    private static int MAX_SCAN_TIME_MS = 1000;




    private BeaconManager beaconManager = null;

    private Region beaconRegion = null;




    TextView txtTemperature, txtHumidity, txtDeviceId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_dashboard, container,false);


        txtTemperature = view.findViewById(R.id.txtTemperature);
        txtDeviceId = view.findViewById(R.id.txtDeviceId);
        txtHumidity = view.findViewById(R.id.txtHumidity);






        handler = new Database(getContext());
        db = handler.getReadableDatabase();



        final Handler threadHandler = new Handler();
        Runnable runnable = new Runnable()
         {
            public void run() {
                //Whatever task you wish to perform
                //For eg. textView.setText("SOME TEXT")
                cursor = db.rawQuery("SELECT * FROM " + Database.DEVICE_TABLE +
                                " ORDER BY "+Database.DATE + " DESC"
                        , new String[] {});

                if (cursor != null) {
                    cursor.moveToFirst();

                    if (cursor.getCount() > 0) {
// get values from cursor here


                        String deviceId = cursor.getString(cursor.getColumnIndex(Database.DEVICE_ID));
                        String temperature = cursor.getString(cursor.getColumnIndex(Database.TEMPERATURE));
                        String humidity = cursor.getString(cursor.getColumnIndex(Database.HUMIDITY));

                        txtDeviceId.setText(deviceId);
                        txtTemperature.setText(temperature + " °C ");
                        txtHumidity.setText(humidity + " % ");

                        Log.d("Device ID from Main: ", deviceId);
                        Log.d("Temperature from Main: ", temperature);
                        Log.d("Humidity from Main: ", humidity);
                    }
                }
                threadHandler.postDelayed(this, 1000);
            }
        };

        threadHandler.postDelayed(runnable, 1000);



        return view;

    }
}
