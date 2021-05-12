package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

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

    Button btnActualizar, btnBuscar, btnLimpiar,btnEliminar;
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
        btnLimpiar=(Button)findViewById(R.id.btnLimpiarV);
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
                    uv = new UpdateVacuna(v.getContext(), idVac, idMas, vacuna, proxF);
                    uv.execute();
                    btnLimpiar.setVisibility(View.VISIBLE);
                }
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar();
                editidVac.setText("");
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
                dv = new DeleteVacuna(v.getContext(), idVac);
                dv.execute();
                vaciar();
                editidVac.setText("");
                btnLimpiar.setVisibility(View.INVISIBLE);
                btnEliminar.setVisibility(View.INVISIBLE);
                btnActualizar.setVisibility(View.INVISIBLE);
                fy.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void buscar(){
        idVac=editidVac.getText().toString();
    if(!idVac.isEmpty()){
        //Insertar método de busqueda
        sv = new SelectVacuna(this,editidVac, editIdMas, editVacuna, editProxFe);
        sv.execute();
        btnActualizar.setVisibility(View.VISIBLE);
        btnEliminar.setVisibility(View.VISIBLE);
        fy.setVisibility(View.VISIBLE);
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
    public void vaciar(){
        editIdMas.setText("");
        editVacuna.setText("");
        editProxFe.setText("");
    }


}