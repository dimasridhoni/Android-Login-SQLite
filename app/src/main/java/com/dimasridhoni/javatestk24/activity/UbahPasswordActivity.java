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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.data.DatabaseHelper;
import com.dimasridhoni.javatestk24.preferences.Preferences;

public class UbahPasswordActivity extends AppCompatActivity {

    EditText editTextPassword, editTextPasswordKonf;
    Button buttonUbahPassword;
    DatabaseHelper db;
    Context context;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        db = new DatabaseHelper(this);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordKonf = findViewById(R.id.editTextPasswordKonf);
        buttonUbahPassword = findViewById(R.id.buttonUbahPassword);

        buttonUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = editTextPassword.getText().toString().trim();
                String passwordKonf = editTextPasswordKonf.getText().toString().trim();
                if (password.equals(passwordKonf)) {
                    username = Preferences.getUsernameUser(getBaseContext());
                    long val = db.updatePassword(username, password);
                    if (val > 0) {
                        Toast.makeText(UbahPasswordActivity.this, "Berhasil Ubah Password", Toast.LENGTH_SHORT).show();

                        Preferences.clearLoggedInUser(getBaseContext());
                        Intent main = new Intent(UbahPasswordActivity.this, MainActivity.class);
                        startActivity(main);
                    } else
                        Toast.makeText(UbahPasswordActivity.this, "Error Ubah Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UbahPasswordActivity.this, "Password dan Password Konfirmasi Harus Sama", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}