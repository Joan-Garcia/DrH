package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.drh.admin2;
import com.example.drh.primerActivity;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMascota extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    String usuarioId, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo;
    private int userExist = -1;

    public InsertMascota(Context context, String usuarioId, String nombreMascota, String fechaNac,
                         String raza, String especie, String color, String tatuaje, String microchip,
                         String sexo){
        progressDialog = new ModalProgressDialog(context,"Insertando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        this.usuarioId = usuarioId;
        this.nombreMascota = nombreMascota;
        this.fechaNac = fechaNac;
        this.raza = raza;
        this.especie = especie;
        this.color = color;
        this.tatuaje = tatuaje;
        this.microchip = microchip;
        this.sexo = sexo;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Credenciales OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario WHERE idPropietario = "+usuarioId+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    userExist = 1;
                else
                    userExist = 0;
                publishProgress();
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado"+ userExist);
                rs.close();
                st.close();
                if(userExist == 1){
                    st = cnEnv.createStatement();
                    Log.println(Log.INFO,"Dato","Fecha: "+ fechaNac);
                    st.executeUpdate("INSERT INTO mascota(idPropietario, nombre, fechaNac, especie, raza, sexo, color, tatuaje, microchip) VALUES ("+usuarioId+",'"+nombreMascota+"','"+fechaNac+"','"+especie+"','"+raza+"','"+sexo+"','"+color+"','"+tatuaje+"','"+microchip+"')");
                    st.close();
                }
                publishProgress();
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
        publishProgress();
        return userExist;
    }

    @Override
    protected void onPostExecute(Integer userExist) {
        modalDialog.setTitle("Insertando Registro");
        progressDialog.hideProgressDialog();
        if(userExist == 1) {
            modalDialog.setMessage("Registro insertado con exito!");
        }else{
            modalDialog.setMessage("El ID del usuario no existe.");
            modalDialog.showModalDialog();
        }
        modalDialog.showModalDialog();
    }

    public int getUserExist(){ return userExist;}
}
