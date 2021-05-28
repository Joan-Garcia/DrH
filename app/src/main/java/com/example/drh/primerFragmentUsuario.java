package com.example.drh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drh.commands.SelectAllMascota;
import com.example.drh.commands.SelectMasUsuario;

import java.util.ArrayList;


public class primerFragmentUsuario extends Fragment {
    View vista;
    RecyclerView rv;
    ArrayList<lisMascotaU> listaM;
    lisMascotaU lEM;
    SelectMasUsuario psu;
    adapMasUsu aM;
    int id;

    public primerFragmentUsuario() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           id= getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_primer_usuario, container, false);
        rv=(RecyclerView) vista.findViewById(R.id.recViewMasUsuario);
        rv.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        listaM= new ArrayList<>();
        lEM= null;
        aM = new adapMasUsu(listaM, vista.getContext());
        vamos();
        return vista;
    }
    public void vamos(){
        psu = new SelectMasUsuario(lEM,aM,listaM,rv,vista.getContext(),id);
        psu.execute();
    }
}