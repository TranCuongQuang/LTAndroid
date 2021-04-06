package com.example.exerciseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends ListActivity {

    TextView txtMsg;
    String[] items = {
            "Nguyễn Văn",
            "Lê Thị B",
            "Trần Văn C",
            "Phan Văn C",
            "Đinh Văn D",
    };
    String[] phones = {
            "0968671710",
            "0968671711",
            "0968671712",
            "0968671713",
            "0968671714",
    };
    Integer[] thumbnails = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5};
    ListView myListView;

//    public class Kwas {
//        String name;
//        String formula;
//
//        public Kwas( String nazwa, String wzor)
//        {
//            name = nazwa;
//            formula = wzor;
//        }
//
//        void setName(String c)
//        {
//            name = c;
//        }
//        void setFormula(String c)
//        {
//            formula = c;
//        }
//        public String getName()
//        {
//            return name;
//        }
//        public String  getFormula() {return formula;}
//    }
//
//    ArrayList<Kwas> list = new ArrayList<Kwas>();
//    Kwas a1 = new Kwas("Kwas Azotowy(V)", "HNO3");
//
//list.add(a1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(this, R.layout.custom_row, items,phones, thumbnails);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String text = "You choose: " + items[position];
        txtMsg.setText(text);
    }
}