package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMascota extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idMas;
    private int mascotaExist;
    Button a, b;
    FrameLayout fy;

    public DeleteMascota(Context context, String idMas, FrameLayout fy, Button a, Button b){
        progressDialog = new ModalProgressDialog(context,"Eliminando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.idMas = idMas;
        this.a=a;
        this.b=b;
        this.fy=fy;
        mascotaExist = -1;
    }

    @Override
    protected void onPreExecute() { progressDialog.showModalProgressDialog(); }

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
                    mascotaExist = 1;
                else
                    mascotaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Mascota encontrada: "+ mascotaExist);
                rs.close();
                st.close();
                if(mascotaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("DELETE FROM freedbtech_dbVeterinaria.mascota WHERE idMascota = "+idMas+"; ");
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
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Mascota FAIL");
        }
        return mascotaExist;
    }

    @Override
    protected void onPostExecute(Integer mascotaExist) {
        modalDialog.setTitle("Eliminando Registro");
        progressDialog.hideProgressDialog();
        if(mascotaExist == 1) {
            modalDialog.setMessage("Registro eliminado con exito!");
            this.a.setVisibility(View.INVISIBLE);
            this.b.setVisibility(View.INVISIBLE);
            this.fy.setVisibility(View.INVISIBLE);
        }else if(mascotaExist == 0){
            modalDialog.setMessage("El ID de la mascota no existe");
            modalDialog.showModalDialog();
        }else if(mascotaExist == -1){
            modalDialog.setMessage("Error al intentar eliminar el registro");
        }
        modalDialog.showModalDialog();
    }
}
