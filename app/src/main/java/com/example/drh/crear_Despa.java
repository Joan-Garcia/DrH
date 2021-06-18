package com.example.drh;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drh.commands.InsertDesparasitacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class crear_Despa extends Fragment {

    EditText editIdMasDes, editFechaDes, editProducto;
    String idMasDes, fechaDes,producto, proxFec;
    Button btnDes, btnLimpiar, btnF;
    View vista;
    InsertDesparasitacion id;
    int bandera=0;
    final Calendar calendario =  Calendar.getInstance();
    int diaD= calendario.get(Calendar.DAY_OF_MONTH);
    int mesD=calendario.get(Calendar.MONTH);
    int anyoD=calendario.get(Calendar.YEAR);
    public crear_Despa() {
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
        vista=inflater.inflate(R.layout.fragment_crear__despa, container, false);
        asignaciones();
        return vista;
    }
    public void asignaciones(){
        editIdMasDes=(EditText)vista.findViewById(R.id.editIdMascotDes);
        editProducto=(EditText)vista.findViewById(R.id.editDes);
        editFechaDes=(EditText)vista.findViewById(R.id.fechaDes1);
        btnDes=(Button)vista.findViewById(R.id.btnDes);
        btnLimpiar=(Button) vista.findViewById(R.id.btnVaciarD);
        btnF=(Button)vista.findViewById(R.id.botonFechaD);



        llenarFecha(diaD,mesD,anyoD);
        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerDatos();

                if (validar()) {
                    //INSERTAR MÉTODO PARA INGRESAR A LA BASE DE DATOS
                    id = new InsertDesparasitacion(getActivity(), idMasDes, producto, proxFec);
                    id.execute();
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
                Toast.makeText(vista.getContext(),"SE RECOMIENDA DESPARACITAR CADA 6 MESES",Toast.LENGTH_LONG).show();
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
                            editFechaDes.setText("");
                        }else if(year==anyoD) {
                            if(monthOfYear<mesD){
                                Toast.makeText(getActivity(), "EL MES NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                editFechaDes.setText("");
                                bandera=2;
                            }else if(monthOfYear==mesD){
                                if(dayOfMonth<diaD){
                                    Toast.makeText(getActivity(), "EL DIA NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                    editFechaDes.setText("");
                                    bandera=2;
                                }else if(dayOfMonth>=diaD){
                                    editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                    bandera=4;
                                }
                            }else if(monthOfYear>mesD){
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }

                        }else if(year>anyoD){
                            editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                            bandera=4;
                        }

                    }
                },dia,mes,anyo);

                dPD.show();
            }
            });

}

    public void obtenerDatos(){
        idMasDes=editIdMasDes.getText().toString();
        producto=editProducto.getText().toString();
        proxFec=editFechaDes.getText().toString();
    }
    public boolean validar(){
        if(idMasDes.isEmpty()){
            editIdMasDes.setError("EL CAMPO IDMASCOTA NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(producto.isEmpty()) {
            editProducto.setError("EL CAMPO NOMBRE PRODUCTO NO PUEDE QUEDAR VACÍO");
            return false;
        }else if(bandera!=4){
            editFechaDes.setError("LA PRÓXIMA FECHA DE DESPARACITACIÓN NO PUEDE SER EN EL PASADO");
            return false;
        }
            return true;
        }

    public void vaciar(){
        editIdMasDes.setText("");
        editProducto.setText("");
        Toast.makeText(vista.getContext(),"FECHA PROXIMA ASIGNADA AUTOMÁTICAMENTE",Toast.LENGTH_SHORT).show();
        llenarFecha(diaD,mesD,anyoD);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(vista.getContext(),"FECHA PROXIMA ASIGNADA AUTOMÁTICAMENTE",Toast.LENGTH_SHORT).show();
        llenarFecha(diaD,mesD,anyoD);
    }

    public void llenarFecha(int dia, int mes, int year) {

        bandera=4;
    if(mes<=6){
        mes=mes+6;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==7){
        mes=1;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==8){
        mes=2;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==9){
        mes=3;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==10){
        mes=4;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==11){
        mes=5;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }else if(mes==12){
        mes=6;
        year++;
        editFechaDes.setText(year+"/"+(mes+1)+"/"+dia);
    }

}
}
/*
if(monthOfYear<mesD){
                                Toast.makeText(getActivity(), "EL MES NO PUEDE SER MENOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                editFechaDes.setText("");
                                bandera=2;
                            }else if(monthOfYear==mesD){
                                if(dayOfMonth<diaD){
                                    Toast.makeText(getActivity(), "EL DIA NO PUEDE SER MAYOR AL ACTUAL", Toast.LENGTH_SHORT).show();
                                    editFechaDes.setText("");
                                    bandera=2;
                                }else if(dayOfMonth>=diaD){
                                    editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

                                    bandera=4;
                                }
                            }else if(monthOfYear>mesD){
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }












                            if(monthOfYear<=6){
                                monthOfYear=monthOfYear+6;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==7){
                                monthOfYear=1;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==8){
                                monthOfYear=2;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==9){
                                monthOfYear=3;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==10){
                                monthOfYear=4;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==11){
                                monthOfYear=5;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }else if(monthOfYear==12){
                                monthOfYear=6;
                                year++;
                                editFechaDes.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
                                bandera=4;
                            }
 */