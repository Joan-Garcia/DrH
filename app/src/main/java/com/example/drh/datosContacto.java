package com.example.drh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class datosContacto extends AppCompatActivity {
ImageView insta, feis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);
        insta=(ImageView)findViewById(R.id.instagrains);
        feis=(ImageView)findViewById(R.id.feisbus);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.instagram.com/carreonchis_/?hl=es-la");
                Intent ab = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(ab);
                }catch(ActivityNotFoundException a){
                    startActivity(new Intent
                            (Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/carreonchis_/?hl=es-la")));
                }
            }
        });
        feis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://www.facebook.com/joangarcia2015");
                Intent ab = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(ab);
                }catch(ActivityNotFoundException a){
                    startActivity(new Intent
                            (Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/joangarcia2015")));
                }
            }
        });
    }
}