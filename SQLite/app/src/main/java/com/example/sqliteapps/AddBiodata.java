package com.example.sqliteapps;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddBiodata extends AppCompatActivity {

    private Button btnSimpan;
    private EditText etId;
    private EditText etNama;
    private String jkel;
    private EditText etAlamat;
    private RadioButton jKelL, jKelP;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biodata);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        etId = (EditText) findViewById(R.id.etId);
        etNama = (EditText) findViewById(R.id.etNama);
        jKelL = (RadioButton) findViewById(R.id.jkelL);
        jKelP = (RadioButton) findViewById(R.id.jkelP);
        etAlamat = (EditText) findViewById(R.id.etAlamat);

        //set jenis kelamin
        jKelL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jkel = "L";
            }
        });
        jKelP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jkel = "P";
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
            }
        });

    }

    private void setClear(){
        etId.setText("");
        etNama.setText("");
        jKelL.setChecked(false);
        jKelP.setChecked(false);
        etAlamat.setText("");

        etId.requestFocus();
    }

    private void simpan (){
        String id, nama, alamat;
        Biodata bioItem;

        id = etId.getText().toString().trim();
        nama = etNama.getText().toString().trim();
        alamat = etAlamat.getText().toString().trim();

        //buat kelas biodata
        bioItem = new Biodata(id, nama, this.jkel, alamat);

        //kirim ke dbhelper untuk di simapan ke db
        dbHelper  = new DatabaseHelper(getApplicationContext());
        if (dbHelper.tambahData(bioItem)){
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_LONG).show();
            setClear();
            finish();
        }else{
            Toast.makeText(this, "gagal", Toast.LENGTH_LONG).show();
        }
    }
}
