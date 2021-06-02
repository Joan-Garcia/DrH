package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertVacuna extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idMascota, nomVac, proxFech;
    private int mascotaExist;
    String fecha;

    public InsertVacuna(Context context, String idMascota, String nomVac, String proxFech){
        progressDialog = new ModalProgressDialog(context,"Insertando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.idMascota = idMascota;
        this.nomVac = nomVac;
        this.proxFech = proxFech;
        mascotaExist = -1;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        fecha = sdf.format(c.getTime());
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
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.mascota WHERE idMascota = "+idMascota+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    mascotaExist = 1;
                else
                    mascotaExist = 0;
                Log.println(Log.INFO,"VerifyMascota","Mascota encontrada: "+ mascotaExist);
                rs.close();
                st.close();
                if(mascotaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("INSERT INTO freedbtech_dbVeterinaria.vacuna(idMascota, fecha, vacuna, proxFecha) VALUES("+idMascota+",'"+fecha+"', '"+nomVac+"', '"+proxFech+"');");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return mascotaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Mascota",
                        throwables.getMessage());
                mascotaExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify User FAIL");
        }
        return mascotaExist;
    }

    @Override
    protected void onPostExecute(Integer mascotaExist) {
        modalDialog.setTitle("Insertando Registro");
        progressDialog.hideProgressDialog();
        if(mascotaExist == 1) {
            modalDialog.setMessage("Registro insertado con exito!");

        }else{
            modalDialog.setMessage("El ID de la mascota no existe");
            modalDialog.showModalDialog();
        }
        modalDialog.showModalDialog();
    }
}
