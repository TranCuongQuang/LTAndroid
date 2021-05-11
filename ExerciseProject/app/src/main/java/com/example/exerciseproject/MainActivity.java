package com.example.exerciseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        final GridView grid = (GridView) findViewById(R.id.grid);
        List<Person> personList = getListData();
        CustomGridAdapter adapter = new CustomGridAdapter(this, R.layout.custom_row9, personList);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> container, View v, int position, long id) {
                Object o = grid.getItemAtPosition(position);
                Person person = (Person) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + person.getName(), Toast.LENGTH_SHORT).show();

                Intent callMainActivityChannel = new Intent(MainActivity.this, MainActivityChannel.class);
//                Bundle myData = new Bundle();
//                myData.putString("Name",  person.getName().toString());
//                callMainActivityChannel.putExtras(myData);
                startActivity(callMainActivityChannel);
            }
        });

    }

    private List<Person> getListData() {
        List<Person> list = new ArrayList<Person>();

        Person p1 = new Person("VnExpress","vnexpress");
        Person p2 = new Person("DanTri","dantri");
        Person p3 = new Person("ThanhNien","thanhnien");

        list.add(p1);
        list.add(p2);
        list.add(p3);

        return list;
    }
}
