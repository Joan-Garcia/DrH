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

import com.example.drh.commands.DeleteVacuna;
import com.example.drh.commands.SelectVacuna;
import com.example.drh.commands.UpdateVacuna;

public class buscarVacuna extends AppCompatActivity {

    Button btnActualizar, btnBuscar,btnEliminar;
    FrameLayout fy;
    EditText editIdMas,editVacuna, editProxFe, editidVac;
    String idMas, vacuna, proxF, idVac;

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
        editProxFe=(EditText)findViewById(R.id.editProxVacMod);

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
                alerta.setMessage("¿DESEAS ELIMINAR EL REGISTRO?")

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
                titulo.setTitle("ELIMINAR VACUNA");
                titulo.show();


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
    }
    public boolean validar(){
        if(idMas.isEmpty()){
            editIdMas.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(vacuna.isEmpty()){
            editVacuna.setError("EL CAMPO NOMBRE DE LA VACUNA NO PUEDE QUEDAR VACÍO");
            return false;
        }else {
            return true;
        }
    }



}