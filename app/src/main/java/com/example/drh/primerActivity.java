package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drh.commands.VerifyDatabaseConnection;

public class primerActivity extends AppCompatActivity {
    VerifyDatabaseConnection dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        dbc = new VerifyDatabaseConnection(this);
        //Comentario test
    }


}