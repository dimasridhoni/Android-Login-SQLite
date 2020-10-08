/*
 * *
 *  * Created by Dimas Ridhoni on 10/7/20 11:16 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/7/20 11:16 AM
 *
 */

package com.dimasridhoni.javatestk24.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ListAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewMemberActivity extends AppCompatActivity {

    Button buttonTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        DatabaseHelper db = new DatabaseHelper(this);

        Integer jumlah = db.getMembercardCount();
        if (jumlah>0) {
            ArrayList<HashMap<String, String>> userList = db.getMemberCard();
            ListView lv = (ListView) findViewById(R.id.membercard_list);
            SimpleAdapter adapter =
                    new SimpleAdapter(ViewMemberActivity.this, userList, R.layout.list_row, new String[]{"nama", "alamat", "tanggal_lahir", "jenis_kelamin"}, new int[]{R.id.nama, R.id.alamat, R.id.tanggal_lahir, R.id.jenis_kelamin});
            lv.setAdapter(adapter);
        } else {
            Toast.makeText(ViewMemberActivity.this, "Data Tidak Ada", Toast.LENGTH_LONG).show();
        }

        buttonTambah = findViewById(R.id.buttonTambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent input = new Intent(ViewMemberActivity.this, InputMemberActivity.class);
                startActivity(input);
            }

        });

    }


}