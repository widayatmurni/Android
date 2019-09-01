package com.example.sqliteapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSimpan, viewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSimpan = (Button) findViewById(R.id.btnAdd);
        viewData = (Button) findViewById(R.id.btnView);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vInput = new Intent(getApplicationContext(), AddBiodata.class);
                startActivity(vInput);
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vListData = new Intent(MainActivity.this, listBiodata.class );
                startActivity(vListData);
            }
        });
    }
}
