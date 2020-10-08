/*
 * *
 *  * Created by Dimas Ridhoni on 10/6/20 9:40 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 10/6/20 9:40 PM
 *
 */

package com.dimasridhoni.javatestk24.model;

public class Membercard {

    private String kodeMember;
    private String nama;
    private String tanggalLahir;
    private String alamat;
    private String jenisKelamin;
    private String username;
    private String password;

    public Membercard() {

    }

    public Membercard(String kodeMember, String nama, String tanggalLahir, String alamat, String jenisKelamin, String username, String password) {
        this.kodeMember = kodeMember;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.username = username;
        this.password = password;
    }

    public String getKodeMember() {
        return kodeMember;
    }

    public void setKodeMember(String kodeMember) {
        this.kodeMember = kodeMember;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
