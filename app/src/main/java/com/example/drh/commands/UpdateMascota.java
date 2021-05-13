package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateMascota extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private String idMas, idUsu, nombreMascota, fechaNac, especie, raza, sexo, color, tatuaje, microchip;
    Button a, b;
    FrameLayout fy;
    private int mascotaExist;

    public UpdateMascota(Context context, String idMas, String idUsu, String nombreMascota,
                         String fechaNac, String raza, String especie, String color, String tatuaje,
                         String microchip, String sexo, Button a, Button b, FrameLayout fy){
        progressDialog = new ModalProgressDialog(context,"Actualizando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(context);
        mascotaExist = -1;
        this.idMas = idMas;
        this.idUsu = idUsu;
        this.nombreMascota = nombreMascota;
        this.fechaNac = fechaNac;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.color = color;
        this.tatuaje = tatuaje;
        this.microchip = microchip;
        this.a=a;
        this.b=b;
        this.fy=fy;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar Usuario OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario WHERE idPropietario = "+idUsu+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1)
                    mascotaExist = 1;
                else
                    mascotaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ mascotaExist);
                rs.close();
                st.close();
                if(mascotaExist == 1){
                    st = cnEnv.createStatement();
                    st.executeUpdate("UPDATE freedbtech_dbVeterinaria.mascota SET idPropietario='"+idUsu+"',nombre='"+nombreMascota+"',fechaNac='"+
                            fechaNac+"',especie='"+especie+"',raza='"+raza+"',sexo='"+sexo+"',color='"+
                            color+"',tatuaje='"+tatuaje+"',microchip='"+microchip+"' WHERE idMascota = "+idMas+"; ");
                    st.close();
                }
                cnEnv.close();
                cn.close();
                return mascotaExist;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR,"Fail Verify User",
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
        modalDialog.setTitle("Actualizando Registro");
        progressDialog.hideProgressDialog();
        if(mascotaExist == 1) {
            modalDialog.setMessage("Registro actualizado con exito!");
            this.a.setVisibility(View.INVISIBLE);
            this.b.setVisibility(View.INVISIBLE);
            this.fy.setVisibility(View.INVISIBLE);
        }else if(mascotaExist == 0){
            modalDialog.setMessage("El ID del propietario no existe");
            modalDialog.showModalDialog();
        }else if(mascotaExist == -1){
            modalDialog.setMessage("Error al intentar actualizar el registro");
        }
        modalDialog.showModalDialog();
    }
}
