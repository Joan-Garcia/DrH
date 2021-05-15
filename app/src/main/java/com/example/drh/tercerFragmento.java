package com.example.drh;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class tercerFragmento extends Fragment {

    Button btnConu, btnConM,btnConTablas, btnVacunas, btnDespa;
    View vista;

    public tercerFragmento() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_tercer_fragmento, container, false);
        asignar();
        return vista;
    }
    public void asignar(){
        btnConu=(Button) vista.findViewById(R.id.botonConUsuario);
        btnConM=(Button) vista.findViewById(R.id.botonConMas);
        btnVacunas=(Button) vista.findViewById(R.id.botonConVac);
        btnDespa=(Button) vista.findViewById(R.id.botonConDes);
        btnConTablas=(Button) vista.findViewById(R.id.botonConR);


        btnConu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent a = new Intent(vista.getContext(),buscarUsuario.class);
            startActivity(a);
            }
        });

        btnConM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent a = new Intent(vista.getContext(),buscar_mascota.class);
            startActivity(a);
            }
        });
        btnConTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent a= new Intent(vista.getContext(),tablaRegistros.class);
            startActivity(a);
            }
        });
        btnVacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (vista.getContext(),buscarVacuna.class);
                startActivity(a);
            }
        });

        btnDespa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(vista.getContext(),buscarDesp.class);
                startActivity(a);
            }
        });
    }



}