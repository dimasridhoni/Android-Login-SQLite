/*
 * *
 *  * Created by Dimas Ridhoni on 10/9/20 12:12 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/9/20 12:12 AM
 *
 */

package com.dimasridhoni.javatestk24.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.data.DatabaseHelper;
import com.dimasridhoni.javatestk24.preferences.Preferences;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewMemberLoginActivity extends AppCompatActivity {

    String username;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member_login);

        DatabaseHelper db = new DatabaseHelper(this);
        username = Preferences.getUsernameUser(getBaseContext());
        ArrayList<HashMap<String, String>> userList = db.getMemberCardByUsername(username);
        ListView lv = (ListView) findViewById(R.id.membercard_list);
        SimpleAdapter adapter =
                new SimpleAdapter(ViewMemberLoginActivity.this, userList, R.layout.list_row_member, new String[]{"nama", "alamat", "tanggal_lahir", "jenis_kelamin"}, new int[]{R.id.nama, R.id.alamat, R.id.tanggal_lahir, R.id.jenis_kelamin});
        lv.setAdapter(adapter);


    }
}