package com.example.exerciseproject;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.File;

public class MainActivityW8 extends FragmentActivity implements MainCallbacks {

    //    FragmentTransaction ft;
    FragmentLeftW5 frmLeft;
    FragmentRightW5 frmRight;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
//        ft = getSupportFragmentManager().beginTransaction();
//        frmLeft = frmLeft.newInstance("first-blue");
//        ft.replace(R.id.frmLeft, frmLeft);
//        ft.commit();
//
//        ft = getSupportFragmentManager().beginTransaction();
//        frmRight = FragmentRightW5.newInstance("first-red");
//        ft.replace(R.id.frmRight, frmRight);
//        ft.commit();
    }

    public boolean tableExists(SQLiteDatabase db, String tableName) {
        //true if table exists, false otherwise
        String mySql = "SELECT name FROM sqlite_master " + " WHERE type='table' " + " AND name='" + tableName + "'";
        int resultSize = db.rawQuery(mySql, null).getCount();
        if (resultSize != 0) {
            return true;
        } else {
            return false;
        }
    }

    private void createTableLop() {
        db.beginTransaction();
        try {
            db.execSQL("create table tblLop (MaLop integer PRIMARY KEY autoincrement, TenLop text );");
            db.setTransactionSuccessful();
        } catch (SQLException e1) {
            finish();
        } finally {
            db.endTransaction();
        }
    }

    private void insertLop() {
        db.beginTransaction();
        try {
            db.execSQL("insert into tblLop(TenLop) " + " values ('Lop 1' );");
            db.execSQL("insert into tblLop(TenLop) " + " values ('Lop 2' );");
            db.execSQL("insert into tblLop(TenLop) " + " values ('Lop 3' );");

            db.setTransactionSuccessful();
        } catch (SQLiteException e2) {
        } finally {
            db.endTransaction();
        }
    }

    private void createTableHocSinh() {
        db.beginTransaction();
        try {
            db.execSQL("create table tblHocSinh (MaHS integer PRIMARY KEY autoincrement, TenHS text, Diem float, MaLop integer );");

            db.setTransactionSuccessful();
        } catch (SQLException e1) {
            finish();
        } finally {
            db.endTransaction();
        }
    }

    private void insertHocSinh() {
        db.beginTransaction();
        try {
            db.execSQL("insert into tblHocSinh(TenHS, Diem, MaLop) " + " values ('Nguyen Van A', 10, 1 );");
            db.execSQL("insert into tblHocSinh(TenHS, Diem, MaLop) " + " values ('Nguyen Van B', 9, 1 );");
            db.execSQL("insert into tblHocSinh(TenHS, Diem, MaLop) " + " values ('Nguyen Van C', 8, 2 );");
            db.execSQL("insert into tblHocSinh(TenHS, Diem, MaLop) " + " values ('Nguyen Van D', 7, 3 );");
            db.execSQL("insert into tblHocSinh(TenHS, Diem, MaLop) " + " values ('Nguyen Van E', 6, 3 );");
            db.setTransactionSuccessful();
        } catch (SQLiteException e2) {
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getClass() == FragmentLeftW5.class) {
            frmLeft = (FragmentLeftW5) fragment;
        }
        if (fragment.getClass() == FragmentRightW5.class) {
            frmRight = (FragmentRightW5) fragment;
        }

        File storagePath = getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "school";
        try {
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

            boolean checkTableLop = tableExists(db, "tblLop");
            boolean checkTableHocSinh = tableExists(db, "tblHocSinh");
            if (checkTableLop == false) {
                createTableLop();
                insertLop();
            }
            if (checkTableHocSinh == false) {
                createTableHocSinh();
                insertHocSinh();
            }

            db.close();
        } catch (SQLiteException e) {
        }
    }

    @Override
    public void onMsgFromFragToMain(String sender, Person person, String action) {
//        Toast.makeText(getApplication(), sender +  " : action " + action, Toast.LENGTH_SHORT).show();
        if (sender.equals("RIGHT-FRAG")) {
            frmLeft.onMsgFromMainToFragment(sender, person, action);
        }
        if (sender.equals("LEFT-FRAG")) {
            frmRight.onMsgFromMainToFragment(sender, person, action);
        }
    }
}

