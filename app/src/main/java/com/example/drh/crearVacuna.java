package com.example.drh;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.drh.commands.InsertVacuna;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class crearVacuna extends Fragment {


    private String mParam1;
    private String mParam2;
    EditText editIdMascota, editFechVac, editNomVac, editProxFechVac;
    String idMascota, fechVac, nomVac, proxFech;
    Button btnVac, btnLimpiar;
    View vista;
    int bandera=0;
    InsertVacuna iv;
    Button btnF;

    public crearVacuna() {
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
        vista= inflater.inflate(R.layout.fragment_crear_vacuna, container, false);

        asignaciones();
        return vista;
    }

    public void asignaciones(){
        editIdMascota= (EditText)vista.findViewById(R.id.editIdMascotaVac);
        editNomVac=(EditText)vista.findViewById(R.id.editVacuna);
        btnF=(Button)vista.findViewById(R.id.botonFechav);
        editProxFechVac=(EditText)vista.findViewById(R.id.fechaVacuna1);
        btnVac=(Button)vista.findViewById(R.id.btnVac);
        btnLimpiar=(Button) vista.findViewById(R.id.btnVaciarV);


        final Calendar calendario =  Calendar.getInstance();
        int diaD= calendario.get(Calendar.DAY_OF_MONTH);
        int mesD=calendario.get(Calendar.MONTH);
        int anyoD=calendario.get(Calendar.YEAR);


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

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "INGRESAR LA FECHA DEL DÍA ACTUAL SI LA VACUNA SOLO ES DE UNA APLICACIÓN", Toast.LENGTH_LONG).show();
                final Calendar calendario =  Calendar.getInstance();
                int dia= calendario.get(Calendar.DAY_OF_MONTH);
                int mes=calendario.get(Calendar.MONTH);
                int anyo=calendario.get(Calendar.YEAR);

                DatePickerDialog dPD =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if(year<anyoD) {
                            Toast.makeText(getActivity(), "EL AÑO NO PUEDE SER EN EL PASADO", Toast.LENGTH_SHORT).show();
                            bandera=1;
                        }else if(year==anyoD) {
                            if(monthOfYear<mesD){
                                Toast.makeText(getActivity(), "EL MES NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                bandera=2;
                            }else if(monthOfYear==mesD){
                                if(dayOfMonth<diaD){
                                    Toast.makeText(getActivity(), "EL DIA NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                    bandera=2;
                                }else if(dayOfMonth>=diaD){
                                    editProxFechVac.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

                                    bandera=4;
                                }
                            }else if(monthOfYear>mesD){
                                editProxFechVac.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }
                        }else if(year>anyoD){
                            editProxFechVac.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                            bandera=4;
                        }

                    }
                },dia,mes,anyo);

                dPD.show();
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
        }else if(bandera!=4){
         Toast.makeText(vista.getContext(),"INGRESA UNA FECHA",Toast.LENGTH_SHORT).show();
         return false;
        }
        return true;
    }
    public void vaciar(){
        editIdMascota.setText("");
        editNomVac.setText("");
        editProxFechVac.setText("");
    }

}