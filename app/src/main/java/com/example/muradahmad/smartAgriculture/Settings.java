package com.example.muradahmad.smartAgriculture;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by muradahmad on 15/10/2018.
 */

public class Settings extends Fragment{

    private Button btnSelectSensor;
    RuuviTag ruuviTag;

    private ListView listView;
    public static final String CHANNEL_1_ID = "channel1";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);


        btnSelectSensor = view.findViewById(R.id.btnSensor);



        createNotificationChannels();


/*

        //instantiate custom adapter
        AddTagAdapter addTagAdapter = new CustomAdapter(getContext(), );

        //handle listview and assign adapter
        listView = (ListView) view.findViewById(R.id.tag_listView);
        listView.setAdapter(addTagAdapter);

        btnSelectSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });*/

        return view;
    }

    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is Channel 1");


            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);


        }
    }

}
