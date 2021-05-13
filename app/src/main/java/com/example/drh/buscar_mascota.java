package com.example.drh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.drh.commands.DeleteMascota;
import com.example.drh.commands.SelectMascota;
import com.example.drh.commands.UpdateMascota;

public class buscar_mascota extends AppCompatActivity {
    EditText editIdMascota,editIdusuario, editNombreMascota,editFechaNac, editRaza,editEspecie,editColor,
            editTatuaje, editMicrochip,editSexo;
    String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo, idMas;
    Button btnBuscaM, btnActualizarM, btnEliminar;
    FrameLayout fy;
    SelectMascota sm;
    UpdateMascota um;
    DeleteMascota dm;
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
        editFechaNac=(EditText)findViewById(R.id.editFNacMas);
        editRaza=(EditText)findViewById(R.id.editRaMas);
        editEspecie=(EditText)findViewById(R.id.editEsMas);
        editColor=(EditText)findViewById(R.id.editCoMas);
        editTatuaje=(EditText)findViewById(R.id.editTatMas);
        editMicrochip=(EditText)findViewById(R.id.editMiMas);
        editSexo=(EditText)findViewById(R.id.editSexMas);

        btnBuscaM=(Button)findViewById(R.id.btnBuscarMas);
        btnActualizarM=(Button)findViewById(R.id.btnActualizarMas);

        btnEliminar=(Button)findViewById(R.id.btnEliminarMas);
        fy=(FrameLayout)findViewById(R.id.frame1Mas);

        btnBuscaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idMas=editIdMascota.getText().toString();
                if(!idMas.isEmpty()){
                    sm = new SelectMascota(v.getContext(), editIdMascota, editIdusuario, editNombreMascota,
                            editFechaNac, editRaza, editEspecie, editColor, editTatuaje, editMicrochip, editSexo,
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
                            raza, especie, color, tatuaje, microchip, sexo, btnEliminar, btnActualizarM,fy);
                    um.execute();

                }
            }
        });



        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(buscar_mascota.this);
                alerta.setMessage("¿DESEAS ELIMINAR EL REGISTRO?")

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
                titulo.setTitle("ELIMINAR MASCOTA");
                titulo.show();
                //Insertar método de eliminar, usar  idMas

            }
        });
    }
    public void obtenerDatos(){

        usuario=editIdusuario.getText().toString();
        nombreMascota=editNombreMascota.getText().toString();
        fechaNac=editFechaNac.getText().toString();
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
        } else if (fechaNac.length()!=10) {
            editFechaNac.setError("INGRESA LOS DATOS EN EL FORMATO PEDIDO AAAA-MM-DD");
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