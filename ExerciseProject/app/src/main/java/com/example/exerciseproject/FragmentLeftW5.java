package com.example.exerciseproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragmentLeftW5 extends Fragment implements FragmentCallbacks {
    private static int save = -1;
    MainActivity main;
    Context context = null;
    private ListView listView;
    private List<Person> personList;
    private static int index = 0;
    SQLiteDatabase db;

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

        File storagePath = main.getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "school";

        try {
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            Cursor c1 = db.rawQuery("select l.TenLop, h.MaHS, h.Diem, h.TenHS from tblLop as l inner join tblHocSinh as h on h.MaLop = l.MaLop ", null);
            c1.moveToPosition(-1);
            int mahs = c1.getColumnIndex("MaHS");
            int tenHS = c1.getColumnIndex("TenHS");
            int diem = c1.getColumnIndex("Diem");
            int tenlop = c1.getColumnIndex("TenLop");
            while (c1.moveToNext()) {
                Person p = new Person(Integer.toString((c1.getInt(mahs))), c1.getString(tenHS), "icon_1", c1.getString(tenlop), Float.parseFloat(String.valueOf(c1.getInt(diem))));
                list.add(p);
            }
            listView.performItemClick(listView.getChildAt(0), 0, 0);
            db.close();
        } catch (SQLiteException e) {
        }

//        Person p1 = new Person("A01", "Nguyễn Văn A", "icon_1", "A1", 10);
//        Person p2 = new Person("A02", "Nguyễn Văn B", "icon_2", "A2", 9);
//        Person p3 = new Person("A03", "Nguyễn Văn C", "icon_3", "A3", 8);
//        Person p4 = new Person("A04", "Nguyễn Văn D", "icon_4", "A4", 7);
//        Person p5 = new Person("A05", "Nguyễn Văn E", "icon_5", "A5", 6);
//
//        list.add(p1);
//        list.add(p2);
//        list.add(p3);
//        list.add(p4);
//        list.add(p5);
//
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout_left = (LinearLayout) inflater.inflate(R.layout.fragment_left, null);

        final TextView txtMsg = (TextView) layout_left.findViewById(R.id.txtMsg);
        listView = (ListView) layout_left.findViewById(R.id.list);

        personList = getListData();

        CustomIconLabelAdapterW5 adapter = new CustomIconLabelAdapterW5(context, R.layout.custom_row5, personList);
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

                parent.getChildAt(position).setBackgroundColor(Color.BLUE);
                if (save != -1 && save != position) {
                    parent.getChildAt(save).setBackgroundColor(Color.WHITE);
                }
                save = position;

            }
        });

        Runnable r = new Runnable() {
            @Override
            public void run() {
                listView.performItemClick(listView.getSelectedView(), 0, 0);
            }
        };
        listView.postDelayed(r, 100);

        return layout_left;
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