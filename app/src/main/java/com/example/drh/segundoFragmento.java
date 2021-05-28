package com.example.drh;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class segundoFragmento extends Fragment {


    TextView a, b;
    View vista;
    String correo, nombre;
    ImageButton btnSalir;
    LinearLayout ly;
    admin2 prueba;

    public segundoFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment segundoFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static segundoFragmento newInstance(String param1, String param2) {
        segundoFragmento fragment = new segundoFragmento();
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

        vista = inflater.inflate(R.layout.fragment_segundo_fragmento, container, false);
        a = (TextView) vista.findViewById(R.id.textAdmin);
        b = (TextView) vista.findViewById(R.id.textNombre);
        btnSalir = (ImageButton) vista.findViewById(R.id.btnSalir);
        ly =(LinearLayout) vista.findViewById(R.id.layoutP);
        prueba= new admin2();
        a.setText(nombre);
        b.setText("Bienvenido/a");
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
        Uri uri= Uri.parse("https://www.youtube.com/playlist?list=PLWHjFiWxfNZ8v3raZrdJPWVlk-5YPefuM");
        Intent ab = new Intent(Intent.ACTION_VIEW,uri);
        try {
            startActivity(ab);
        }catch(ActivityNotFoundException a){
            startActivity(new Intent
                    (Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/playlist?list=PLWHjFiWxfNZ8v3raZrdJPWVlk-5YPefuM")));
        }
    }

}