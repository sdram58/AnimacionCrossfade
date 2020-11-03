package com.catata.animacioncrossfade;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tvContenido;
    int duracionAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.pbIntro);
        tvContenido = (TextView) findViewById(R.id.tvContenido);

        duracionAnimacion = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        //Inicialmente ocultamos el texto.
        tvContenido.setVisibility(View.GONE);

        //Cuando hacemos click sobre el progressbar
        cargarDatos();


    }

    private void cargarDatos() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crossFadeAnimation();
            }
        },1500);
    }

    private void crossFadeAnimation() {

        //Hacemos el texto transparente
        tvContenido.setAlpha(0f);

        //Lo mostramos, no se verá porque es transparente

        tvContenido.setVisibility(View.VISIBLE);

        //Mostramos progresivamente
        tvContenido.animate()
                .alpha(1f)
                .setDuration(duracionAnimacion);

        //Ocultamos progresivamente el progressBar
        progressBar.animate()
                .alpha(0f)
                .setDuration(duracionAnimacion)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

}