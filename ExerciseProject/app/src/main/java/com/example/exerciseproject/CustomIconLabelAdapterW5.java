package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomIconLabelAdapterW5 extends ArrayAdapter {
    Context context;

    private List<Person> listData;

    public CustomIconLabelAdapterW5(Context context, int layoutToBeInflated, List<Person> listData) {
        super(context, R.layout.custom_row5, listData);
        this.context = context;
        this.listData = listData;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.custom_row5, null);
            if(position == 0){
                // This is the first item, you need to select this
                convertView.setSelected(true);

            }
            holder = new ViewHolder();
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            holder.txtId = (TextView) convertView.findViewById(R.id.txtId);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            if(position == 0){
                // This is the first item, you need to select this
                convertView.setSelected(true);

            }
        }

        Person person = this.listData.get(position);

        holder.txtId.setText(person.getId());
        int imageId = this.getDrawableResIdByName(person.getAvatar());
        holder.imgAvatar.setImageResource(imageId);

        return convertView;
    }

    public int getDrawableResIdByName(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView imgAvatar;
        TextView txtId;
    }
}
