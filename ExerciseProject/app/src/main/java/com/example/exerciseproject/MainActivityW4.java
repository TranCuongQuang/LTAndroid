package com.example.exerciseproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityW4 extends ListActivity {

    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        List<Person> personList = getListData();
        CustomIconLabelAdapter adapter = new CustomIconLabelAdapter(this, R.layout.custom_row, personList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Object o = l.getItemAtPosition(position);
        Person person = (Person) o;
        String text = "You choose: " + person.getName();
        txtMsg.setText(text);
    }

    private List<Person> getListData() {
        List<Person> list = new ArrayList<Person>();

        Person p1 = new Person("Nguyễn Văn A", "icon_1", "0968671711");
        Person p2 = new Person("Nguyễn Văn B", "icon_2", "0968671712");
        Person p3 = new Person("Nguyễn Văn C", "icon_3", "0968671713");
        Person p4 = new Person("Nguyễn Văn D", "icon_4", "0968671714");
        Person p5 = new Person("Nguyễn Văn E", "icon_5", "0968671715");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        return list;
    }
}
