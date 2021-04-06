package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomIconLabelAdapter extends ArrayAdapter {
    Context context;
    Integer[] thumbnails;
    String[] items;
    String[] phones;

//    public  class Abc {
//        public  String Name ;
//        public String Phone;
//    }

    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] items, String[] phones, Integer[] thumbnails) {
        super(context, R.layout.custom_row, items);
        this.context = context;
        this.thumbnails = thumbnails;
        this.items = items;
        this.phones = phones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_row, null);
        TextView label = (TextView) row.findViewById(R.id.my_custom_textview3);
        TextView phone = (TextView) row.findViewById(R.id.my_custom_textview4);
        ImageView icon = (ImageView) row.findViewById(R.id.icon);
        label.setText(items[position]);
        phone.setText(phones[position]);
        icon.setImageResource(thumbnails[position]);
        return (row);
    }
}
