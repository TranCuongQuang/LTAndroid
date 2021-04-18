package com.example.exerciseproject;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.Date;

public class FragmentRightW5 extends Fragment implements FragmentCallbacks, View.OnClickListener {
    MainActivity main;
    TextView txtId;
    TextView txtName;
    TextView txtClass;
    TextView txtPoint;
    Button btnFirst;
    Button btnPrevious;
    Button btnNext;
    Button btnLast;

    public static FragmentRightW5 newInstance(Person strArg1) {
        FragmentRightW5 fragment = new FragmentRightW5();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg1", (Parcelable) strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
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
                main.onMsgFromRightFragToMain("RIGHT-FRAG", "first");
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromRightFragToMain("RIGHT-FRAG", "last");
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromRightFragToMain("RIGHT-FRAG", "previous");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromRightFragToMain("RIGHT-FRAG", "next");
            }
        });
        return view_layout;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnFirst.getId()) {
            Bundle arguments = getArguments();
            Person person = (Person) arguments.getSerializable("arg1");
//            main.onMsgFromFragToMain("RIGHT-FRAG", person);
        }
    }

    @Override
    public void onMsgFromMainToLeftFragment(String sender, String position) {

    }

    @Override
    public void onMsgFromMainToRightFragment(String sender, Person strValue) {
        txtId.setText(strValue.getId());
        txtName.setText(strValue.getName());
        txtClass.setText(strValue.getClassRoom());
        txtPoint.setText(strValue.getPoint() + "");
    }
}
