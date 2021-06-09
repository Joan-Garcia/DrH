package com.example.drh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.drh.commands.SelectAllVacuna;

import java.util.ArrayList;

public class datosContacto extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<contacto> listaV;
    contacto con;
    adapContaco aC;
    Integer []imagenesContaco= new Integer[]{R.drawable.contaco, R.drawable.bd};
    Integer []imagenesRedS= new Integer[]{R.drawable.insta, R.drawable.feis};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);

        vamos();
    }
    public void vamos(){
        rv=(RecyclerView)findViewById(R.id.idRecV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listaV= new ArrayList<>();
        aC = new adapContaco(listaV, this);
        con= new contacto("PROGRAMADOR","JORGE CARREÓN",
                          "jace62@hotmail.es","4773767637",
                "/carreonchis_", imagenesContaco[0],imagenesRedS[0]);
        listaV.add(con);
        con= new contacto("ADMINISTRADOR BD", "JOAN GARCÍA",
                "joan.garcia@gmail.com","4771234567","/joangarcia2015",
                imagenesContaco[1],imagenesRedS[1]);

        listaV.add(con);
        aC= new adapContaco(listaV,this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
rv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "PRUEBA", Toast.LENGTH_SHORT).show();
    }
});
        /*
        aC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listaV.get(rv.getChildAdapterPosition());

                if(listaV.get(rv.getChildAdapterPosition(view))){
                    Uri uri= Uri.parse("https://www.instagram.com/carreonchis_/?hl=es-la");
                    Intent ab = new Intent(Intent.ACTION_VIEW,uri);
                    try {
                        startActivity(ab);
                    }catch(ActivityNotFoundException a){
                        startActivity(new Intent
                                (Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/carreonchis_/?hl=es-la")));
                    }
                }else{
                    Uri uri= Uri.parse("https://www.facebook.com/joangarcia2015");
                    Intent ab = new Intent(Intent.ACTION_VIEW,uri);
                    try {
                        startActivity(ab);
                    }catch(ActivityNotFoundException a){
                        startActivity(new Intent
                                (Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/carreonchis_/?hl=es-la")));
                    }
                }
            }
        });*/

        rv.setAdapter(aC);
    }
}