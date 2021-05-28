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

import com.example.drh.commands.DeleteDespa;
import com.example.drh.commands.SelectDespa;
import com.example.drh.commands.UpdateDespa;

public class buscarDesp extends AppCompatActivity {

    Button btnActualizar, btnBuscar,btnEliminar;
    FrameLayout fy;
    EditText editIdMas,editDes, editProxFe, editidDes;
    String idMas, despa, proxF, idDes;
    SelectDespa sd;
    UpdateDespa ud;
    DeleteDespa dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_desp);
        asignaciones();
    }
    public void asignaciones(){
        btnActualizar=(Button)findViewById(R.id.btnActualizarD);
        btnBuscar=(Button)findViewById(R.id.btnBuscarDes);

        btnEliminar=(Button)findViewById(R.id.btnEliminarD);
        fy=(FrameLayout)findViewById(R.id.frameDes);

        editidDes=(EditText)findViewById(R.id.editIdDesMod);
        editIdMas=(EditText)findViewById(R.id.editIdMascotaDes);
        editDes=(EditText)findViewById(R.id.editDesMod);
        editProxFe=(EditText)findViewById(R.id.editProxDesMod);

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
                    ud = new UpdateDespa(v.getContext(), idDes, idMas, despa, proxF, btnActualizar,btnEliminar,fy);
                    ud.execute();

                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertar método de eliminar
                AlertDialog.Builder alerta = new AlertDialog.Builder(buscarDesp.this);
                alerta.setMessage("¿Estás seguro de que quieres eliminar el registro?\n\n"+
                        "Esta operación será irreversible.")

                        .setCancelable(true)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dd = new DeleteDespa(v.getContext(), idDes, btnActualizar,btnEliminar,fy);
                                dd.execute();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar Desparasitación");
                titulo.show();


            }
        });
    }

    public void buscar(){
        idDes=editidDes.getText().toString();
        if(!idDes.isEmpty()){
            //Insertar método de busqueda
            sd = new SelectDespa(this, editidDes,editIdMas,editDes, editProxFe,btnActualizar,btnEliminar,fy);
            sd.execute();

        }else{
            editidDes.setError("EL CAMPO IDDESPARACITACIÓN NO PUEDE QUEDAR VACÍO");
        }
    }


    public void obtnerDatos(){
        despa=editDes.getText().toString();
        idMas=editIdMas.getText().toString();
        proxF=editProxFe.getText().toString();
    }
    public boolean validar(){
        if(idMas.isEmpty()){
            editIdMas.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(despa.isEmpty()){
            editDes.setError("EL CAMPO NOMBRE DEL PRODUCTO APLICADO NO PUEDE QUEDAR VACÍO");
            return false;
        }else {
            return true;
        }
    }



}