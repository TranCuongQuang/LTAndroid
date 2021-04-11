package com.example.exerciseproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentLeftW5 extends Fragment {
    MainActivity main;
    Context context = null;
    String message = "";
    private String items[] = {"Text-on-Line-00", "Text-on-Line-00", "Text-on-Line-00"};

    public static FragmentLeftW5 newInstance(String strArg) {
        FragmentLeftW5 fragment = new FragmentLeftW5();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    private List<Person> getListData() {
        List<Person> list = new ArrayList<Person>();

        Person p1 = new Person("A01", "Nguyễn Văn A", "icon_1", "A1", 10);
        Person p2 = new Person("A02", "Nguyễn Văn B", "icon_2", "A2", 9);
        Person p3 = new Person("A03", "Nguyễn Văn C", "icon_3", "A3", 8);
        Person p4 = new Person("A04", "Nguyễn Văn D", "icon_4", "A4", 7);
        Person p5 = new Person("A05", "Nguyễn Văn E", "icon_5", "A5", 6);

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout_left = (LinearLayout) inflater.inflate(R.layout.fragment_left, null);

        final TextView txtMsg = (TextView) layout_left.findViewById(R.id.txtMsg);
        final ListView listView = (ListView) layout_left.findViewById(R.id.list);

        List<Person> personList = getListData();
        CustomIconLabelAdapterW5 adapter = new CustomIconLabelAdapterW5(context, R.layout.custom_row5, personList);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

//        listView.setBackgroundColor(Color.parseColor("#ffccddff"));
        listView.setSelection(0);
        listView.smoothScrollToPosition(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Person person = (Person) o;
                String text = "Mã số: " + person.getId();

                txtMsg.setText(text);
                main.onMsgFromFragToMain("LEFT-FRAG", person);
            }
        });
        return layout_left;
    }
}