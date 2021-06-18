package com.example.drh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.drh.commands.DeleteVacuna;
import com.example.drh.commands.SelectVacuna;
import com.example.drh.commands.UpdateVacuna;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class buscarVacuna extends AppCompatActivity {

    Button btnActualizar, btnBuscar,btnEliminar, btnF;
    FrameLayout fy;
    EditText editIdMas,editVacuna, editProxFe, editidVac;
    String idMas, vacuna, proxF, idVac;
    int bandera=0;

    SelectVacuna sv;
    UpdateVacuna uv;
    DeleteVacuna dv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_vacunas);
        asignaciones();
    }
    public void asignaciones(){
        btnActualizar=(Button)findViewById(R.id.btnActualizarV);
        btnBuscar=(Button)findViewById(R.id.btnBuscarV);
        btnEliminar=(Button)findViewById(R.id.btnEliminarV);
        fy=(FrameLayout)findViewById(R.id.frameVac);

        editidVac=(EditText)findViewById(R.id.editIdVacunaMod);
        editIdMas=(EditText)findViewById(R.id.editIdMascotaVac);
        editVacuna=(EditText)findViewById(R.id.editVacMod);
        editProxFe=(EditText)findViewById(R.id.fechaVacuna1M);
        btnF=(Button)findViewById(R.id.botonFechaVM);

        final Calendar calendario =  Calendar.getInstance();
        int diaD= calendario.get(Calendar.DAY_OF_MONTH);
        int mesD=calendario.get(Calendar.MONTH);
        int anyoD=calendario.get(Calendar.YEAR);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtnerDatos();

                if(validar()){
                    //Insertar método para actualizar vacuna
                    uv = new UpdateVacuna(v.getContext(), idVac, idMas, vacuna, proxF, btnEliminar, btnActualizar, fy);
                    uv.execute();

                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertar método de eliminar
                AlertDialog.Builder alerta = new AlertDialog.Builder(buscarVacuna.this);
                alerta.setMessage("¿Estás seguro de que quieres eliminar el registro?\n\n"+
                        "Esta operación será irreversible.")

                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dv = new DeleteVacuna(v.getContext(), idVac, btnEliminar,btnActualizar,fy);
                                dv.execute();

                                editidVac.setText("");

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar Vacuna");
                titulo.show();


            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "INGRESAR LA FECHA DEL DÍA ACTUAL SI LA VACUNA SOLO ES DE UNA APLICACIÓN", Toast.LENGTH_LONG).show();
                final Calendar calendario =  Calendar.getInstance();
                int dia= calendario.get(Calendar.DAY_OF_MONTH);
                int mes=calendario.get(Calendar.MONTH);
                int anyo=calendario.get(Calendar.YEAR);

                DatePickerDialog dPD =  new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if(year<anyoD) {
                            Toast.makeText(v.getContext(), "EL AÑO NO PUEDE SER EN EL PASADO", Toast.LENGTH_SHORT).show();
                            bandera=1;
                        }else if(year==anyoD) {
                            if(monthOfYear<mesD){
                                Toast.makeText(v.getContext(), "EL MES NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                bandera=2;
                            }else if(monthOfYear==mesD){
                                if(dayOfMonth<diaD){
                                    Toast.makeText(v.getContext(), "EL DIA NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                    bandera=2;
                                }else if(dayOfMonth>=diaD){
                                    editProxFe.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

                                    bandera=4;
                                }
                            }else if(monthOfYear>mesD){
                                editProxFe.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }
                        }else if(year>anyoD){
                            editProxFe.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                            bandera=4;
                        }

                    }
                },dia,mes,anyo);

                dPD.show();
            }



        });
    }

    public void buscar(){
        idVac=editidVac.getText().toString();
    if(!idVac.isEmpty()){
        //Insertar método de busqueda
        sv = new SelectVacuna(this,editidVac, editIdMas, editVacuna, editProxFe,btnEliminar,btnActualizar,fy);
        sv.execute();

    }else{
        editidVac.setError("EL CAMPO IDVACUNA NO PUEDE QUEDAR VACÍO");
    }

    }


    public void obtnerDatos(){
        vacuna=editVacuna.getText().toString();
        idMas=editIdMas.getText().toString();
        proxF=editProxFe.getText().toString();
        if(!proxF.isEmpty()){
            bandera=4;
        }
    }
    public boolean validar(){
        if(idMas.isEmpty()){
            editIdMas.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(vacuna.isEmpty()){
            editVacuna.setError("EL CAMPO NOMBRE DE LA VACUNA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(bandera!=4){
            Toast.makeText(this,"INGRESA UNA FECHA",Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
        }




}