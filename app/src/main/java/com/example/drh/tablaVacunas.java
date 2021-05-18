package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drh.commands.SelectAllMascota;
import com.example.drh.commands.SelectAllVacuna;

import java.util.ArrayList;

public class tablaVacunas extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<lisEVacuna> listaV;
    lisEVacuna lEV;
    SelectAllVacuna psu;
    adapVacunas aM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_vacunas);
        rv = (RecyclerView) findViewById(R.id.recViewVacunas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaV= new ArrayList<>();
        lEV= null;
        aM = new adapVacunas(listaV, this);
        vamos();
    }
    public void vamos(){
        psu=new SelectAllVacuna( lEV,aM,listaV,rv,this);
        //lMascota aMasco listaM
        psu.execute();
        //le adap array rev con
    }
}