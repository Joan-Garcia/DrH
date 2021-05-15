package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.R;
import com.example.drh.listAdapter;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;
import com.example.drh.lisElement.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.drh.lisElement;

public class pruebaSelecU extends AsyncTask<Void, Integer, Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int a=0;
    lisElement le;
    listAdapter lA;
    ArrayList <lisElement> listaE;
    RecyclerView rv;
    Context context;

    public pruebaSelecU(lisElement le, listAdapter lA, ArrayList<lisElement> listaE, RecyclerView rv, Context context) {
        this.le = le;
        this.lA = lA;
        this.listaE = listaE;
        this.rv = rv;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn= new Connection();
        java.sql.Connection cnEnv= cn.connect();
        if(cnEnv!=null){
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs= st.executeQuery("SELECT* FROM freedbtech_dbVeterinaria.propietario");
                while(rs.next()){
                    this.le= new lisElement(rs.getString("nombre")
                            + " " +rs.getString("aPaterno") +" "+ rs.getString("aMaterno"),
                            rs.getString("domicilio"),
                            rs.getString("colonia"),
                            rs.getString("cp"),
                            rs.getString("pais"),
                            rs.getString("estado"),
                            rs.getString("email"),
                            rs.getString("tel"),
                            rs.getString("cel"),
                            rs.getString("idPropietario"),
                            rs.getString("tipoUsuario"),
                            rs.getString("ciudad"),
                            "#3B4341");

                        this.listaE.add(this.le);

                }
                workerThread();
                a=1;
                return a;
            }catch (SQLException throwables){

            }
        }
        a=-1;
        return a;
    }
    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.lA = new listAdapter(this.listaE, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.lA);
        });
    }
}
