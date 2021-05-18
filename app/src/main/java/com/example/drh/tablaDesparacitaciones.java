package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drh.commands.SelectAllDespa;
import com.example.drh.commands.SelectAllVacuna;

import java.util.ArrayList;

public class tablaDesparacitaciones extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<lisEDespa> listaD;
    lisEDespa lED;
    SelectAllDespa psu;
    adapDespa aM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_desparacitaciones);
        rv = (RecyclerView) findViewById(R.id.recViewDes);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaD= new ArrayList<>();
        lED= null;
        aM = new adapDespa(listaD, this);
        vamos();
    }
    public void vamos(){
        psu=new SelectAllDespa( lED,aM,listaD,rv,this);
        //lMascota aMasco listaM
        psu.execute();
        //le adap array rev con
    }
}