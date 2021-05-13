package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;

import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMascota extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    Context context;
    private EditText idMas, idUsu, nombreMascota, fechaNac, especie, raza, sexo, color, tatuaje, microchip;
    private int mascotaExist;
    FrameLayout fy;
    Button a,b;

    public SelectMascota(Context context, EditText idMas, EditText idUsu, EditText nombreMascota,
                         EditText fechaNac, EditText raza, EditText especie, EditText color, EditText tatuaje,
                         EditText microchip, EditText sexo, Button a, Button b, FrameLayout fy){
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Buscando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
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
        mascotaExist = -1;
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
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.mascota WHERE idMascota = "+idMas.getText().toString()+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1) {
                    mascotaExist = 1;

                    String idPro = rs.getString("idPropietario");
                    String nom = rs.getString("nombre");
                    String fec = rs.getString("fechaNac");
                    String esp = rs.getString("especie");
                    String raz = rs.getString("raza");
                    String sex = rs.getString("sexo");
                    String col = rs.getString("color");
                    String tat = rs.getString("tatuaje");
                    String mic = rs.getString("microchip");

                    workerThread(idPro, nom, fec, esp, raz, sex, col, tat, mic);
                }else
                    mascotaExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ mascotaExist);
                rs.close();
                st.close();
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
        modalDialog.setTitle("Buscando Registro");
        progressDialog.hideProgressDialog();
        if(mascotaExist == 0) {
            modalDialog.setMessage("El ID no existe");
            modalDialog.showModalDialog();
        }else if(mascotaExist == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }else if(mascotaExist == -1){
            modalDialog.setMessage("Error al intentar recuperar el registro");
            modalDialog.showModalDialog();
        }

    }

    @WorkerThread
    void workerThread(String idUsu, String nombreMascota, String fechaNac, String especie,
                      String raza, String sexo, String color, String tatuaje, String microchip) {
        ContextCompat.getMainExecutor(context).execute(()  -> {
            this.idUsu.setText(idUsu);
            this.nombreMascota.setText(nombreMascota);
            this.fechaNac.setText(fechaNac);
            this.especie.setText(especie);
            this.raza.setText(raza);
            this.sexo.setText(sexo);
            this.color.setText(color);
            this.tatuaje.setText(tatuaje);
            this.microchip.setText(microchip);
            this.a.setVisibility(View.VISIBLE);
            this.b.setVisibility(View.VISIBLE);
            this.fy.setVisibility(View.VISIBLE);
        });
    }
}
