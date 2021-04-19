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

public class FragmentRightW5 extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView txtId;
    TextView txtName;
    TextView txtClass;
    TextView txtPoint;
    Button btnFirst;
    Button btnPrevious;
    Button btnNext;
    Button btnLast;

    private int index = 0;
    private int len = 4;

    public static FragmentRightW5 newInstance(int index) {
        FragmentRightW5 fragment = new FragmentRightW5();

        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        //bundle.putInt("len", len);
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

        try {
            Bundle arguments = getArguments();
            index = arguments.getInt("index");
            len = 4;
        } catch (Exception e) {

        }


        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, 0, len);
            }
        });

        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RIGHT-FRAG", null, len, len);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != 0) {
                    index = index - 1;
                }
                main.onMsgFromFragToMain("RIGHT-FRAG", null, index, len);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (index != len) {
                    index = index + 1;
                }
                main.onMsgFromFragToMain("RIGHT-FRAG", null, index, len);
            }
        });
        return view_layout;
    }

    @Override
    public void onMsgFromMainToFragment(String sender, Person strValue, int index, int len) {
        txtId.setText(strValue.getId());
        txtName.setText(strValue.getName());
        txtClass.setText(strValue.getClassRoom());
        txtPoint.setText(strValue.getPoint() + len + "");
        newInstance(index);
    }
}
