package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.adapUsuario;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.drh.lisUsuario;

public class SelectAllUsuario extends AsyncTask<Void, Integer, Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int status;
    lisUsuario le;
    adapUsuario lA;
    ArrayList <lisUsuario> listaE;
    RecyclerView rv;
    Context context;

    public SelectAllUsuario(lisUsuario le, adapUsuario lA, ArrayList<lisUsuario> listaE, RecyclerView rv, Context context) {
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Recuperando Registros",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
        this.le = le;
        this.lA = lA;
        this.listaE = listaE;
        this.rv = rv;
        status = -1;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.showModalProgressDialog();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn= new Connection();
        java.sql.Connection cnEnv= cn.connect();
        if(cnEnv!=null){
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs= st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario");
                while(rs.next()){
                    this.le= new lisUsuario(rs.getString("nombre")
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
                status = 1;
                return status;
            }catch (SQLException throwables){ // Para posibles fallos de conexión
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                status = -1;
            }
        }else{ // Para posibles fallos de conexión
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify User FAIL");
        }
        return status;
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

    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.lA = new adapUsuario(this.listaE, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.lA);
        });
    }
}
