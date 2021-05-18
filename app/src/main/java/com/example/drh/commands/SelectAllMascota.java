package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.adapMascota;
import com.example.drh.adapVacunas;
import com.example.drh.lisEMascota;
import com.example.drh.lisEVacuna;
import com.example.drh.lisUsuario;
import com.example.drh.adapUsuario;
import com.example.drh.tablaVacunas;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectAllMascota extends AsyncTask <Void, Integer, Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int status;
    lisEMascota le;
    adapMascota lA;
    ArrayList<lisEMascota> listaE;
    RecyclerView rv;
    Context context;

    public SelectAllMascota(lisEMascota le, adapMascota lA, ArrayList<lisEMascota> listaE, RecyclerView rv, Context context) {
        this.le = le;
        this.lA = lA;
        this.listaE = listaE;
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
        cn= new Connection();
        java.sql.Connection cnEnv = cn.connect();


        if(cnEnv!=null){
            try{
                Statement st =cnEnv.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.vistamascotas");
                while(rs.next()){
                    this.le = new lisEMascota(rs.getString("idMascota"),
                            rs.getString("nombre"),
                            rs.getDate("fechaNac"),
                            rs.getString("especie"),
                            rs.getString("raza"),
                            rs.getString("sexo"),
                            rs.getString("color"),
                            rs.getString("tatuaje"),
                            rs.getString("microchip"),
                            "#3B4341",
                            rs.getString("Propietario"));
                    this.listaE.add(this.le);
                }
                workerThread();
                status =1;
                return status;
            }catch(SQLException throwables){
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                status=-1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify User FAIL");
        }

        return status;
    }
    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.lA = new adapMascota(this.listaE, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.lA);
        });
    }
    @Override
    protected void onPostExecute(Integer status) {
        modalDialog.setTitle("Recuperando Registros");
        progressDialog.hideProgressDialog();
        if(status == -1) {
            modalDialog.setMessage("Ocurrió un error al recuperar los registros.");
            modalDialog.showModalDialog();
        }else if(status == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }
    }
}
