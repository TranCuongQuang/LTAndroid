package com.example.exerciseproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class FragmentRightW5 extends Fragment implements FragmentCallbacks {
    MainActivityW5 main;
    TextView txtId;
    TextView txtName;
    TextView txtClass;
    TextView txtPoint;
    Button btnFirst;
    Button btnPrevious;
    Button btnNext;
    Button btnLast;

    public static FragmentRightW5 newInstance(int index) {
        FragmentRightW5 fragment = new FragmentRightW5();

        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivityW5) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout = (LinearLayout) inflater.inflate(R.layout.fragment_right, null);
        txtId = (TextView) view_layout.findViewById(R.id.txtId);
        txtClass = (TextView) view_layout.findViewById(R.id.txtClass);
        txtName = (TextView) view_layout.findViewById(R.id.txtName);
        txtPoint = (TextView) view_layout.findViewById(R.id.txtPoint);

        btnFirst = (Button) view_layout.findViewById(R.id.btnFirst);
        btnPrevious = (Button) view_layout.findViewById(R.id.btnPrevious);
        btnNext = (Button) view_layout.findViewById(R.id.btnNext);
        btnLast = (Button) view_layout.findViewById(R.id.btnLast);


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, "first");
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, "last");
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, "previous");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, "next");
            }
        });
        return view_layout;
    }

    @Override
    public void onMsgFromMainToFragment(String sender, Person person, String action) {
        txtId.setText(person.getId());
        txtName.setText(person.getName());
        txtClass.setText(person.getClassRoom());
        txtPoint.setText(person.getPoint() + "");
    }
}
