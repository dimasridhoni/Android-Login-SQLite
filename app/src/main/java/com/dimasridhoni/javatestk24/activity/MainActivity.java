/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 9:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 9:34 PM
 *
 */

package com.dimasridhoni.javatestk24.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.data.DatabaseHelper;
import com.dimasridhoni.javatestk24.model.Membercard;
import com.dimasridhoni.javatestk24.preferences.Preferences;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    DatabaseHelper db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),HomeActivity.class));
            finish();
        }

        db = new DatabaseHelper(this);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String role;

            Boolean login = db.checkUser(username, password);
            if (login== true) {

                if (username.equals("admin")) {
                    role = "Admin";
                } else {
                    role = "Member";
                }
                Preferences.setLoggedInUser(getBaseContext(), username, role);
                Preferences.setLoggedInStatus(getBaseContext(),true);

                Intent HomePage = new Intent(MainActivity.this, HomeActivity.class);
                Bundle b = new Bundle();
                b.putString("textViewUsername", editTextUsername.getText().toString());
                b.putString("textViewPassword", editTextPassword.getText().toString());
                HomePage.putExtras(b);
                startActivity(HomePage);
                finish();

            } else {
                Toast.makeText(MainActivity.this, "Username / Password Salah", Toast.LENGTH_LONG).show();
            }
            }
        });
    }
}