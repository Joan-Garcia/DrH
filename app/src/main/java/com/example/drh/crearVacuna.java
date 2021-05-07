package com.example.drh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drh.commands.InsertVacuna;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link crearVacuna#newInstance} factory method to
 * create an instance of this fragment.
 */
public class crearVacuna extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editIdMascota, editFechVac, editNomVac, editProxFechVac;
    String idMascota, fechVac, nomVac, proxFech;
    Button btnVac, btnLimpiar;
    View vista;
    InsertVacuna iv;
    public crearVacuna() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment crearVacuna.
     */
    // TODO: Rename and change types and number of parameters
    public static crearVacuna newInstance(String param1, String param2) {
        crearVacuna fragment = new crearVacuna();
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
        vista= inflater.inflate(R.layout.fragment_crear_vacuna, container, false);
        asignaciones();
        return vista;
    }

    public void asignaciones(){
        editIdMascota= (EditText)vista.findViewById(R.id.editIdMascotaVac);

        editNomVac=(EditText)vista.findViewById(R.id.editVacuna);
        editProxFechVac=(EditText)vista.findViewById(R.id.editProxVac);
        btnVac=(Button)vista.findViewById(R.id.btnVac);
        btnLimpiar=(Button) vista.findViewById(R.id.btnVaciarV);

        btnVac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
                if(validar()){
                    //INSERTAR MÉTODO PARA INGRESAR A LA BASE DE DATOS
                    iv = new InsertVacuna(getActivity(), idMascota, nomVac, proxFech);
                    iv.execute();
                    btnLimpiar.setVisibility(View.VISIBLE);

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

    public void obtenerDatos(){
        idMascota=editIdMascota.getText().toString();

        nomVac=editNomVac.getText().toString();
        proxFech=editProxFechVac.getText().toString();
    }

    public boolean validar(){
        if(idMascota.isEmpty()){
            editIdMascota.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(nomVac.isEmpty()){
            editNomVac.setError("EL CAMPO NOMBRE DE LA VACUNA NO PUEDE QUEDAR VACÍO");
            return false;
        }else {
            return true;
        }
    }
    public void vaciar(){
        editIdMascota.setText("");
        editNomVac.setText("");
        editProxFechVac.setText("");
    }

}