package com.example.muradahmad.smartAgriculture;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by muradahmad on 07/11/2018.
 */

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    Database handler;
    SQLiteDatabase db;

    Plants plantsObject;

    public CustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;

    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {

        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {

        return pos;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list, null);
        }


        plantsObject = new Plants();

        handler = new Database(context);
        db = handler.getReadableDatabase();


        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = view.findViewById(R.id.delete_btn);






        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                //do something
               String plantName = list.get(position);

               // plantsObject.deletePlant(plantName);

                //plantsObject.delete(plantName);
                db.delete(Database.PLANTS_TABLE,Database.PLANT_NAME +"=?", new String[] {plantName});


                Log.d("Plant deleted",plantName);

                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        return view;
    }
}