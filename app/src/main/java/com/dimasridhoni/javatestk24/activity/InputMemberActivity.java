/*
 * *
 *  * Created by Dimas Ridhoni on 10/7/20 10:55 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/7/20 10:55 AM
 *
 */

package com.dimasridhoni.javatestk24.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dimasridhoni.javatestk24.R;
import com.dimasridhoni.javatestk24.adapter.JenisKelaminAdapter;
import com.dimasridhoni.javatestk24.data.DatabaseHelper;
import com.dimasridhoni.javatestk24.model.JenisKelamin;
import com.dimasridhoni.javatestk24.model.Membercard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InputMemberActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editTextNama, editTextTanggalLahir, editTextAlamat,
            editTextUsername, editTextPassword, editTextPasswordKonf;

    private Spinner spJenisKelamin;
    JenisKelaminAdapter jenisKelaminAdapter;
    List<JenisKelamin> listJenisKelamin = new ArrayList<JenisKelamin>();
    private String kdJenisKelamin;
    JenisKelamin jenisKelamin = new JenisKelamin();

    Button buttonTambah;
    Calendar calendar;
    SimpleDateFormat dateformatyyyyMMdd;
    SimpleDateFormat dateformatddMMyyyy;
    DatePickerDialog.OnDateSetListener datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_member);


        db = new DatabaseHelper(this);
        editTextNama = findViewById(R.id.editTextNama);
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordKonf = findViewById(R.id.editTextPasswordKonf);

        spJenisKelamin= findViewById(R.id.spJenisKelaimn);
        spJenisKelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                setKdJenisKelamin(listJenisKelamin.get(position).getKdJenisKelamin());
                Log.d("Jenis Kelamin : ",getKdJenisKelamin());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        jenisKelaminAdapter = new JenisKelaminAdapter(InputMemberActivity.this, listJenisKelamin);
        spJenisKelamin.setAdapter(jenisKelaminAdapter);

        //listJenisTransaksi.clear();
        JenisKelamin x = new JenisKelamin();
        x.setKdJenisKelamin("X");
        x.setJenisKelamin("Pilih Jenis Kelamin");
        listJenisKelamin.add(x);

        JenisKelamin l = new JenisKelamin();
        l.setKdJenisKelamin("L");
        l.setJenisKelamin("Laki-laki");
        listJenisKelamin.add(l);

        JenisKelamin p = new JenisKelamin();
        p.setKdJenisKelamin("P");
        p.setJenisKelamin("Perempuan");
        listJenisKelamin.add(p);

        jenisKelaminAdapter.notifyDataSetChanged();

        calendar = Calendar.getInstance();
        dateformatddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
        dateformatyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
        //editTextTanggalLahir.setText(dateformatddMMyyyy.format(calendar.getTime()));

        editTextTanggalLahir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(InputMemberActivity.this, datepicker, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        /*editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(InputMemberActivity.this, datepicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        /*editTextTanggalLahir.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                new DatePickerDialog(InputMemberActivity.this, datepicker, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

        datepicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub


                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String formatdmy = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(formatdmy);
                editTextTanggalLahir.setText(sdf.format(calendar.getTime()));

            }
        };

        buttonTambah = findViewById(R.id.buttonTambah);
        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Membercard membercard = new Membercard();
                String nama = editTextNama.getText().toString().trim();
                String tanggalLahir = editTextTanggalLahir.getText().toString().trim();
                String alamat = editTextAlamat.getText().toString().trim();
                String jenisKelamin = getKdJenisKelamin().trim();
                String userName = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String passwordKonf = editTextPasswordKonf.getText().toString().trim();

                if (password.equals(passwordKonf)) {
                    membercard.setNama(nama);
                    membercard.setTanggalLahir(tanggalLahir);
                    membercard.setAlamat(alamat);
                    membercard.setJenisKelamin(jenisKelamin);
                    membercard.setUsername(userName);
                    membercard.setPassword(password);
                    long val = db.addMemberCard(membercard);
                    if (val > 0) {
                        Toast.makeText(InputMemberActivity.this, "Berhasil Tambah Member", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(InputMemberActivity.this, HomeActivity.class);
                        startActivity(home);
                    } else
                        Toast.makeText(InputMemberActivity.this, "Error Tambah Member", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(InputMemberActivity.this, "Password tidak sama", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    public String getKdJenisKelamin() {
        return kdJenisKelamin;
    }

    public void setKdJenisKelamin(String kdJenisKelamin) {
        this.kdJenisKelamin = kdJenisKelamin;
    }
}