package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivityChannel extends Activity {
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;

    String[][] vnExpressUrlCaptionMenu = {
            {"https://vnexpress.net/rss/the-thao.rss", "Thể thao"},
            {"https://vnexpress.net/rss/phap-luat.rss", "Pháp luật"},
            {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
            {"https://vnexpress.net/rss/suc-khoe.rss", "Sức khỏe"},
            {"https://vnexpress.net/rss/gia-dinh.rss", "Đời sống"},
            {"https://vnexpress.net/rss/du-lich.rss", "Du lịch"},
            {"https://vnexpress.net/rss/khoa-hoc.rss", "Khoa học"},
            {"https://vnexpress.net/rss/oto-xe-may.rss", "Xe"},
            {"https://vnexpress.net/rss/thoi-su.rss", "Thời sự"}
    };

    String[] vnExpressUrlCaption = new String[vnExpressUrlCaptionMenu.length];
    String[] vnExpressUrlAddress = new String[vnExpressUrlCaptionMenu.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        for (int i = 0; i < vnExpressUrlAddress.length; i++) {
            vnExpressUrlAddress[i] = vnExpressUrlCaptionMenu[i][0];
            vnExpressUrlCaption[i] = vnExpressUrlCaptionMenu[i][1];
        }
        context = getApplicationContext();
        this.setTitle("NPR Headline News\n" + niceDate());

        myMainListView = (ListView)this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                String urlAddress = vnExpressUrlAddress[_index];
                String urlCaption = vnExpressUrlCaption[_index];

                Intent callShowHeadlines = new Intent(MainActivityChannel.this, ShowHeadlines.class);
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });

        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vnExpressUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);

    }

    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy ", Locale.US);
        return sdf.format(new Date());
    }
}
