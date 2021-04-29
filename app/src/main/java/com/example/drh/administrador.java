package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drh.commands.VerifyDatabaseConnection;

public class administrador extends AppCompatActivity {
    VerifyDatabaseConnection dbc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        dbc = new VerifyDatabaseConnection(this);
    }
}