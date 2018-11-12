package com.example.muradahmad.smartAgriculture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by muradahmad on 08/11/2018.
 */

public class AddTagAdapter extends ArrayAdapter<RuuviTag> {
    private List<RuuviTag> tags;

    public AddTagAdapter(@NonNull Context context, List<RuuviTag> tags) {
        super(context, 0, tags);
        this.tags = tags;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final RuuviTag tag = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_add, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.address)).setText(tag.getId());


        return convertView;
    }
}