package com.example.exerciseproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

    public static FragmentRightW5 newInstance(String strArg1) {
        FragmentRightW5 fragment = new FragmentRightW5();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
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

        LinearLayout view_layout= (LinearLayout) inflater.inflate(R.layout.fragment_right, null);
        txtId = (TextView) view_layout.findViewById(R.id.txtId);
        txtClass = (TextView) view_layout.findViewById(R.id.txtClass);
        txtName = (TextView) view_layout.findViewById(R.id.txtName);
        txtPoint = (TextView) view_layout.findViewById(R.id.txtPoint);

        btnFirst = (Button) view_layout.findViewById(R.id.btnFirst);
        btnPrevious = (Button) view_layout.findViewById(R.id.btnNext);
        btnNext = (Button) view_layout.findViewById(R.id.btnNext);
        btnLast = (Button) view_layout.findViewById(R.id.btnLast);


//        try {
//            Bundle arguments = getArguments();
//            txtId.setText(arguments.getString("arg1", ""));
//        } catch (Exception e) {
//            Log.e("RED BUNDLE ERROR – ", "" + e.getMessage());
//        }

//        btnClick = (Button) view_layout.findViewById(R.id.btnClick);
//        btnClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String redMessage = "Red clock:\n" + new Date().toString();
//                txtId.setText(redMessage);
//                main.onMsgFromFragToMain("RED-FRAG", redMessage);
//            }
//        });
        return view_layout;
    }

    @Override
    public void onMsgFromMainToFragment(String sender, Person strValue) {
        txtId.setText(strValue.getId());
        txtName.setText(strValue.getName());
        txtClass.setText(strValue.getClassRoom());
        txtPoint.setText(strValue.getPoint() + "");
    }
}
