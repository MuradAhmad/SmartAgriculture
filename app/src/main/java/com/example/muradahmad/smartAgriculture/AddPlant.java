package com.example.muradahmad.smartAgriculture;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by muradahmad on 15/10/2018.
 */

public class AddPlant extends Fragment {

    Database handler;
    SQLiteDatabase db;
    Cursor cursor;

    private String plantName;


    private ArrayList<String> plantslist = new ArrayList<String>();

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_plant, container, false);



       // Button btnAdd = (Button)view.findViewById(R.id.btnAdd);


        handler = new Database(getContext());
        db = handler.getReadableDatabase();


        // get the listview
        expListView = view.findViewById(R.id.lvExpandable);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(getContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
               // Toast.makeText(getContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub


                plantName = (listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition));
                save(plantName);
                Toast.makeText(
                        getContext(),
                        "Saved in Database: "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

               /* Toast.makeText(
                        getContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/

                return false;
            }
        });

   /*     btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //do something
               //list.remove(position); //or some other task
               // notifyDataSetChanged();
            }
        });*/

        return view;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Plants");
        listDataHeader.add("Flowers");
        listDataHeader.add("Low light plants");

        // Adding child data
        List<String> plants = new ArrayList<String>();
        plants.add("Areca Palm");
        plants.add("English Ivy");
        plants.add("Aloe Vera");
        plants.add("Indian Basil");
        plants.add("Dracaena");
        plants.add("Spider Plant");
        plants.add("Snake plant");




        List<String> flowers = new ArrayList<String>();
        flowers.add("Azalea");
        flowers.add("Ladies’ Slipper Orchid");
        flowers.add("Madagascar Jasmine");
        flowers.add("Poison Primrose");


        List<String> lowlightplants = new ArrayList<String>();
        lowlightplants.add("Corn Plant");
        lowlightplants.add("Dragon Tree");
        lowlightplants.add("Cast Iron Plant");
        lowlightplants.add("Mother In Laws Tongue");
        lowlightplants.add("Parlor Palm");



        listDataChild.put(listDataHeader.get(0), plants); // Header, Child data
        listDataChild.put(listDataHeader.get(1), flowers);
        listDataChild.put(listDataHeader.get(2), lowlightplants);


    }


    public void save(String plantName) {
        String time = new SimpleDateFormat("dd-MM-yyyy, hh:mm:ss").format(new Date());

            ContentValues values = new ContentValues();

           values.put(Database.PLANT_NAME, plantName);

            values.put(Database.TIMESTAMP, time);
            //values.put(DBContract.RuuvitagDB.COLUMN_VALUES, "-500,-500,-500,-500,-500,-500,-500,-500");
        db.insert(Database.PLANTS_TABLE, null, values);

        Log.d("Saved in DB", plantName);

            //long newRowId = db.insert(Database.PLANTS_TABLE, null, values);

    }
}
