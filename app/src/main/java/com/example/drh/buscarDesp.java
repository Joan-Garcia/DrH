package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

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

    Button btnActualizar, btnBuscar, btnLimpiar,btnEliminar;
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
        btnLimpiar=(Button)findViewById(R.id.btnLimpiarD);
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
                    ud = new UpdateDespa(v.getContext(), idDes, idMas, despa, proxF);
                    ud.execute();
                    btnLimpiar.setVisibility(View.VISIBLE);
                }
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar();
                editidDes.setText("");
                btnLimpiar.setVisibility(View.INVISIBLE);
                btnEliminar.setVisibility(View.INVISIBLE);
                btnActualizar.setVisibility(View.INVISIBLE);
                fy.setVisibility(View.INVISIBLE);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertar método de eliminar
                dd = new DeleteDespa(v.getContext(), idDes);
                dd.execute();
                vaciar();
                editidDes.setText("");
                btnLimpiar.setVisibility(View.INVISIBLE);
                btnEliminar.setVisibility(View.INVISIBLE);
                btnActualizar.setVisibility(View.INVISIBLE);
                fy.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void buscar(){
        idDes=editidDes.getText().toString();
        if(!idDes.isEmpty()){
            //Insertar método de busqueda
            sd = new SelectDespa(this, editidDes,editIdMas,editDes, editProxFe);
            sd.execute();
            btnActualizar.setVisibility(View.VISIBLE);
            btnEliminar.setVisibility(View.VISIBLE);
            fy.setVisibility(View.VISIBLE);
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
    public void vaciar(){
        editIdMas.setText("");
        editDes.setText("");
        editProxFe.setText("");
    }


}