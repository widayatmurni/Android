package com.example.sqliteapps;

public class Biodata {

    private String Id, Nama, JKel, Alamat;

    public Biodata(String id, String nama, String JKel, String alamat) {
        Id = id;
        Nama = nama;
        this.JKel = JKel;
        Alamat = alamat;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setJKel(String JKel) {
        this.JKel = JKel;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getId() {
        return Id;
    }

    public String getNama() {
        return Nama;
    }

    public String getJKel() {
        return JKel;
    }

    public String getAlamat() {
        return Alamat;
    }
}
