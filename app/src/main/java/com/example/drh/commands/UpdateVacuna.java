package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateVacuna extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idMas, vacuna, proxF, idVac;
    private int vacunaExist;

    public UpdateVacuna(Context context, String idVac, String idMas, String vacuna, String proxF){
        progressDialog = new ModalProgressDialog(context,"Actualizando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        vacunaExist = -1;
        this.idVac = idVac;
        this.idMas = idMas;
        this.vacuna = vacuna;
        this.proxF = proxF;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Mascota OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.mascota WHERE idMascota = "+idMas+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    vacunaExist = 1;
                else
                    vacunaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ vacunaExist);
                rs.close();
                st.close();
                if(vacunaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("UPDATE freedbtech_dbVeterinaria.vacuna SET idMascota='"+idMas+"',vacuna='"+vacuna+"',proxFecha='"+
                            proxF+"' WHERE idVacuna = "+idVac+"; ");
                    st.close();
                }
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
        modalDialog.setTitle("Actualizando Registro");
        progressDialog.hideProgressDialog();
        if(vacunaExist == 1) {
            modalDialog.setMessage("Registro actualizado con exito!");
        }else if(vacunaExist == 0){
            modalDialog.setMessage("El ID de la mascota no existe");
            modalDialog.showModalDialog();
        }else if(vacunaExist == -1){
            modalDialog.setMessage("Error al intentar actualizar el registro");
        }
        modalDialog.showModalDialog();
    }
}
