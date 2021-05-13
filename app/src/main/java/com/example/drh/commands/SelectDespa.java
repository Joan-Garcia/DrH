package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDespa extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private EditText idMas, producto, proxF, idDes;
    Context context;
    private int despaExist;
    Button a, b;
    FrameLayout fy;
    public SelectDespa(Context context, EditText idDes, EditText idMas, EditText producto, EditText proxF
    , Button a, Button b, FrameLayout fy){
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Buscando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
        this.idDes = idDes;
        this.idMas = idMas;
        this.producto = producto;
        this.proxF = proxF;
        this.a=a;
        this.b=b;
        this.fy=fy;
        despaExist = -1;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.showModalProgressDialog();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar idVacuna OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.desparacitacion WHERE idDesparacitacion = "+idDes.getText().toString()+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1) {
                    despaExist = 1;

                    String idD = rs.getString("idDesparacitacion");
                    String idM = rs.getString("idMascota");
                    String des = rs.getString("producto");
                    String pro = rs.getString("proxFecha");

                    workerThread(idD, idM, des, pro);
                }else
                    despaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Desparacitacion encontrada: "+ despaExist);
                rs.close();
                st.close();
                cnEnv.close();
                cn.close();
                return despaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Despa",
                        throwables.getMessage());
                despaExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Despa FAIL");
        }
        return despaExist;
    }

    @Override
    protected void onPostExecute(Integer despaExist) {
        modalDialog.setTitle("Buscando Registro");
        progressDialog.hideProgressDialog();
        if(despaExist == 0) {
            modalDialog.setMessage("El ID no existe");
            modalDialog.showModalDialog();
        }else if(despaExist == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }else if(despaExist == -1){
            modalDialog.setMessage("Error al intentar recuperar el registro");
            modalDialog.showModalDialog();
        }

    }

    @WorkerThread
    void workerThread(String idDes, String idMas, String producto, String proxF) {
        ContextCompat.getMainExecutor(context).execute(()  -> {
            this.idDes.setText(idDes);
            this.idMas.setText(idMas);
            this.producto.setText(producto);
            this.proxF.setText(proxF);
            this.a.setVisibility(View.VISIBLE);
            this.b.setVisibility(View.VISIBLE);
            this.fy.setVisibility(View.VISIBLE);
        });
    }
}
