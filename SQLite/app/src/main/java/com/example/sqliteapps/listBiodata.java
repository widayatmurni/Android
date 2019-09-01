package com.example.sqliteapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class listBiodata extends AppCompatActivity {

    private RecyclerView rcView;
    private RecyclerView.Adapter rcViewAdapter;
    private RecyclerView.LayoutManager rcViewLayoutManager;
    DatabaseHelper db;
    ArrayList<Biodata> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_biodata);

        getListBiodata();

        rcView = (RecyclerView) findViewById(R.id.rc_view);

        rcViewLayoutManager = new LinearLayoutManager(listBiodata.this);
        rcView.setLayoutManager(rcViewLayoutManager);
        rcView.setAdapter(new BioAdapter(listData));

    }

    private void getListBiodata() {
        listData.clear();

        db = new DatabaseHelper(getApplicationContext());
        db.getWritableDatabase();

        listData = db.getBiodata();


        db.close();

    }
}
