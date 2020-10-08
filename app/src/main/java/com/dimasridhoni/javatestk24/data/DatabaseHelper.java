/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 9:45 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 9:45 PM
 *
 */

package com.dimasridhoni.javatestk24.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dimasridhoni.javatestk24.model.Membercard;
import com.dimasridhoni.javatestk24.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context ctx;

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTER_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_KODE_MEMBER + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAMA + " TEXT,"
                + Constants.KEY_TANGGAL_LAHIR + " TEXT,"
                + Constants.KEY_ALAMAT + " TEXT,"
                + Constants.KEY_JENIS_KELAMIN + " TEXT,"
                + Constants.KEY_USERNAME + " TEXT,"
                + Constants.KEY_PASSWORD + " TEXT)";
        Log.d("TableCreated", "done");
        db.execSQL(CREATE_REGISTER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    /*
     * CRUD OPERATIONS : CREATE -->  add user  , DELETE , UPDATE  METHODS .
     *
     */

    public ArrayList<HashMap<String, String>> getMemberCard(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> membercardList = new ArrayList<>();
        String query = "SELECT * FROM "+ Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> membercard = new HashMap<>();
            membercard.put("nama",cursor.getString(cursor.getColumnIndex(Constants.KEY_NAMA)));
            membercard.put("alamat",cursor.getString(cursor.getColumnIndex(Constants.KEY_ALAMAT)));
            membercard.put("tanggal_lahir",cursor.getString(cursor.getColumnIndex(Constants.KEY_TANGGAL_LAHIR)));
            //membercard.put("jenis_kelamin",cursor.getString(cursor.getColumnIndex(Constants.KEY_JENIS_KELAMIN)));

            if (cursor.getString(cursor.getColumnIndex(Constants.KEY_JENIS_KELAMIN)).equals("L")){
                membercard.put("jenis_kelamin", "Laki-laki");
            } else {
                membercard.put("jenis_kelamin", "Perempuan");
            }
            membercardList.add(membercard);
        }
        return  membercardList;
    }

    public ArrayList<HashMap<String, String>> getMemberCardByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> membercardList = new ArrayList<>();
        String query = "SELECT * FROM "+ Constants.TABLE_NAME + " WHERE "+ Constants.KEY_USERNAME + "='"+username+"'" ;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> membercard = new HashMap<>();
            membercard.put("nama",cursor.getString(cursor.getColumnIndex(Constants.KEY_NAMA)));
            membercard.put("alamat",cursor.getString(cursor.getColumnIndex(Constants.KEY_ALAMAT)));
            membercard.put("tanggal_lahir",cursor.getString(cursor.getColumnIndex(Constants.KEY_TANGGAL_LAHIR)));
            //membercard.put("jenis_kelamin",cursor.getString(cursor.getColumnIndex(Constants.KEY_JENIS_KELAMIN)));

            if (cursor.getString(cursor.getColumnIndex(Constants.KEY_JENIS_KELAMIN)).equals("L")){
                membercard.put("jenis_kelamin", "Laki-laki");
            } else {
                membercard.put("jenis_kelamin", "Perempuan");
            }
            membercardList.add(membercard);
        }
        return  membercardList;
    }


    public long addMemberCard(Membercard membercard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAMA, membercard.getNama());
        values.put(Constants.KEY_TANGGAL_LAHIR, membercard.getTanggalLahir());
        values.put(Constants.KEY_ALAMAT, membercard.getAlamat());
        values.put(Constants.KEY_JENIS_KELAMIN, membercard.getJenisKelamin());
        values.put(Constants.KEY_USERNAME, membercard.getUsername());
        values.put(Constants.KEY_PASSWORD, membercard.getPassword());

        // Error
        long res = db.insert(Constants.TABLE_NAME, null, values);
        Log.d("Saved!", "saved to DB");
        db.close();
        return res;

    }






    public int updatePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_PASSWORD, password);
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_USERNAME + "=?",
                new String[]{String.valueOf(username)});
    }



    public int getMembercardCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }


    public Boolean checkUser(String username, String password) {
        if (username.equals("admin") && (password.equals("123"))) {
            return true;
        } else {
            String[] columns = {Constants.KEY_KODE_MEMBER};
            SQLiteDatabase db = getReadableDatabase();
            String selection = Constants.KEY_USERNAME + "=?" + " and " + Constants.KEY_PASSWORD + "=?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            int count = cursor.getCount();
            cursor.close();
            db.close();

            if (count > 0)
                return true;
            else
                return false;
        }
    }




}
