package com.example.drh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin2 extends AppCompatActivity {

    primerFragmento pF = new primerFragmento();
    segundoFragmento sF = new segundoFragmento();
    tercerFragmento tF = new tercerFragmento();
    String email, name;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);
        email = getIntent().getStringExtra("correo");
        name = getIntent().getStringExtra("nombre");
        BottomNavigationView navigation = findViewById(R.id.fondoBoton);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.segundoFragmento);
        cargarFragmento(sF);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.primerFragmento:

                    cargarFragmento(pF);
                    return true;
                case R.id.segundoFragmento:

                    cargarFragmento(sF);
                    return true;
                case R.id.tercerFragmento:

                    cargarFragmento(tF);
                    return true;
            }
            return false;
        }

    };

    public void cargarFragmento(Fragment a) {
        bundle.putString("correo", email);
        bundle.putString("nombre", name);
        a.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//.detach(a);
        transaction.replace(R.id.contenedor, a);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}