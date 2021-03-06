package com.example.drh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.drh.commands.InsertUsuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link crearP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class crearP extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String [] tipoUsuario= new String[]{"","ADMINISTRADOR","USUARIO"};
    View vista;
    Button btnCrearP, btnLimpiar;
    EditText et_Nombre, et_APater, et_AMater,et_Domicilio, et_Colonia,et_Ciudad,et_Estado
            ,et_CP, et_Pais, et_TelFijo, et_TelCel, et_Correo, et_Contra, et_ConfContra, et_tipoUsuario;
    String nombre, aPater, aMater, domicilio, colonia,ciudad, estado,cp,pais,telFijo,telCel,
            correo,contra,confcontra,tUsuario;
    InsertUsuario iu;
    public crearP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment crearP.
     */
    // TODO: Rename and change types and number of parameters
    public static crearP newInstance(String param1, String param2) {
        crearP fragment = new crearP();
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
        vista= inflater.inflate(R.layout.fragment_crear_p, container, false);
        asignaciones(vista);

        return vista;
    }
    public void asignaciones(View v){

        //Asignaciones de EditText
        et_Nombre= (EditText)v.findViewById(R.id.editNombre);
        et_APater= (EditText)v.findViewById(R.id.editAppater);
        et_AMater= (EditText)v.findViewById(R.id.editApMater);
        et_Domicilio= (EditText)v.findViewById(R.id.editDomicilio);
        et_Colonia= (EditText)v.findViewById(R.id.editColonia);
        et_Ciudad= (EditText)v.findViewById(R.id.editCiudad);
        et_Estado= (EditText)v.findViewById(R.id.editEstado);
        et_CP= (EditText)v.findViewById(R.id.editCP);
        et_Pais= (EditText)v.findViewById(R.id.editPais);
        et_TelFijo= (EditText)v.findViewById(R.id.editTelFijo);
        et_TelCel= (EditText)v.findViewById(R.id.editTelCelu);
        et_Correo= (EditText)v.findViewById(R.id.editEmail);
        et_Contra= (EditText)v.findViewById(R.id.editContra);
        et_ConfContra= (EditText)v.findViewById(R.id.editConfContra);
        btnLimpiar=(Button) v.findViewById(R.id.btnVaciarP);
        btnCrearP= (Button)v.findViewById(R.id.btnCrearU);
        et_tipoUsuario= (EditText)vista.findViewById(R.id.editTipoUsuario);



       btnCrearP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
                if(validar()){
                    if(contra.equals(confcontra)){
                        if(tUsuario=="")
                            tUsuario="U";
                        //INSERTAR M??TODO QUE COMPRUEBE EN LA BASE DE DATOS SI EL USUARIO YA EXISTE
                        iu = new InsertUsuario(getActivity(), nombre, aPater, aMater, domicilio, colonia,
                                ciudad, estado, cp, pais, telFijo, telCel, correo, contra, tUsuario);
                        iu.execute();
                        btnLimpiar.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(vista.getContext(),"LAS CONTRASE??AS NO COINCIDEN",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

       btnLimpiar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               vaciar();
               btnLimpiar.setVisibility(View.INVISIBLE);
           }
       });
    }

    public boolean validar() {
        if (nombre.isEmpty()) {

            et_Nombre.setError("EL CAMPO NOMBRE NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (aPater.isEmpty()) {

            et_APater.setError("EL CAMPO APELLIDO PATERNO NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (telCel.isEmpty()) {

            et_TelCel.setError("EL CAMPO TEL??FONO CELULAR NO PUEDE QUEDAR VAC??O");
            return false;
        }else if(telCel.length()!=10){
            et_TelCel.setError("EL CAMPO TEL??FONO CELELULAR DEBE DE CONTENER 10 DIGITOS");
            return false;
        }else if (correo.isEmpty()) {

            et_Correo.setError("EL CAMPO CORREO NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (contra.isEmpty()) {
            et_Contra.setError("EL CAMPO CONTRASE??A NO PUEDE QUEDAR VAC??O");
            return false;
        } else if (confcontra.isEmpty()) {
            et_ConfContra.setError("EL CAMPO CONFIRMAR CONTRASE??A NO PUEDE QUEDAR VAC??O");
            return false;
        } else if(validarUsuario()){
                et_tipoUsuario.setError("LLENE EL CAMPO CON U/A");
            return false;
        }
        return true;
    }

    public void obtenerDatos(){

        nombre=et_Nombre.getText().toString();
        aPater=et_APater.getText().toString();
        aMater=et_AMater.getText().toString();
        domicilio=et_Domicilio.getText().toString();
        colonia=et_Colonia.getText().toString();
        ciudad=et_Ciudad.getText().toString();
        estado=et_Estado.getText().toString();
        cp=et_CP.getText().toString();
        pais=et_Pais.getText().toString();
        telFijo=et_TelFijo.getText().toString();
        telCel=et_TelCel.getText().toString();
        correo=et_Correo.getText().toString();
        contra=et_Contra.getText().toString();
        confcontra=et_ConfContra.getText().toString();
        tUsuario=et_tipoUsuario.getText().toString();
    }

    public void vaciar(){
        et_Nombre.setText("");
        et_APater.setText("");
        et_AMater.setText("");
        et_Domicilio.setText("");
        et_Colonia.setText("");
        et_Ciudad.setText("");
        et_Estado.setText("");
        et_CP.setText("");
        et_Pais.setText("");
        et_TelFijo.setText("");
        et_TelCel.setText("");
        et_Correo.setText("");
        et_Contra.setText("");
        et_ConfContra.setText("");
        et_tipoUsuario.setText("");

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
