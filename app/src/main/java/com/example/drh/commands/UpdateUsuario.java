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

public class UpdateUsuario extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String id, nombre, aPater, aMater, domicilio, colonia, ciudad, estado, cp, pais, telFijo, telCel,
            correo, contra, tUsuario;
    private int userExist;

    public UpdateUsuario(Context context,String id, String nombre, String aPater, String aMater, String domicilio,
                         String colonia, String ciudad, String estado, String cp, String pais, String telFijo,
                         String telCel, String correo, String contra, String tUsuario){
        progressDialog = new ModalProgressDialog(context,"Actualizando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        userExist = -1;
        this.id = id;
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
                if(rs.getRow() == 1) {
                    if (!rs.getString("idPropietario").equals(id))
                        userExist = 1;
                    else
                        userExist = 0;
                }else
                    userExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ userExist);
                rs.close();
                st.close();
                if(userExist == 0){
                    st = cnEnv.createStatement();
                    st.executeUpdate("UPDATE freedbtech_dbVeterinaria.propietario SET nombre='"+nombre+"',aPaterno='"+aPater+"',aMaterno='"+
                            aMater+"',domicilio='"+domicilio+"',colonia='"+colonia+"',ciudad='"+ciudad+"',estado='"+
                            estado+"',cp='"+cp+"',pais='"+pais+"',tel='"+telFijo+"',cel='"+telCel+"',email='"+
                            correo+"',pass='"+contra+"',tipoUsuario='"+tUsuario+"' WHERE idPropietario = "+id+"; ");
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
        modalDialog.setTitle("Actualizando Registro");
        progressDialog.hideProgressDialog();
        if(userExist == 0) {
            modalDialog.setMessage("Registro actualizado con exito!");

        }else if(userExist == 1){
            modalDialog.setMessage("El email ya se encuentra registrado");
            modalDialog.showModalDialog();
        }else if(userExist == -1){
            modalDialog.setMessage("Error al intentar actualizar el registro");
        }
        modalDialog.showModalDialog();
    }
}
