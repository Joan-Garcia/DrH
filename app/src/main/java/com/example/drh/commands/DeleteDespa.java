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

public class DeleteDespa extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idDes;
    private int despaExist;
    Button a, b;
    FrameLayout fy;

    public DeleteDespa(Context context, String idDes, Button a, Button b, FrameLayout fy){
        progressDialog = new ModalProgressDialog(context,"Eliminando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.idDes = idDes;
        this.a=a;
        this.b=b;
        this.fy=fy;
        despaExist = -1;
    }

    @Override
    protected void onPreExecute() { progressDialog.showModalProgressDialog(); }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Desparacitacion OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.desparacitacion WHERE idDesparacitacion = "+idDes+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    despaExist = 1;
                else
                    despaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Desparacitacion encontrada: "+ despaExist);
                rs.close();
                st.close();
                if(despaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("DELETE FROM freedbtech_dbVeterinaria.desparacitacion WHERE idDesparacitacion = "+idDes+"; ");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return despaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Despa",
                        throwables.getMessage());
                despaExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Desparacitacion FAIL");
        }
        return despaExist;
    }

    @Override
    protected void onPostExecute(Integer despaExist) {
        modalDialog.setTitle("Eliminando Registro");
        progressDialog.hideProgressDialog();
        if(despaExist == 1) {
            modalDialog.setMessage("Registro eliminado con exito!");
            this.a.setVisibility(View.INVISIBLE);
            this.b.setVisibility(View.INVISIBLE);
            this.fy.setVisibility(View.INVISIBLE);
        }else if(despaExist == 0){
            modalDialog.setMessage("El ID de la desparacitacion no existe");
            modalDialog.showModalDialog();
        }else if(despaExist == -1){
            modalDialog.setMessage("Error al intentar eliminar el registro");
        }
        modalDialog.showModalDialog();
    }
}
