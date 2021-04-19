package com.example.exerciseproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentLeftW5 extends Fragment implements FragmentCallbacks {
    //    private static int save = -1;
    MainActivity main;
    Context context = null;
    private ListView listView;
    private List<Person> personList;
    private static int index = 0;

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
        listView = (ListView) layout_left.findViewById(R.id.list);

        personList = getListData();
        final CustomIconLabelAdapterW5 adapter = new CustomIconLabelAdapterW5(context, R.layout.custom_row5, personList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                index = position;

                Object o = listView.getItemAtPosition(position);
                Person person = (Person) o;
                String text = "Mã số: " + person.getId();

                txtMsg.setText(text);
                main.onMsgFromFragToMain("LEFT-FRAG", person, null);

//                if (save != -1 && save != position) {
//                    parent.getChildAt(save).setBackgroundColor(Color.WHITE);
//                } else {
//                    parent.getChildAt(save).setBackgroundColor(Color.BLUE);
//                }
//                save = position;

            }
        });
        return layout_left;
    }

    public void onStart() {
        super.onStart();

        listView.performItemClick(listView.getSelectedView(), 0, 0);
    }

    @Override
    public void onMsgFromMainToFragment(String sender, Person person, String action) {
        int len = personList.size() - 1;
        if (action.equals("first")) {
            this.index = 0;
        } else if (action.equals("last")) {
            this.index = len;
        } else if (action.equals("previous")) {
            if (this.index != 0) {
                this.index = this.index - 1;
            }
        } else if (action.equals("next")) {
            if (this.index != len) {
                this.index = this.index + 1;
            }
        }

        listView.performItemClick(listView.getSelectedView(), index, 0);
    }
}