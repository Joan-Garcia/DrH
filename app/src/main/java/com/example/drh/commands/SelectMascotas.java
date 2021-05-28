package com.example.drh.commands;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drh.adapDespa;
import com.example.drh.adapFech;
import com.example.drh.adapMasUsu;
import com.example.drh.adapVacunas;
import com.example.drh.lisEDespa;
import com.example.drh.lisEVacuna;
import com.example.drh.lisMasFech;

import com.example.drh.lisMascotaU;
import com.example.drh.utils.Connection;
import com.example.drh.utils.ModalDialog;
import com.example.drh.utils.ModalProgressDialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SelectMascotas extends AsyncTask<Void, Integer, Integer> {
    private Connection cn;
    private ModalProgressDialog progressDialog;
    private ModalDialog modalDialog;
    int status;
    int id;

    lisMasFech lMU;
    adapFech aF;
    ArrayList<lisMasFech> lisFech;
    RecyclerView rv;
    Context context;

    public SelectMascotas(int id, lisMasFech lMU, adapFech aF, ArrayList<lisMasFech> lisFech, RecyclerView rv, Context context) {
        this.id = id;
        this.lMU = lMU;
        this.aF = aF;
        this.lisFech = lisFech;
        this.rv = rv;
        this.context = context;
        status = 0;
        progressDialog = new ModalProgressDialog(this.context, "Recuperando Registros",
                "Por favor espere...", ProgressDialog.STYLE_SPINNER);
        modalDialog = new ModalDialog(this.context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.showModalProgressDialog();
    }


    @Override
    protected Integer doInBackground(Void... voids) {
        cn = new Connection();
        java.sql.Connection cnEnv = cn.connect();

        if (cnEnv != null) {
            try {
                Statement st = cnEnv.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT * FROM freedbtech_dbVeterinaria.vistavacunas " +
                                "  WHERE idVacuna IN(SELECT idVacuna FROM freedbtech_dbVeterinaria.vacuna " +
                                "  WHERE idMascota IN(Select idMascota from mascota where idPropietario = " + id + " ));");

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                String getCurrentDateTime = sdf.format(c.getTime());
                String a = "";

                status = 2;
                while (rs.next()) {
                    if (getCurrentDateTime.compareTo(rs.getString("proxFecha")) > 0) {
                        a = "#FFFF4444";
                    } else {
                        a = "#FF99CC00";
                    }

                    this.lMU = new lisMasFech(
                            rs.getString("idVacuna"),
                            rs.getString("Mascota"),
                            rs.getString("Vacuna"),
                            a,
                            rs.getDate("proxFecha"),
                            rs.getDate("fecha"),
                            "VACUNA");
                    this.lisFech.add(this.lMU);
                    status = 1;
                }

                rs = st.executeQuery("SELECT * FROM freedbtech_dbVeterinaria.vistadesparacitaciones " +
                        "  WHERE idDesparacitacion IN(SELECT idDesparacitacion FROM freedbtech_dbVeterinaria.desparacitacion " +
                        "  WHERE idMascota IN(Select idMascota from mascota where idPropietario = " + id + " ));");
                while (rs.next()) {
                    if (getCurrentDateTime.compareTo(rs.getString("proxFecha")) > 0) {
                        a = "#FFFF4444";
                    } else {
                        a = "#FF99CC00";
                    }

                    this.lMU = new lisMasFech(
                            rs.getString("idDesparacitacion"),
                            rs.getString("Mascota"),
                            rs.getString("producto"),
                            a,
                            rs.getDate("fecha"),
                            rs.getDate("proxFecha"),
                            "DESPARACITACIÓN");
                    this.lisFech.add(this.lMU);
                    status = 1;
                }
                workerThread();
                return status;
            } catch (SQLException throwables) {
                Log.println(Log.ERROR, "ALGO FALLA PUÑETAS",
                        throwables.getMessage());
                status = -1;
            }
        }
        return status;
    }

    @WorkerThread
    private void workerThread() {
        ContextCompat.getMainExecutor(context).execute(() -> {
            this.aF = new adapFech(this.lisFech, this.context);
            this.rv.setHasFixedSize(true);
            this.rv.setLayoutManager(new LinearLayoutManager(this.context));
            this.rv.setAdapter(this.aF);
        });
    }

    @Override
    protected void onPostExecute(Integer status) {
        modalDialog.setTitle("Recuperando Registros");
        progressDialog.hideProgressDialog();
        if (status == -1) {
            modalDialog.setMessage("Ocurrió un error al recuperar los registros.");
            modalDialog.showModalDialog();
        } else if (status == 1) {
            // modalDialog.setMessage("El email ya se encuentra registrado");
            // modalDialog.showModalDialog();
        } else if (status == 2) {
            modalDialog.setMessage("No se encontraron vacunas y/o desparasitaciones de tus mascostas."+
                    "\n\nAcude al hospital para comenzar un tratamiento!");
            modalDialog.showModalDialog();
        }
    }
}
