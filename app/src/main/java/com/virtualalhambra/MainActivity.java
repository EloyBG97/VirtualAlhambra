package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    static Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Pantalla de carga
        setTheme(R.style.AppTheme);

        // Se le solicitan al usuario todos los permisos necesarios
        permisosUsuario();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se oculta el botón
        boton = findViewById(R.id.unido);
        boton.setVisibility(View.INVISIBLE);
    }

    public void permisosUsuario() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},5);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},3);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},4);
        }

    }

    /** Called when the user taps the Send button */
    public void selectPersonaje(View view) {
        Intent intent = new Intent(this, SelectPersonajes.class);
        startActivity(intent);
    }


    /** Called when the user taps the Send button */
    public void tocaCajaFuerte (View view) {

        // Se activa al pulsar el botón de la izquierda. Activa la clase CajaFuerte
        Intent intent = new Intent(this, CajaFuerte.class);
        startActivity(intent);
    }

    public void leyenda (View view){
     //   if(MultiTouchView.getAtributo()){
            Intent intent = new Intent(this, Leyendas.class);
            startActivity(intent);
            MultiTouchView.setAtributo(false);
            this.setBoton(false);
     //   }

        MultiTouchView v = findViewById(R.id.touchView);
        v.reiniciar();
    }

    public static void setBoton(boolean b) {

        if (b)
            boton.setVisibility(View.VISIBLE);

        else

            boton.setVisibility(View.INVISIBLE);

    }
}
