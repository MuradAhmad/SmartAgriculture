package com.example.muradahmad.smartAgriculture;

import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.opencsv.CSVWriter;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by muradahmad on 07/08/2018.
 */

public class Dashboard extends Fragment implements SensorEventListener {


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == sensor.TYPE_LIGHT){
            txtluminous.setText(""+ event.values[0]);
            Log.d("sensor Light",""+ event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private class LeScanResult {
        BluetoothDevice device;
        int rssi;
        byte[] scanData;
    }



    String strTemperature;



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




    String filepath = "";
    private BeaconManager beaconManager = null;

    private Region beaconRegion = null;


    Sensor sensor;
    SensorManager sensorManager;


    TextView txtTemperature, txtHumidity, txtDeviceId,  txtluminous;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_dashboard, container,false);


        txtTemperature = view.findViewById(R.id.txtTemperature);
        txtDeviceId = view.findViewById(R.id.txtDeviceId);
        txtHumidity = view.findViewById(R.id.txtHumidity);
        txtluminous = view.findViewById(R.id.txtlight);



        //File file = new File(filepath);
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");


        try {
            file.createNewFile();

            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            csvWrite.writeNext(cursor.getColumnNames());

        } catch (IOException e) {
            e.printStackTrace();
        }



        sensorManager = (SensorManager) getContext().getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        handler = new Database(getContext());
        db = handler.getReadableDatabase();



        final Handler threadHandler = new Handler();
        Runnable runnable = new Runnable()
         {
            public void run() {
                //Whatever task you wish to perform
                //For eg. textView.setText("SOME TEXT")
                cursor = db.rawQuery("SELECT * FROM " + Database.DEVICE_TABLE,null);

                if (cursor != null) {
                    cursor.moveToFirst();

                    if (cursor.getCount() > 0) {
// get values from cursor here


                        String deviceId = cursor.getString(cursor.getColumnIndex(Database.DEVICE_ID));
                        String temperature = cursor.getString(cursor.getColumnIndex(Database.TEMPERATURE));
                        String humidity = cursor.getString(cursor.getColumnIndex(Database.HUMIDITY));
                        //String time = cursor.getString(cursor.getColumnIndex(Database.DATE));
                        //String light = cursor.getString(cursor.getColumnIndex(Database.LIGHT));


                        txtDeviceId.setText(deviceId);
                        txtTemperature.setText(temperature + " °C ");
                        txtHumidity.setText(humidity + " % ");


                        strTemperature =temperature;

                        Log.d("Device ID from Main: ", deviceId);
                        Log.d("Temperature from Main: ", temperature);
                        Log.d("Humidity from Main: ", humidity);



/*
                        try {



                            FileWriter outputfile = new FileWriter(file);

                            CSVWriter writer = new CSVWriter(outputfile);

                            // Once every minute, fetch values corresponding to this time and write this to the data csv.

                            String[] data = {time, temperature, humidity, light};
                            writer.writeNext(data);
                        }

                        catch (IOException error) {
                            error.printStackTrace();
                            //Log.d("asd", error.printStackTrace());
                        }
*/


                    }
                }
                threadHandler.postDelayed(this, 50000);
            }
        };

        threadHandler.postDelayed(runnable, 60000);



        // send users notifications from here
        // calculate the temperature and humidity for plant
        //




/*
        if(Integer.valueOf(strTemperature) <10.0 || Integer.valueOf(strTemperature) > 35.0) {

            Intent intent = new Intent(getContext(), NotificationReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        }*/

        //Graph
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        graph.getViewport().setScalable(true);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                //make loop to add datapoints
                // for DataPoint in GraphData{
                // int time =
                // int temperature =
                // new DataPoint(time, temperature)
                //
                //new DataPoint(graph_time, graph_temp),
                new DataPoint(0, 1),
                new DataPoint(1, 4),
                new DataPoint(2, 3),
                new DataPoint(3, 5),

        });
        LineGraphSeries<DataPoint> seriesTemperature = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 3),
                new DataPoint(1, 2),
                new DataPoint(2, 4),
                new DataPoint(3, 1),
        });

        //maybe if statement here to determine which graph to show
        seriesTemperature.setColor(Color.RED);
        graph.addSeries(series);
        graph.addSeries(seriesTemperature);


        Button btn = (Button) view.findViewById(R.id.Switch_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //do something with button here
                Log.i("ASD", "button was pressed");

            }
        });

        return view;

    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

    }
}
