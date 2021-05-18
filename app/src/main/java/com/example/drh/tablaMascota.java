package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drh.commands.SelectAllMascota;
import com.example.drh.commands.SelectAllUsuario;

import java.util.ArrayList;

public class tablaMascota extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<lisEMascota> listaM;
    lisEMascota lEM;
    SelectAllMascota psu;
    adapMascota aM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_mascota);
        rv = (RecyclerView) findViewById(R.id.recViewMas);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaM= new ArrayList<>();
        lEM= null;
        aM = new adapMascota(listaM, this);
        vamos();
    }
    public void vamos(){
        psu=new SelectAllMascota( lEM,aM,listaM,rv,this);
        //lMascota aMasco listaM
        psu.execute();
        //le adap array rev con
    }
}