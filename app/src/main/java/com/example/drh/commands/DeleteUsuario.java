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

public class DeleteUsuario extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idUsu;
    private int userExist;
    Button a, b;
    FrameLayout fy;


    public DeleteUsuario(Context context, String idUsu, Button a, Button b, FrameLayout fy){
        progressDialog = new ModalProgressDialog(context,"Eliminando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.idUsu = idUsu;
        this.a=a;
        this.b=b;
        this.fy=fy;
        userExist = -1;
    }

    @Override
    protected void onPreExecute() { progressDialog.showModalProgressDialog(); }

    @Override
    protected Integer doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Usuario OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario WHERE idPropietario = "+idUsu+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    userExist = 1;
                else
                    userExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrada: "+ userExist);
                rs.close();
                st.close();
                if(userExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("DELETE FROM freedbtech_dbVeterinaria.propietario WHERE idPropietario = "+idUsu+"; ");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return userExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify Usuario",
                        throwables.getMessage());
                userExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify Usuario FAIL");
        }
        return userExist;
    }

    @Override
    protected void onPostExecute(Integer userExist) {
        modalDialog.setTitle("Eliminando Registro");
        progressDialog.hideProgressDialog();
        if(userExist == 1) {
            modalDialog.setMessage("Registro eliminado con exito!");
            this.a.setVisibility(View.INVISIBLE);
            this.b.setVisibility(View.INVISIBLE);
            this.fy.setVisibility(View.INVISIBLE);
        }else if(userExist == 0){
            modalDialog.setMessage("El ID del propietario no existe");
            modalDialog.showModalDialog();
        }else if(userExist == -1){
            modalDialog.setMessage("Error al intentar eliminar el registro");
        }
        modalDialog.showModalDialog();
    }
}
