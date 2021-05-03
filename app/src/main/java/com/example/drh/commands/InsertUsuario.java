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

public class InsertUsuario extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String nombre, aPater, aMater, domicilio, colonia, ciudad, estado, cp, pais, telFijo, telCel,
    correo, contra, confcontra, tUsuario;
    private int userExist;

    public InsertUsuario(Context context, String nombre, String aPater, String aMater, String domicilio,
                         String colonia, String ciudad, String estado, String cp, String pais, String telFijo,
                         String telCel, String correo, String contra, String tUsuario){
        progressDialog = new ModalProgressDialog(context,"Insertando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        userExist = -1;
        this.nombre = nombre;
        this.aPater = aPater;
        this.aMater = aMater;
        this.domicilio = domicilio;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.estado = estado;
        this.cp = cp;
        this.pais = pais;
        this.telFijo = telFijo;
        this.telCel = telCel;
        this.correo = correo;
        this.contra = contra;
        this.tUsuario = tUsuario;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar email user OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario WHERE email = '"+correo+"'; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    userExist = 1;
                else
                    userExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ userExist);
                rs.close();
                st.close();
                if(userExist == 0){
                    st = cnEnv.createStatement();
                    st.executeUpdate("INSERT INTO freedbtech_dbVeterinaria.propietario(nombre, " +
                            "aPaterno, aMaterno, domicilio, colonia, ciudad, estado, cp, pais, tel, cel, email, " +
                            "pass, tipoUsuario) VALUES('"+nombre+"','"+aPater+"','"+aMater+"','"+domicilio+"','"+
                            colonia+"','"+ciudad+"','"+estado+"','"+cp+"','"+pais+"','"+telFijo+"','"+telCel+"','"+
                            correo+"','"+contra+"','"+tUsuario+"');");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return userExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify User",
                        throwables.getMessage());
                userExist = -1;
            }
        }else{
            Log.println(Log.ERROR,"MySQLConnection","Conexión para Verify User FAIL");
        }
        return userExist;
    }

    @Override
    protected void onPostExecute(Integer userExist) {
        modalDialog.setTitle("Insertando Registro");
        progressDialog.hideProgressDialog();
        if(userExist == 0) {
            modalDialog.setMessage("Registro insertado con exito!");

        }else if(userExist == 1){
            modalDialog.setMessage("El email ya se encuentra registrado");
            modalDialog.showModalDialog();
        }
        modalDialog.showModalDialog();
    }
}
