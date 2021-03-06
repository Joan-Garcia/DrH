package com.example.drh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.drh.commands.DeleteUsuario;
import com.example.drh.commands.SelectUsuario;
import com.example.drh.commands.UpdateUsuario;

public class buscarUsuario extends AppCompatActivity {

    Button btnBuscar, btnActualizar, btnEliminar;
    EditText textId, textNombre,textAPater, textAMater,textDom, textColo,textCiu, textEdo, textCP,
            textPais,textTel, textTelCel,textCorreo,textContra,textTUser;
    String id;
    String nombre, aPater, aMater, domicilio, colonia,ciudad, estado,cp,pais,telFijo,telCel,
    correo,contra,tUsuario;
    FrameLayout fy;
    SelectUsuario su;
    UpdateUsuario uu;
    DeleteUsuario du;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);
        asignaciones();
    }

    public void asignaciones(){
        btnBuscar=(Button) findViewById(R.id.btnBuscar);
        btnActualizar=(Button) findViewById(R.id.btnActualizar);
        btnEliminar=(Button)findViewById(R.id.btnEliminarUser);
        textId=(EditText) findViewById(R.id.editIdUsuario);
        fy=(FrameLayout) findViewById(R.id.frame1);

        //Asignaciones de los campos de texto
        textNombre=(EditText)findViewById(R.id.editNombreMod);
        textAPater=(EditText)findViewById(R.id.editAppaterMod);
        textAMater=(EditText)findViewById(R.id.editApMaterMod);
        textDom=(EditText)findViewById(R.id.editDomicilioMod);
        textColo=(EditText)findViewById(R.id.editColoniaMod);
        textCiu=(EditText)findViewById(R.id.editCiudadMod);
        textEdo=(EditText)findViewById(R.id.editEstadoMod);
        textCP=(EditText)findViewById(R.id.editCPMod);
        textPais=(EditText)findViewById(R.id.editPaisMod);
        textTel=(EditText)findViewById(R.id.editTelFijoMod);
        textTelCel=(EditText)findViewById(R.id.editTelCeluMod);
        textCorreo=(EditText)findViewById(R.id.editEmailMod);
        textContra=(EditText)findViewById(R.id.editContraMod);
        textTUser=(EditText)findViewById(R.id.editTipoUsuarioMod);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerId();
                if(validarId()){
                    //INSERTAR M??TODO DE B??SQUEDA e inserci??n en los campos
                    su = new SelectUsuario(v.getContext(), textId, textNombre,textAPater, textAMater,textDom, textColo,textCiu, textEdo, textCP,
                            textPais,textTel, textTelCel,textCorreo,textContra,textTUser,btnActualizar,btnEliminar,fy);
                    su.execute();

                }
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
                if(validar()){
                    //Insertar m??todo para validar el correo si exxiste ya en la bd
                    uu = new UpdateUsuario(v.getContext(), id, nombre, aPater, aMater, domicilio, colonia,ciudad, estado,cp,pais,telFijo,telCel,
                            correo,contra,tUsuario,fy,btnEliminar,btnActualizar);
                    uu.execute();


                }
                }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertar m??todo para eliminar, usar id
                AlertDialog.Builder alerta = new AlertDialog.Builder(buscarUsuario.this);
                alerta.setMessage("??Est??s seguro de que quieres eliminar el registro?\n\n"+
                        "Esta operaci??n ser?? irreversible.")
                        .setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                du = new DeleteUsuario(v.getContext(), id, btnEliminar, btnActualizar, fy);
                                du.execute();
                                textId.setText("");

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar Usuario");
                titulo.show();


            }
        });
    }
    public void obtenerId(){
        id=textId.getText().toString();

    }
    public boolean validarId(){
        if(id.isEmpty()) {
            textId.setError("EL CAMPO NO PUEDE QUEDAR VAC??O");
            return false;

        }
        return true;
    }

    public void obtenerDatos(){
        nombre=textNombre.getText().toString();
        aPater=textAPater.getText().toString();
        aMater=textAMater.getText().toString();
        domicilio=textDom.getText().toString();
        colonia=textColo.getText().toString();
        ciudad=textCiu.getText().toString();
        estado=textEdo.getText().toString();
        cp=textCP.getText().toString();
        pais=textPais.getText().toString();
        telFijo=textTel.getText().toString();
        telCel=textTelCel.getText().toString();
        correo=textCorreo.getText().toString();
        contra=textContra.getText().toString();
        tUsuario=textTUser.getText().toString();
    }


    public boolean validar() {
        if (nombre.isEmpty()) {
            textNombre.setError("EL CAMPO NOMBRE NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (aPater.isEmpty()) {
            textAPater.setError("EL CAMPO APELLIDO PATERNO NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (telCel.isEmpty()) {
            textTelCel.setError("EL CAMPO TEL??FONO CELELULAR NO PUEDE QUEDAR VAC??O");
            return false;
        }else if(telCel.length()!=10){
            textTelCel.setError("EL CAMPO TEL??FONO CELELULAR DEBE DE CONTENER 10 DIGITOS");
            return false;
        } else if (correo.isEmpty()) {
            textCorreo.setError("EL CAMPO CORREO NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (contra.isEmpty()) {
            textContra.setError("EL CAMPO CONTRASE??A NO PUEDE QUEDAR VAC??O");
            return false;
        } else if(validarUsuario()){
            textTUser.setError("LLENE EL CAMPO CON U/A");
            return false;
        }
        return true;
    }
    public boolean validarUsuario(){
        if(tUsuario.equals("")){
            return false;
        }else if(tUsuario.equals("U")){
            return false;
        }else if(tUsuario.equals("A")){
            return false;
        }
        return true;
    }

}