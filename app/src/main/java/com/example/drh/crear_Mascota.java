package com.example.drh;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drh.commands.InsertMascota;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class crear_Mascota extends Fragment {



    EditText editIdusuario, editNombreMascota, editRaza,editEspecie,editColor,
            edirTatuaje, editMicrochip,editSexo;
    String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo, fecha;
    Button btnCrearM, btnVaciar, btnFecha;
    TextView testFecha;
    View vista;
    InsertMascota vui;
    Integer  dia, mes, anyo, dia1=0, mes1=0 ,anyo1=0;
    int arre[] = new int[3], bandera=0;



    public crear_Mascota() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_crearmascota, container, false);
        asignaciones(vista);
        return vista;

    }

    public void asignaciones(View v){
        editIdusuario = (EditText)vista.findViewById(R.id.editPropietario);
        editNombreMascota = (EditText)vista.findViewById(R.id.editNombreMasco);

        testFecha=(TextView) vista.findViewById(R.id.textoFecha);

        editRaza = (EditText)vista.findViewById(R.id.editRaza);
        editEspecie = (EditText)vista.findViewById(R.id.editEspecie);
        editColor = (EditText)vista.findViewById(R.id.editColor);
        edirTatuaje = (EditText)vista.findViewById(R.id.editTatuaje);
        editMicrochip = (EditText)vista.findViewById(R.id.editMicrochip);
        editSexo= (EditText) vista.findViewById(R.id.editSexo1);
        btnCrearM = (Button) vista.findViewById(R.id.btnCrearMascota);
        btnVaciar=(Button) vista.findViewById(R.id.btnVaciarM);
        btnFecha=(Button) vista.findViewById(R.id.botonFecha);

        btnCrearM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtenerDatos();
                Calendar c = Calendar.getInstance();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy ");
                String getCurrentDateTime = sdf.format(c.getTime());
                 dia1 = c.get(Calendar.DAY_OF_MONTH);
                 mes1=c.get(Calendar.MONTH);
                 anyo1=c.get(Calendar.YEAR);
                if(validar()){
                    //INSERTAR MÉTODO QUE COMPRUEBE EN LA BASE DE DATOS SI EL USUARIO YA EXISTE
                    vui = new InsertMascota(getActivity(), usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo);
                    vui.execute();

                    btnVaciar.setVisibility(View.VISIBLE);
                }
            }
        });

       btnVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vaciar();
                btnVaciar.setVisibility(View.INVISIBLE);
            }
        });
       btnFecha.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            final Calendar calendario =  Calendar.getInstance();
            dia= calendario.get(Calendar.DAY_OF_MONTH);
            mes=calendario.get(Calendar.MONTH);
            anyo=calendario.get(Calendar.YEAR);

               DatePickerDialog dPD =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                       if(year>anyo1) {
                           Toast.makeText(getActivity(), "EL AÑO NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                           bandera=1;
                       }else if(year==anyo1) {
                           if(monthOfYear>mes1){
                               Toast.makeText(getActivity(), "EL MES NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                               bandera=2;
                           }else if(monthOfYear==mes1){
                               if(dayOfMonth>dia1){
                                   Toast.makeText(getActivity(), "EL DIA NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                   bandera=2;
                               }else if(dayOfMonth<=dia1){
                                   testFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                   bandera=4;
                               }
                           }else if(monthOfYear<mes1){
                               testFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                               bandera=4;
                           }
                       }else if(year<anyo1){
                           testFecha.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                           bandera=4;
                       }

                   }
               },dia,mes,anyo);
               dPD.show();
           }

       } );

    }


    public void obtenerDatos(){
        //String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo;
        usuario= editIdusuario.getText().toString();
        nombreMascota= editNombreMascota.getText().toString();
        raza= editRaza.getText().toString();
        especie= editEspecie.getText().toString();
        color= editColor.getText().toString();
        tatuaje= edirTatuaje.getText().toString();
        microchip= editMicrochip.getText().toString();
        sexo=editSexo.getText().toString();
        fechaNac=testFecha.getText().toString();

    }

    public boolean validar() {
        if (usuario.isEmpty()) {
            editIdusuario.setError("EL CAMPO IDPROPIETARIO NO PUEDE QUEDAR VACÍO");
            return false;
            } else if(nombreMascota.isEmpty()) {
            editNombreMascota.setError("EL CAMPO NOMBRE NO PUEDE QUEDAR VACÍO");
            return false;

            } else if(bandera!=4){
            testFecha.setText("AAAA/MM/DD");
            Toast.makeText(getActivity(),"INGRESA UNA FECHA",Toast.LENGTH_SHORT).show();
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

    public void vaciar(){


                editIdusuario.setText("");
                editNombreMascota.setText("");
                testFecha.setText("DD/MM/AAAA");
                editRaza.setText("");
                editEspecie.setText("");
                editColor.setText("");
                edirTatuaje.setText("");
                editMicrochip.setText("");
                editSexo.setText("");
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