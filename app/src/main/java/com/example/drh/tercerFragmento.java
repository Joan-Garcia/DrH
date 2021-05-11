package com.example.drh;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tercerFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tercerFragmento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnConu, btnConM,btnConTablas, btnVacunas, btnDespa;
    View vista;

    public tercerFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tercerFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static tercerFragmento newInstance(String param1, String param2) {
        tercerFragmento fragment = new tercerFragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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