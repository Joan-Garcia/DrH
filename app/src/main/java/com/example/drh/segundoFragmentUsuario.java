package com.example.drh;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class segundoFragmentUsuario extends Fragment {
View vista;
RecyclerView rv;
TextView a, b;
String correo, nombre;
ImageButton btnSalir;
LinearLayout ly;
admin2 prueba;

    public segundoFragmentUsuario() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static segundoFragmentUsuario newInstance(String param1, String param2) {
        segundoFragmentUsuario fragment = new segundoFragmentUsuario();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            correo = getArguments().getString("correo");
            nombre = getArguments().getString("nombre");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       vista= inflater.inflate(R.layout.fragment_segundo_usuario, container, false);


        a = (TextView) vista.findViewById(R.id.textBienvenido);
        b = (TextView) vista.findViewById(R.id.textNomUsuario);
        btnSalir = (ImageButton) vista.findViewById(R.id.btnSalirUsuario);
        ly =(LinearLayout) vista.findViewById(R.id.layoutGUIA);
        prueba= new admin2();
        a.setText("Bienvenido/a");
        b.setText(nombre);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirCuenta();
            }
        });

        ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tutorial();
            }
        });
        return vista;
    }
    public void salirCuenta() {
        Toast.makeText(vista.getContext(), "CERRANDO SESIÓN", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(vista.getContext(), Login.class);
        startActivity(a);
        this.getActivity().finish();



    }

    public void tutorial(){
        Uri uri= Uri.parse("https://youtube.com/playlist?list=PLWHjFiWxfNZ_fF5SLauGQXH679LqB7DVU");
        Intent ab = new Intent(Intent.ACTION_VIEW,uri);
        try {
            startActivity(ab);
        }catch(ActivityNotFoundException a){
            startActivity(new Intent
                    (Intent.ACTION_VIEW,Uri.parse("https://youtube.com/playlist?list=PLWHjFiWxfNZ_fF5SLauGQXH679LqB7DVU")));
        }
    }




}