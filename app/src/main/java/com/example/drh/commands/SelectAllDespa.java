package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.adapDespa;
import com.example.drh.adapVacunas;
import com.example.drh.lisEDespa;
import com.example.drh.lisEVacuna;
import com.example.drh.tablaDesparacitaciones;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectAllDespa  extends AsyncTask<Void, Integer, Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int status;
    lisEDespa lD;
    adapDespa aD;
    ArrayList<lisEDespa> listaD;
    RecyclerView rv;
    Context context;

    public SelectAllDespa(lisEDespa lD, adapDespa aD, ArrayList<lisEDespa> listaD, RecyclerView rv, Context context) {
        this.lD = lD;
        this.aD = aD;
        this.listaD = listaD;
        this.rv = rv;
        this.context = context;
        status=0;
        progressDialog = new ModalProgressDialog(this.context,"Recuperando Registros",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
    }
    @Override
    protected void onPreExecute() {
        progressDialog.showModalProgressDialog();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn=new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            try{
                Statement st = cnEnv.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.vistadesparacitaciones ORDER BY idDesparacitacion;");
                while(rs.next()){
                    this.lD= new lisEDespa(rs.getString("idDesparacitacion"),
                            rs.getString("Mascota"),
                            rs.getString("producto"),
                            "#3B4341",
                            rs.getDate("fecha"),
                            rs.getDate("proxFecha"));
                    this.listaD.add(this.lD);
                }
                workerThread();
                status=1;
                return status;
            }catch(SQLException throwables){
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                status=-1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexi??n para Verify User FAIL");
        }
        return status;
    }
    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.aD = new adapDespa(this.listaD, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.aD);
        });
    }
    @Override
    protected void onPostExecute(Integer status) {
        modalDialog.setTitle("Recuperando Registros");
        progressDialog.hideProgressDialog();
        if(status == -1) {
            modalDialog.setMessage("Ocurri?? un error al recuperar los registros.");
            modalDialog.showModalDialog();
        }else if(status == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }
    }
}
