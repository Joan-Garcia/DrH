package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drh.commands.VerifyDatabaseConnection;
import com.example.drh.commands.VerifyUser;
import com.example.drh.utils.ModalDialog;

public class Login extends AppCompatActivity {
    private EditText et_correo,et_contra;
    /*private conexionSQL admin;
    private SQLiteDatabase baseD;
    private Cursor fila;*/
    VerifyDatabaseConnection vdbc;
    VerifyUser vu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        et_correo=(EditText)findViewById(R.id.editCorreo);
        et_contra=(EditText)findViewById(R.id.editContra);

    }

    public void buscarU(View view){
        //vdbc = new VerifyDatabaseConnection(this);
        //vdbc.execute();
        String correo= et_correo.getText().toString();
        String contra=et_contra.getText().toString();
        //admin=new conexionSQL(this,"prueba",null,1);
        //baseD=admin.getReadableDatabase();
        if(checar(correo,contra)){
            /*fila = baseD.rawQuery("select correo from usuarios where correo= ? "
                    , new String[]{correo});
            if (fila.moveToFirst()) {
                fila = baseD.rawQuery("select correo, pass from usuarios where correo= ? and pass= ?"
                        , new String[]{correo,contra});
                if(fila.moveToFirst()){
                    baseD.close();
                    Intent a = new Intent(this, primerActivity.class);
                    startActivity(a);
                }else{
                    Toast.makeText(this,"LA CONTRASEÑA ES INCORRECTA",Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this,"EL USUARIO NO EXISTE",Toast.LENGTH_SHORT).show();
                baseD.close();
            }*/
            vu = new VerifyUser(this, et_correo, et_contra);
            vu.execute();



            /*try {

                if(vu.getUserExist()){
                    Intent a = new Intent(this, primerActivity.class);
                    startActivity(a);
                }
            }catch (InterruptedException e){
                Log.println(Log.ERROR, "VerifyUser", "Fallo al esperar el hilo");
            }*/


        }

    }

    public void crearUsuario(View view){
        Intent a = new Intent(this, crearUsuario.class);
        startActivity(a);
    }
    public boolean checar(String a, String b){
        if (a.isEmpty()){
            et_correo.setError("EL CAMPO CORREO NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(b.isEmpty()){
            et_contra.setError("EL CAMPO CONTRASEÑA NO PUEDE QUEDAR VACÍO");
            return false;
        }
        return true;
    }
    public void vaciar(){
        et_correo.setText("");
        et_contra.setText("");
    }
}