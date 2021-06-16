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
import android.widget.TextView;
import android.widget.Toast;

import com.example.drh.commands.DeleteMascota;
import com.example.drh.commands.SelectMascota;
import com.example.drh.commands.UpdateMascota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class buscar_mascota extends AppCompatActivity {
    EditText editIdMascota,editIdusuario, editNombreMascota, editRaza,editEspecie,editColor,
            editTatuaje, editMicrochip,editSexo, editFecha;
    String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo, idMas;
    Button btnBuscaM, btnActualizarM, btnEliminar, btnFecha;

    FrameLayout fy;
    SelectMascota sm;
    UpdateMascota um;
    DeleteMascota dm;
    int dia, mes, anyo, dia1, mes1, anyo1, bandera=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_mascota);
        asignaciones();
    }
    public void asignaciones(){
        editIdMascota=(EditText) findViewById(R.id.editIdMascota);
        editIdusuario=(EditText)findViewById(R.id.editIdUMas);
        editNombreMascota=(EditText)findViewById(R.id.editNMasco);
        editFecha=(EditText) findViewById(R.id.fechaMod);
        editRaza=(EditText)findViewById(R.id.editRaMas);
        editEspecie=(EditText)findViewById(R.id.editEsMas);
        editColor=(EditText)findViewById(R.id.editCoMas);
        editTatuaje=(EditText)findViewById(R.id.editTatMas);
        editMicrochip=(EditText)findViewById(R.id.editMiMas);
        editSexo=(EditText)findViewById(R.id.editSexMas);

        btnBuscaM=(Button)findViewById(R.id.btnBuscarMas);
        btnActualizarM=(Button)findViewById(R.id.btnActualizarMas);
        btnFecha=(Button)findViewById(R.id.botonFecha1);
        btnEliminar=(Button)findViewById(R.id.btnEliminarMas);
        fy=(FrameLayout)findViewById(R.id.frame1Mas);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String getCurrentDateTime = sdf.format(c.getTime());
        dia1 = c.get(Calendar.DAY_OF_MONTH);
        mes1=c.get(Calendar.MONTH);
        anyo1=c.get(Calendar.YEAR);

        btnBuscaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMas=editIdMascota.getText().toString();
                if(!idMas.isEmpty()){
                    sm = new SelectMascota(v.getContext(), editIdMascota, editIdusuario, editNombreMascota,
                            editFecha, editRaza, editEspecie, editColor, editTatuaje, editMicrochip, editSexo,
                            btnActualizarM,btnEliminar,fy);
                    sm.execute();
                }else{
                    editIdMascota.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
                }
            }
        });
        btnActualizarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtenerDatos();

                if(validar()){
                    um = new UpdateMascota(v.getContext(), idMas, usuario, nombreMascota, fechaNac,
                            raza, especie, color, tatuaje, microchip, sexo, btnEliminar, btnActualizarM,fy,editIdMascota);
                    um.execute();

                }
            }
        });



        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(buscar_mascota.this);
                alerta.setMessage("¿Estás seguro de que quieres eliminar el registro?\n\n"+
                        "Esta operación será irreversible.")

                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dm = new DeleteMascota(v.getContext(), idMas, fy,btnActualizarM,btnEliminar);
                                dm.execute();

                                editIdMascota.setText("");


                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar Mascota");
                titulo.show();
                //Insertar método de eliminar, usar  idMas

            }
        });
        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendario =  Calendar.getInstance();
                dia= calendario.get(Calendar.DAY_OF_MONTH);
                mes=calendario.get(Calendar.MONTH);
                anyo=calendario.get(Calendar.YEAR);

                DatePickerDialog dPD =  new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if(year>anyo1) {
                            Toast.makeText(v.getContext(), "EL AÑO NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                            bandera=1;
                        }else if(year==anyo1) {
                            if(monthOfYear>mes1){
                                Toast.makeText(v.getContext(), "EL MES NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                bandera=2;
                            }else if(monthOfYear==mes1){
                                if(dayOfMonth>dia1){
                                    Toast.makeText(v.getContext(), "EL DIA NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                    bandera=2;
                                }else if(dayOfMonth<=dia1){
                                    editFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                    bandera=4;
                                }
                            }else if(monthOfYear<mes1){
                                editFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }
                        }else if(year<anyo1){
                            editFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                            bandera=4;
                        }

                    }
                },dia,mes,anyo);

                dPD.show();
            }



        });
}

    public void obtenerDatos(){

        usuario=editIdusuario.getText().toString();
        nombreMascota=editNombreMascota.getText().toString();
        fechaNac=editFecha.getText().toString();
        if(!fechaNac.equals("AAAA/MM/DD")){
            bandera=4;
        }
        raza=editRaza.getText().toString();
        especie=editEspecie.getText().toString();
        color=editColor.getText().toString();
        tatuaje=editTatuaje.getText().toString();
        microchip=editMicrochip.getText().toString();
        sexo=editSexo.getText().toString();

    }


    public boolean validar(){

        if (usuario.isEmpty()) {
            editIdusuario.setError("EL CAMPO IDPROPIETARIO NO PUEDE QUEDAR VACÍO");
            return false;
        } else if(nombreMascota.isEmpty()) {
            editNombreMascota.setError("EL CAMPO NOMBRE NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(bandera!=4) {
            Toast.makeText(this,"INGRESA UNA FECHA",Toast.LENGTH_SHORT).show();
            return false;
        } else if (especie.isEmpty()) {
            editEspecie.setError("EL CAMPO ESPECIE NO PUEDE QUEDAR VACÍO");
            return false;
        } else if (raza.isEmpty()) {
            editRaza.setError("EL CAMPO RAZA NO PUEDE QUEDAR VACÍO");
            return false;
        } else if (color.isEmpty()) {
            editColor.setError("EL CAMPO COLOR NO PUEDE QUEDAR VACÍO");
            return false;
        } else if (validarS()) {
            editSexo.setError("INGRESE M/H");
            return false;
        }
        return true;
    }


    public boolean validarS(){
        if(sexo.equals("H")){
            return false;
        }else if (sexo.equals("M")){
            return false;
        }
        return true;
    }



}