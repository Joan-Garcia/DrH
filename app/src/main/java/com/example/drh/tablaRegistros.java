package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drh.commands.SelectAllUsuario;
import com.example.drh.utils.Connection;

import java.util.ArrayList;

public class tablaRegistros extends AppCompatActivity {

RecyclerView rv;
ArrayList <String> listaInfo;
ArrayList <lisElement> listaE;
Connection cn;
lisElement le;
SelectAllUsuario psu;
listAdapter lA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_registros);
        rv = (RecyclerView) findViewById(R.id.recView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaE = new ArrayList<>();
        le = null;
        lA= new listAdapter(listaE,this);

        vamosaverquepasa();
    }
    public void vamosaverquepasa(){
        psu= new SelectAllUsuario(le,lA,listaE,rv,this);
        psu.execute();
    }


}