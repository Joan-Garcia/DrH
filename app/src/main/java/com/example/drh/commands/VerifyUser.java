package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.example.drh.primerActivity;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifyUser extends AsyncTask<Void,Integer,Boolean> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private EditText edEmail, edPass;
    private boolean userExist, isAdmin;
    Context context;

    public VerifyUser(Context context, EditText edEmail, EditText edPass){
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Verificando Credenciales",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
        this.edEmail = edEmail;
        this.edPass = edPass;
        userExist = false;
        isAdmin = false;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.showModalProgressDialog();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();
        if(cnEnv!=null){
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Credenciales OK");
            try {
                Statement st = cnEnv.createStatement();
                String email = edEmail.getText().toString();
                String pass = edPass.getText().toString();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario WHERE email = '"+email+"' AND pass = '"+pass+"'; ");

                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1) {
                    userExist = true;
                    if(rs.getString("tipoUsuario").equals("A"))
                        isAdmin = true;
                }
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado"+ userExist);
                rs.close();
                st.close();
                cnEnv.close();
                cn.close();
                return userExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                userExist = false;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify User FAIL");
        }
        return userExist;
    }

    @Override
    protected void onPostExecute(Boolean userExist) {
        modalDialog.setTitle("Estado de la Conexión");
        progressDialog.hideProgressDialog();
        if(userExist) {
            //modalDialog.setMessage("Usuario Encontrado");
            if(isAdmin){
                //Cambiar a activity de admin
                Log.println(Log.INFO, "UserType", "Administrador");
            }else {
                //Cambiar a activity de usuario
                Intent a = new Intent(context, primerActivity.class);
                context.startActivity(a);
            }
        }else{
            modalDialog.setMessage("Usuario No Encontrado");
            modalDialog.showModalDialog();
        }
        //modalDialog.showModalDialog();
    }
}
