package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drh.commands.VerifyUser;

public class crearUsuario extends AppCompatActivity {
    private EditText et_Correo, et_Nombre, et_Contra, et_confC;
    private conexionSQL admin;
    private SQLiteDatabase baseD;
    private Cursor fila;
    private VerifyUser vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        et_Correo = (EditText) findViewById(R.id.editCo);
        et_Nombre = (EditText) findViewById(R.id.editNombre);
        et_Contra = (EditText) findViewById(R.id.editPass);
        et_confC = (EditText) findViewById(R.id.editConfCon);

    }

    public void alLoggin(View view) {
        patras();
    }

    public void patras() {
        Intent a = new Intent(this, Login.class);
        startActivity(a);
    }

    public void buscarU(View v) {
        String correo = et_Correo.getText().toString();
        String nombre = et_Nombre.getText().toString();
        String contra = et_Contra.getText().toString();
        String confC = et_confC.getText().toString();
        admin=new conexionSQL(this,"prueba",null,1);
        baseD= admin.getWritableDatabase();
        if (checar(correo,nombre,contra,confC)){
           if (validar( contra, confC)) {
                fila = baseD.rawQuery("select correo, pass from usuarios where correo= ? "
                        , new String[]{correo});
                if (fila.moveToFirst()) {
                    Toast.makeText(this, "EL USUARIO YA EXISTE", Toast.LENGTH_SHORT).show();
                    baseD.close();
                } else {
                    ingresar(correo, nombre,contra);
                }

            } else {
                Toast.makeText(this, "LAS CONSTRASEÑAS NO COINCIDEN", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public boolean checar(String a, String b, String c, String d) {
        if (a.isEmpty()) {
            et_Correo.setError("EL CAMPO CORREO NO PUEDE ESTAR VACÍO");
            return false;
        } else if (b.isEmpty()) {
            et_Nombre.setError("EL CAMPO NOMBRE NO PUEDE ESTAR VACÍO");
            return false;
        } else if (c.isEmpty()) {
            et_Contra.setError("EL CAMPO CONTRA NO PUEDE ESTAR VACÍO");
            return false;
        } else if (d.isEmpty()) {
            et_confC.setError("EL CAMPO CONFIRMAR CONTRASEÑA NO PUEDE ESTAR VACÍO");
            return false;
        }
        return true;
    }


    public boolean validar(String a, String b) {
       if(a.equals(b)){
        return true;
    }
       return false;
    }

    public void ingresar(String correo, String nombre, String contra) {
        ContentValues contenido = new ContentValues();
        contenido.put("correo", correo);
        contenido.put("pass", contra);
        contenido.put("nombre",nombre);
        baseD.insert("USUARIOS",null,contenido);
        baseD.close();
        Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                patras();
            }
        }, 500);

    }
}
