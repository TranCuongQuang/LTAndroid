package com.example.exerciseproject;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.io.File;

public class MainActivity extends FragmentActivity implements MainCallbacks {

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
        File storagePath = getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "school";
        try {
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            System.out.println("-------" + storagePath);
            Log.e("-----", myDbPath);
            boolean checkTable = tableExists(db, "lop");
            boolean checkTable1 = tableExists(db, "hocsinh");
//            if (checkTable == false) {
//                insertSomeDbData();
//            }
//            if (checkTable1 == false) {
//                insertHocSinh();
//            }
            useRawQueryShowAll();
            db.close();
        } catch (SQLiteException e) {
        }
    }

    public boolean tableExists(SQLiteDatabase db, String tableName) {
//true if table exists, false otherwise
        String mySql = "SELECT name FROM sqlite_master " + " WHERE type='table' "
                + " AND name='" + tableName + "'";
        int resultSize = db.rawQuery(mySql, null).getCount();
        if (resultSize == 0) {
            return true;
        } else return false;
    }

    private void insertSomeDbData() {
// create table: tblAmigo
        db.beginTransaction();
        try { // create table
            db.execSQL("create table lop (maLop integer PRIMARY KEY autoincrement, TenLop text );");
// commit your changes
            db.setTransactionSuccessful();
        } catch (SQLException e1) {
            finish();
        } finally {
            db.endTransaction();
        }
// populate table: tblAmigo
        db.beginTransaction();
        try { // insert rows
            db.execSQL("insert into lop(TenLop) " + " values ('Lop 1' );");
            db.execSQL("insert into lop(TenLop) " + " values ('Lop 2' );");
            db.execSQL("insert into lop(TenLop) " + " values ('Lop 3' );");
// commit your changes
            db.setTransactionSuccessful();

        } catch (SQLiteException e2) {
        } finally {
            db.endTransaction();
        }
    }// insertSomeData

    private void insertHocSinh() {
// create table: tblAmigo
        db.beginTransaction();
        try { // create table
            db.execSQL("create table hocsinh (maHocSinh integer PRIMARY KEY autoincrement, TenHS text, Diem float, maLop integer );");
// commit your changes
            db.setTransactionSuccessful();
        } catch (SQLException e1) {
            finish();
        } finally {
            db.endTransaction();
        }
// populate table: tblAmigo
        db.beginTransaction();
        try { // insert rows
            db.execSQL("insert into hocsinh(TenHS, Diem, maLop) " + " values ('Nguyen Van A', 10, 1 );");
            db.execSQL("insert into hocsinh(TenHS, Diem, maLop) " + " values ('Nguyen Van B', 9, 1 );");
            db.execSQL("insert into hocsinh(TenHS, Diem, maLop) " + " values ('Nguyen Van C', 8, 2 );");
            db.execSQL("insert into hocsinh(TenHS, Diem, maLop) " + " values ('Nguyen Van D', 7, 3 );");
            db.execSQL("insert into hocsinh(TenHS, Diem, maLop) " + " values ('Nguyen Van E', 6, 3 );");
// commit your changes
            db.setTransactionSuccessful();

        } catch (SQLiteException e2) {
        } finally {
            db.endTransaction();
        }
    }// insertSomeData

    private void useRawQueryShowAll() {
        try { // hard-coded SQL select with no arguments
            Cursor c1 = db.rawQuery("select * from lop", null);

            String tmp = showCursor(c1);
            Log.e("++++++++", tmp);
        } catch (Exception e) {
        }
    }

    private String showCursor(Cursor cursor) { //show SCHEMA (column names & types)
        cursor.moveToPosition(-1); //reset cursor's top
        String cursorData = "\nCursor: [";
        try { // get column names
            String[] colName = cursor.getColumnNames();
            for (int i = 0; i < colName.length; i++) {
                cursorData += colName[i] + getColumnType(cursor, i);
                if (i < colName.length - 1) {
                    cursorData += ", ";
                }
            }
        } catch (Exception e) {
            Log.e("<<SCHEMA>>", e.getMessage());
        }
        cursorData += "]";
// now get the rows
        cursor.moveToPosition(-1); //reset cursor's top
        while (cursor.moveToNext()) {
            String cursorRow = "\n[";
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                cursorRow += cursor.getString(i);
                if (i < cursor.getColumnCount() - 1) cursorRow += ", ";
            }
            cursorData += cursorRow + "]";
        }
        return cursorData + "\n";
    }

    private String getColumnType(Cursor cursor, int i) {
        try {
//peek at a row holding valid data
            cursor.moveToFirst();
            int result = cursor.getType(i);
            String[] types = {":NULL", ":INT", ":FLOAT", ":STR", ":BLOB", ":UNK"};
//backtrack - reset cursor's top
            cursor.moveToPosition(-1);
            return types[result];
        } catch (Exception e) {
            return " ";
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
