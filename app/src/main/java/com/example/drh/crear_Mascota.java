package com.example.drh;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.drh.commands.InsertMascota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link crear_Mascota#newInstance} factory method to
 * create an instance of this fragment.
 */
public class crear_Mascota extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editIdusuario, editNombreMascota,editFechaNac, editRaza,editEspecie,editColor,
            edirTatuaje, editMicrochip,editSexo;
    String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo;
    Button btnCrearM, btnVaciar;
    View vista;
    InsertMascota vui;
    Integer a=0;


    public crear_Mascota() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mascota.
     */
    // TODO: Rename and change types and number of parameters
    public static crear_Mascota newInstance(String param1, String param2) {
        crear_Mascota fragment = new crear_Mascota();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        editFechaNac = (EditText)vista.findViewById(R.id.editFechaNac);
        editNombreMascota = (EditText)vista.findViewById(R.id.editNombreMasco);
        editRaza = (EditText)vista.findViewById(R.id.editRaza);
        editEspecie = (EditText)vista.findViewById(R.id.editEspecie);
        editColor = (EditText)vista.findViewById(R.id.editColor);
        edirTatuaje = (EditText)vista.findViewById(R.id.editTatuaje);
        editMicrochip = (EditText)vista.findViewById(R.id.editMicrochip);
        editSexo= (EditText) vista.findViewById(R.id.editSexo1);
        btnCrearM = (Button) vista.findViewById(R.id.btnCrearMascota);
        btnVaciar=(Button) vista.findViewById(R.id.btnVaciarM);


        btnCrearM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                obtenerDatos();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
                String getCurrentDateTime = sdf.format(c.getTime());
                if(validar(getCurrentDateTime)){
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

    }


    public void obtenerDatos(){
        //String usuario, nombreMascota, fechaNac,raza, especie, color, tatuaje,microchip, sexo;
        usuario= editIdusuario.getText().toString();
        nombreMascota= editNombreMascota.getText().toString();
        fechaNac= editFechaNac.getText().toString();
        raza= editRaza.getText().toString();
        especie= editEspecie.getText().toString();
        color= editColor.getText().toString();
        tatuaje= edirTatuaje.getText().toString();
        microchip= editMicrochip.getText().toString();
        sexo=editSexo.getText().toString().trim();

    }

    public boolean validar(String fecha) {


        if (usuario.isEmpty()) {
            editIdusuario.setError("EL CAMPO IDPROPIETARIO NO PUEDE QUEDAR VACÍO");
            return false;
            }else if(nombreMascota.isEmpty()) {
            editNombreMascota.setError("EL CAMPO NOMBRE NO PUEDE QUEDAR VACÍO");
            return false;
            }else if (fechaNac.length()!=10) {
                editFechaNac.setError("INGRESA LOS DATOS EN EL FORMATO PEDIDO AAAA-MM-DD");
                return false;
            }else if(fecha.compareTo(fechaNac)<0) {
            editFechaNac.setError("LA MASCOTA NO PUEDE TENER UNA FECHA DE NACIMIENTO MAYOR AL DIA DE HOY");
            return false;
            }else if (especie.isEmpty()) {
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
                editFechaNac.setText("");
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