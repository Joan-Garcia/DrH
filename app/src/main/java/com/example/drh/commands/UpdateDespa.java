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

public class UpdateDespa extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idMas, producto, proxF, idDes;
    private int despaExist;

    public UpdateDespa(Context context, String idDes, String idMas, String producto, String proxF){
        progressDialog = new ModalProgressDialog(context,"Actualizando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        despaExist = -1;
        this.idDes = idDes;
        this.idMas = idMas;
        this.producto = producto;
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
                    despaExist = 1;
                else
                    despaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ despaExist);
                rs.close();
                st.close();
                if(despaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("UPDATE freedbtech_dbVeterinaria.desparacitacion SET idMascota='"+idMas+"',producto='"+producto+"',proxFecha='"+
                            proxF+"' WHERE idDesparacitacion = "+idDes+"; ");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return despaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Mascota",
                        throwables.getMessage());
                despaExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Mascota FAIL");
        }
        return despaExist;
    }

    @Override
    protected void onPostExecute(Integer despaExist) {
        modalDialog.setTitle("Actualizando Registro");
        progressDialog.hideProgressDialog();
        if(despaExist == 1) {
            modalDialog.setMessage("Registro actualizado con exito!");
        }else if(despaExist == 0){
            modalDialog.setMessage("El ID de la mascota no existe");
            modalDialog.showModalDialog();
        }else if(despaExist == -1){
            modalDialog.setMessage("Error al intentar actualizar el registro");
        }
        modalDialog.showModalDialog();
    }
}
