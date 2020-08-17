package com.example.easyship.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyship.R;
import com.example.easyship.models.uidesign.SpinnerItem;

import java.util.ArrayList;

public class SpinnerItemAdapter extends ArrayAdapter<SpinnerItem> {
    public SpinnerItemAdapter(Context context, ArrayList<SpinnerItem> itemList){
        super(context, 0, itemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item, parent, false
            );
        }

        ImageView image = convertView.findViewById(R.id.spinner_item_image);
        TextView text = convertView.findViewById(R.id.spinner_item_text);

        SpinnerItem currentItem = getItem(position);

        if(currentItem != null){
            image.setImageResource(currentItem.getImage());
            text.setText(currentItem.getName());
        }

        return convertView;
    }
}
