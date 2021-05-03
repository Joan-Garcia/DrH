package com.example.drh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link crear_Despa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class crear_Despa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editIdMasDes, editFechaDes, editProducto, editProxFec;
    String idMasDes, fechaDes,producto, proxFec;
    Button btnDes;
    View vista;
    public crear_Despa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment crear_Despa.
     */
    // TODO: Rename and change types and number of parameters
    public static crear_Despa newInstance(String param1, String param2) {
        crear_Despa fragment = new crear_Despa();
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
        vista=inflater.inflate(R.layout.fragment_crear__despa, container, false);
        asignaciones();
        return vista;
    }
    public void asignaciones(){
        editIdMasDes=(EditText)vista.findViewById(R.id.editIdMascotDes);
        editFechaDes=(EditText)vista.findViewById(R.id.editFechaDes);
        editProducto=(EditText)vista.findViewById(R.id.editDes);
        editProxFec=(EditText)vista.findViewById(R.id.editProxDes);
        btnDes=(Button)vista.findViewById(R.id.btnDes);

        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();
                if (validar()) {
                    //INSERTAR MÉTODO PARA INGRESAR A LA BASE DE DATOS
                    Toast.makeText(vista.getContext(),"DESPARACITACIÓN APLICADA",Toast.LENGTH_SHORT).show();
                    vaciar();
                }
            }
        });
    }

    public void obtenerDatos(){
        idMasDes=editIdMasDes.getText().toString();
        fechaDes=editFechaDes.getText().toString();
        producto=editProducto.getText().toString();
        proxFec=editProxFec.getText().toString();
    }
    public boolean validar(){
        if(idMasDes.isEmpty()){
            editIdMasDes.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(fechaDes.isEmpty()){
            editFechaDes.setError("EL CAMPO FECHA VACUNACION NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(producto.isEmpty()){
            editProducto.setError("EL CAMPO NOMBRE PRODUCTO NO PUEDE QUEDAR VACÍO");
            return false;
        }else{
            return true;
        }
    }
    public void vaciar(){
        editIdMasDes.setText("");
        editFechaDes.setText("");
        editProducto.setText("");
        editProxFec.setText("");
    }
}