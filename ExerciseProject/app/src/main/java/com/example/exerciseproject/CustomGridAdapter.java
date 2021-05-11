package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomGridAdapter extends ArrayAdapter {
    Context context;
    private List<Person> listData;

    public CustomGridAdapter(Context context, int layoutToBeInflated, List<Person> listData) {
        super(context, R.layout.custom_row9, listData);
        this.context = context;
        this.listData = listData;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomGridAdapter.ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.custom_row9, null);
            holder = new CustomGridAdapter.ViewHolder();
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            convertView.setTag(holder);
        } else {
            holder = (CustomGridAdapter.ViewHolder) convertView.getTag();
        }

        Person person = this.listData.get(position);

        int imageId = this.getDrawableResIdByName(person.getAvatar());
        holder.imgAvatar.setImageResource(imageId);

        return convertView;
    }

    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView imgAvatar;
    }

}
