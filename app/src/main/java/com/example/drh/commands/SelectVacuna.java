package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectVacuna extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private EditText idMas, vacuna, proxF, idVac;
    Context context;
    private int vacunaExist;

    public SelectVacuna(Context context, EditText idVac, EditText idMas, EditText vacuna, EditText proxF){
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Buscando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
        this.idVac = idVac;
        this.idMas = idMas;
        this.vacuna = vacuna;
        this.proxF = proxF;
        vacunaExist = -1;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar idMascota OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.vacuna WHERE idVacuna = "+idVac.getText().toString()+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1) {
                    vacunaExist = 1;

                    String idV = rs.getString("idVacuna");
                    String idM = rs.getString("idMascota");
                    String vac = rs.getString("vacuna");
                    String pro = rs.getString("proxFecha");

                    workerThread(idV, idM, vac, pro);
                }else
                    vacunaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Mascota encontrada: "+ vacunaExist);
                rs.close();
                st.close();
                cnEnv.close();
                cn.close();
                return vacunaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Mascota",
                        throwables.getMessage());
                vacunaExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Mascota FAIL");
        }
        return vacunaExist;
    }

    @Override
    protected void onPostExecute(Integer vacunaExist) {
        modalDialog.setTitle("Buscando Registro");
        progressDialog.hideProgressDialog();
        if(vacunaExist == 0) {
            modalDialog.setMessage("El ID no existe");
            modalDialog.showModalDialog();
        }else if(vacunaExist == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }else if(vacunaExist == -1){
            modalDialog.setMessage("Error al intentar recuperar el registro");
            modalDialog.showModalDialog();
        }

    }

    @WorkerThread
    void workerThread(String idVac, String idMas, String vacuna, String proxF) {
        ContextCompat.getMainExecutor(context).execute(()  -> {
            this.idVac.setText(idVac);
            this.idMas.setText(idMas);
            this.vacuna.setText(vacuna);
            this.proxF.setText(proxF);
        });
    }
}
