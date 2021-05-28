package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.adapMasUsu;
import com.example.drh.adapMascota;
import com.example.drh.lisEMascota;
import com.example.drh.lisMascotaU;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectMasUsuario extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int status;
    int id;

    lisMascotaU lMU;
    adapMasUsu aM;
    ArrayList<lisMascotaU> listaE;
    RecyclerView rv;
    Context context;

    public SelectMasUsuario(
                            lisMascotaU lMU,
                            adapMasUsu aM,
                            ArrayList<lisMascotaU> listaE,
                            RecyclerView rv,
                            Context context, Integer id) {

        this.lMU = lMU;
        this.aM = aM;
        this.listaE = listaE;
        this.rv = rv;
        this.context = context;
        this.id=id;
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
        if(cnEnv!=null) {
            try{
                Statement st =cnEnv.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.mascota " +
                        "where idPropietario ="+id+ ";");
                        status = 2;


                        while (rs.next()){
                        this.lMU = new lisMascotaU(rs.getString("idMascota"),
                                rs.getString("nombre"), rs.getDate("fechaNac"),
                                rs.getString("especie"),
                                rs.getString("raza"),
                                rs.getString("sexo"),
                                rs.getString("color"),
                                rs.getString("tatuaje"),
                                rs.getString("microchip"),
                                "#3B4341");
                        this.listaE.add(this.lMU);
                            status =1;
                    }
                    workerThread();

                    return status;

            }catch(SQLException throwables){
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                status=-1;
            }
        }

        return status;
    }

    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.aM = new adapMasUsu(this.listaE, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.aM);
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
        }else if( status==2){
            modalDialog.setMessage("NO CUENTAS CON MASCOTAS");
            modalDialog.showModalDialog();
        }
    }
}
