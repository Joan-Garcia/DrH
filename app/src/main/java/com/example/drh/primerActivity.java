package com.example.drh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.drh.commands.VerifyDatabaseConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class primerActivity extends AppCompatActivity {
    VerifyDatabaseConnection dbc;
    String email, name;
    int id;
    Bundle bundle = new Bundle();
    primerFragmentUsuario pFU = new primerFragmentUsuario();
    segundoFragmentUsuario sFU= new segundoFragmentUsuario();
    tercerFragmentUsuario tFU = new tercerFragmentUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        dbc = new VerifyDatabaseConnection(this);
        email = getIntent().getStringExtra("correo");
        name = getIntent().getStringExtra("nombre");
        id= getIntent().getIntExtra("id",0);
        BottomNavigationView navigation = findViewById(R.id.fondoUsuario);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.segundoFragmentousuario);
        cargarFragmento(sFU);

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.primerFragmentoUsuario:
                    cargarFragmento(pFU);
                    return true;
                case R.id.segundoFragmentousuario:
                    cargarFragmento(sFU);
                    return true;
                case R.id.tercerFragmentoUsuario:
                    cargarFragmento(tFU);
                    return true;
            }
            return false;
        }

    };

    public void cargarFragmento(Fragment a) {
        bundle.putString("correo", email);
        bundle.putString("nombre", name);
        bundle.putInt("id",id);
        a.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//.detach(a);
        transaction.replace(R.id.contenedorUsuario, a);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}


