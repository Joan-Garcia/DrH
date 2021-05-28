package com.example.drh;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drh.commands.SelectMascotas;

import java.util.ArrayList;

public class tercerFragmentUsuario extends Fragment {
    View vista;
    RecyclerView rv;
    SelectMascotas psu;
    lisMasFech lMF;
    ArrayList<lisMasFech> lisFecha;
    adapFech aF;
    int id;


    public tercerFragmentUsuario() {
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
        vista= inflater.inflate(R.layout.fragment_tercer_usuario, container, false);
        rv=(RecyclerView) vista.findViewById(R.id.rViewMasUsuarioT);
        rv.setLayoutManager(new LinearLayoutManager(vista.getContext()));
        lisFecha= new ArrayList<>();
        lMF = null;
        aF = new adapFech(lisFecha, vista.getContext());
        vamos();
        return vista;
    }
    public void vamos(){

        psu= new SelectMascotas(id,lMF,aF,lisFecha,rv,vista.getContext());
        psu.execute();
    }
}