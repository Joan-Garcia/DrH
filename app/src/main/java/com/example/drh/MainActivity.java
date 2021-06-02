package com.example.drh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView  b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (ImageView) findViewById(R.id.iV2);
        b.setVisibility(View.VISIBLE);
        Animation a2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.desplazamiento_abajo);
        b.setAnimation(a2);
        vamonos();
    }



    public void vamonos() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ab = new Intent(MainActivity.this, Login.class);
                Pair[] pairs = new Pair[1];
                pairs[0]= new Pair<View, String>(b,"logoImageTrans");
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions aO = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(ab,aO.toBundle());
                    finish();
                }else{
                    startActivity(ab);
                    finish();
                }

            }
        }, 1700);

    }
}