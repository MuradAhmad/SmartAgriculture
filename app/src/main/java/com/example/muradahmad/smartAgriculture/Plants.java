package com.example.muradahmad.smartAgriculture;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by muradahmad on 15/10/2018.
 */

public class Plants extends Fragment{


    Database handler;
    SQLiteDatabase db;
    Cursor cursor;

    String plantName;
    String testing;


    private ListView listView;

    ArrayList<String> list = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_plants, container, false);



        handler = new Database(getContext());
        db = handler.getReadableDatabase();


                //Whatever task you wish to perform
                //For eg. textView.setText("SOME TEXT")
        cursor = db.rawQuery("SELECT * FROM " + Database.PLANTS_TABLE + " ORDER BY "+Database.TIMESTAMP + " DESC" ,null);



        if(cursor != null) {

            cursor.moveToFirst();

            //if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {

                // get values from cursor here

                String plant = cursor.getString(cursor.getColumnIndex(Database.PLANT_NAME));

                Log.d("From DB", plant);

                //generate list

                list.add(plant);
                cursor.moveToNext();

            }
        }




        //instantiate custom adapter
        CustomAdapter adapter = new CustomAdapter(list, getContext());

        //handle listview and assign adapter
        listView = view.findViewById(R.id.list_viewPlants);
        listView.setAdapter(adapter);



/*

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                                            // TODO Auto-generated method stub
                                            Toast.makeText(getContext(), "selected Item Name is " + listView.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                                        }
                                    }
        );
*/


        return view;
    }

    public void deletePlant(String plant){


        plantName = plant;

        //Log.d("Remove from DBList ", plantName);

        //db.delete(Database.PLANTS_TABLE,Database.PLANT_NAME +"='" + plantName+"'",null );
    }
    public Boolean delete(String plant){

        Log.d("Remove from DBList ", plant);
        return db.delete(Database.PLANTS_TABLE,Database.PLANT_NAME +"=?", new String[] {plant})>0;
    }


}
