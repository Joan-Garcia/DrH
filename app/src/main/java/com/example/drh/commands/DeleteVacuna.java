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

public class DeleteVacuna extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idVac;
    private int vacunaExist;
    Button a, b;
    FrameLayout fy;

    public DeleteVacuna(Context context, String idVac, Button a, Button b, FrameLayout fy){
        progressDialog = new ModalProgressDialog(context,"Eliminando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.idVac = idVac;
        this.a=a;
        this.b=b;
        this.fy=fy;
        vacunaExist = -1;
    }

    @Override
    protected void onPreExecute() { progressDialog.showModalProgressDialog(); }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Vacuna OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.vacuna WHERE idVacuna = "+idVac+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    vacunaExist = 1;
                else
                    vacunaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Vacuna encontrada: "+ vacunaExist);
                rs.close();
                st.close();
                if(vacunaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("DELETE FROM freedbtech_dbVeterinaria.vacuna WHERE idVacuna = "+idVac+"; ");
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
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Vacuna FAIL");
        }
        return vacunaExist;
    }

    @Override
    protected void onPostExecute(Integer vacunaExist) {
        modalDialog.setTitle("Eliminando Registro");
        progressDialog.hideProgressDialog();
        if(vacunaExist == 1) {
            modalDialog.setMessage("Registro eliminado con exito!");
            this.a.setVisibility(View.INVISIBLE);
            this.b.setVisibility(View.INVISIBLE);
            this.fy.setVisibility(View.INVISIBLE);
        }else if(vacunaExist == 0){
            modalDialog.setMessage("El ID de la vacuna no existe");
            modalDialog.showModalDialog();
        }else if(vacunaExist == -1){
            modalDialog.setMessage("Error al intentar eliminar el registro");
        }
        modalDialog.showModalDialog();
    }
}
