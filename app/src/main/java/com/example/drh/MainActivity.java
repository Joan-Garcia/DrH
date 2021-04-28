package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (ImageView) findViewById(R.id.iV);
        b = (ImageView) findViewById(R.id.iV2);
        cambiarImagen();
    }

    public void cambiarImagen() {
        Random rgenerador = new Random();
        Integer[] imagenesID =
                {R.drawable.caballo, R.drawable.husky, R.drawable.husky2, R.drawable.nunny, R.drawable.turtle};
        int resource = imagenesID[rgenerador.nextInt(imagenesID.length)];
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        b.setVisibility(View.INVISIBLE);
        a.setImageResource(resource);
        a.setAnimation(animacion1);
        fin();
    }

    public void fin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation a2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.desplazamiento_abajo);
                b.setVisibility(View.VISIBLE);
                b.setAnimation(a2);
                vamonos();

            }

        }, 2000);
    }

    public void vamonos() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ab = new Intent(MainActivity.this, Login.class);
                startActivity(ab);
                finish();
            }
        }, 1500);

    }
}