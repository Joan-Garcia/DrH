package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
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

public class SelectUsuario extends AsyncTask<Void,Integer,Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    private EditText id, nombre, aPater, aMater, domicilio, colonia, ciudad, estado, cp, pais, telFijo, telCel,
            correo, contra, tUsuario;
    FrameLayout fy;
    private int userExist;
    Button a,b;
    Context context;

    public SelectUsuario(Context context, EditText id , EditText nombre, EditText aPater, EditText aMater, EditText domicilio,
                         EditText colonia, EditText ciudad, EditText estado, EditText cp, EditText pais, EditText telFijo,
                         EditText telCel, EditText correo, EditText contra, EditText tUsuario, Button actualizar,
                         Button eliminar, FrameLayout fy){
        this.context = context;
        progressDialog = new ModalProgressDialog(this.context,"Buscando Registro",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog= new ModalDialog(this.context);
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
        this.a=actualizar;
        this.b=eliminar;
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
            Log.println(Log.INFO,"MySQLConnection","Conexión para Verificar email user OK");
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.propietario " +
                        "WHERE idPropietario = "+id.getText().toString()+"; ");
                rs.next();
                Log.println(Log.INFO, "Dato", "Rows: "+ rs.getRow());
                if(rs.getRow() == 1) {
                    userExist = 1;
                    //Thread.sleep(1000);

                    String nom = rs.getString("nombre");
                    String ap = rs.getString("aPaterno");
                    String am = rs.getString("aMaterno");
                    String dom = rs.getString("domicilio");
                    String col = rs.getString("colonia");
                    String ciu = rs.getString("ciudad");
                    String est = rs.getString("estado");
                    String cp = rs.getString("cp");
                    String pai = rs.getString("pais");
                    String tel = rs.getString("tel");
                    String cel = rs.getString("cel");
                    String ema = rs.getString("email");
                    String pas = rs.getString("pass");
                    String tUs = rs.getString("tipoUsuario");

                    workerThread(nom, ap, am, dom, col, ciu, est, cp, pai, tel, cel, ema, pas, tUs);
                }else
                    userExist = 0;
                Log.println(Log.INFO,"VerifyUser","Usuario encontrado: "+ userExist);
                rs.close();
                st.close();
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
        modalDialog.setTitle("Buscando Registro");
        progressDialog.hideProgressDialog();
        if(userExist == 0) {
            modalDialog.setMessage("El ID no existe");
            modalDialog.showModalDialog();
        }else if(userExist == 1){
            //modalDialog.setMessage("El email ya se encuentra registrado");
            //modalDialog.showModalDialog();
        }

    }

    @WorkerThread
    void workerThread(String nombre, String aPater, String aMater, String domicilio,
                      String colonia, String ciudad, String estado, String cp, String pais, String telFijo,
                      String telCel, String correo, String contra, String tUsuario) {
        ContextCompat.getMainExecutor(context).execute(()  -> {
            this.nombre.setText(nombre);
            this.aPater.setText(aPater);
            this.aMater.setText(aMater);
            this.domicilio.setText(domicilio);
            this.colonia.setText(colonia);
            this.ciudad.setText(ciudad);
            this.estado.setText(estado);
            this.cp.setText(cp);
            this.pais.setText(pais);
            this.telFijo.setText(telFijo);
            this.telCel.setText(telCel);
            this.correo.setText(correo);
            this.contra.setText(contra);
            this.tUsuario.setText(tUsuario);
            this.a.setVisibility(View.VISIBLE);
            this.b.setVisibility(View.VISIBLE);
            this.fy.setVisibility(View.VISIBLE);
        });
    }
}
