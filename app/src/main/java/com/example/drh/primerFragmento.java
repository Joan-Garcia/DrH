package com.example.drh;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.R.layout.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class primerFragmento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    FragmentManager fm;
    Spinner sp;
    String a;
    FragmentActivity myContext;
    crearP uF = new crearP();
    crear_Mascota mF= new crear_Mascota();
    crear_Despa dF=  new crear_Despa();
    crearVacuna vF= new crearVacuna();
    seleccionar sF= new seleccionar();




    public primerFragmento() {
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
        vista = inflater.inflate(R.layout.fragment_primer_fragmento, container, false);
        sp=(Spinner)vista.findViewById(R.id.spinner);
        llenar();

        return vista;
    }
    @Override
    public void onResume() {
        super.onResume();
        llenar();
    }

    public void llenar() {

        String [] tablas= new String[]{"Seleccione","CREAR USUARIO","INGRESAR MASCOTA","APLICAR VACUNA","APLICAR DESPARACITACIÓN"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(vista.getContext(),
                R.layout.spinner_item_prueba,tablas);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               a = sp.getSelectedItem().toString();
                mostrar(a);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void mostrar(String a) {
        FragmentTransaction fragManager = myContext.getSupportFragmentManager().beginTransaction();
        //"CREAR USUARIO","INGRESAR MASCOTA","APLICAR VACUNA","APLICAR DESPARACITACIÓN"};
        if (a.equals("CREAR USUARIO")) {
            fragManager.replace(R.id.contenedor1,uF);
            fragManager.addToBackStack(null);

        } else if (a.equals("INGRESAR MASCOTA")) {

            fragManager.replace(R.id.contenedor1,mF);
            fragManager.addToBackStack(null);

        } else if (a.equals("APLICAR VACUNA")) {

            fragManager.replace(R.id.contenedor1,vF);
            fragManager.addToBackStack(null);

        } else if (a.equals("APLICAR DESPARACITACIÓN")) {

            fragManager.replace(R.id.contenedor1,dF);
            fragManager.addToBackStack(null);

        } else {

            fragManager.replace(R.id.contenedor1,sF);
            fragManager.addToBackStack(null);
        }
        fragManager.commit();

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }




}




