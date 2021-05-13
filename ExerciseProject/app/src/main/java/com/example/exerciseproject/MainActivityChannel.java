package com.example.exerciseproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivityChannel extends Activity {
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    String Name;
    String Logo;

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

    String[][] _24hUrlCaptionMenu = {
            {"https://cdn.24h.com.vn/upload/rss/bongda.rss", "Bóng đá"},
            {"https://cdn.24h.com.vn/upload/rss/thoitrang.rss", "Thời trang"},
            {"https://cdn.24h.com.vn/upload/rss/amthuc.rss", "Ẩm thực"},
            {"https://cdn.24h.com.vn/upload/rss/lamdep.rss", "Làm đẹp"},
            {"https://cdn.24h.com.vn/upload/rss/congnghethongtin.rss", "Công nghệ thông tin"},
            {"https://cdn.24h.com.vn/upload/rss/giaoducduhoc.rss", "Giáo dục"},
            {"https://cdn.24h.com.vn/upload/rss/thethao.rss", "Thể thao"},
            {"https://cdn.24h.com.vn/upload/rss/dulich24h.rss", "Du lịch"},
            {"https://cdn.24h.com.vn/upload/rss/cuoi24h.rss", "Cười 24h"}
    };

    String[][] thanhNienUrlCaptionMenu = {
            {"https://thanhnien.vn/rss/thoi-su.rss", "Thời sự"},
            {"https://thanhnien.vn/rss/the-gioi.rss", "Thế giới"},
            {"https://thanhnien.vn/rss/van-hoa.rss", "Văn hóa"},
            {"https://thanhnien.vn/rss/giai-tri.rss", "Giải trí"},
            {"https://thethao.thanhnien.vn/rss/home.rss", "Thể thao"},
            {"https://thanhnien.vn/rss/doi-song.rss", "Đời sống"},
            {"https://thanhnien.vn/rss/tai-chinh-kinh-doanh.rss", "Tài chính"},
            {"https://thanhnien.vn/rss/gioi-tre.rss", "Giới trẻ"},
            {"https://thanhnien.vn/rss/giao-duc.rss", "Giáo dục"},
    };

    String[][] dataUrlCaptionMenu;
    String[] dataUrlCaption;
    String[] dataUrlAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
         Name = myBundle.getString("Name");
         Logo = myBundle.getString("Logo");

        int imageId = this.getDrawableResIdByName(Logo);
        ImageView imgLogo = (ImageView)this.findViewById(R.id.imgLogoChange);
        imgLogo.setImageResource(imageId);

        if(Name.equals("VnExpress")){
            dataUrlCaptionMenu = vnExpressUrlCaptionMenu;
            dataUrlCaption = new String[dataUrlCaptionMenu.length];
            dataUrlAddress = new String[dataUrlCaptionMenu.length];
        }else if(Name.equals("24h")){
            dataUrlCaptionMenu = _24hUrlCaptionMenu;
            dataUrlCaption = new String[dataUrlCaptionMenu.length];
            dataUrlAddress = new String[dataUrlCaptionMenu.length];
        }else if(Name.equals("ThanhNien")){
            dataUrlCaptionMenu = thanhNienUrlCaptionMenu;
            dataUrlCaption = new String[dataUrlCaptionMenu.length];
            dataUrlAddress = new String[dataUrlCaptionMenu.length];
        }

        for (int i = 0; i < dataUrlAddress.length; i++) {
            dataUrlAddress[i] = dataUrlCaptionMenu[i][0];
            dataUrlCaption[i] = dataUrlCaptionMenu[i][1];
        }

        context = getApplicationContext();
        this.setTitle(Name +" News\n" + niceDate());

        myMainListView = (ListView)this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                String urlAddress = dataUrlAddress[_index];
                String urlCaption = dataUrlCaption[_index];

                Intent callShowHeadlines = new Intent(MainActivityChannel.this, ShowHeadlines.class);
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress);
                myData.putString("urlCaption", urlCaption);
                myData.putString("Logo", Logo);
                myData.putString("Name", Name);

                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);
            }
        });

        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);
    }

    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy ", Locale.US);
        return sdf.format(new Date());
    }

    public int getDrawableResIdByName(String resName)  {
        String pkgName = getPackageName();
        int resID = getResources().getIdentifier(resName , "drawable", pkgName);
        return resID;
    }
}
