package com.example.muradahmad.smartAgriculture;

import android.content.Intent;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);


        btnSelectSensor = view.findViewById(R.id.btnSensor);


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



}
