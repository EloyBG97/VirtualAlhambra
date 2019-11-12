package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class CajaFuerte extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private ArrayList<Double> latitud = new ArrayList<>();
    private ArrayList<Double> longitud = new ArrayList<>();

    static ArrayList<Integer> id_poema = new ArrayList<>();

    Button botonuno, botondos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pantalla de carga
        setTheme(R.style.AppTheme);

        inicializarCoordenadas();

        // Creación del LocationServices client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        coordenadasUsuario();

    }

    private void inicializarCoordenadas() {
        /*
                        A B' C' D' E' F' G'
                        B
                        C
                        D
                        E
         *//*
        latitud.add(37.197516);    // A
        latitud.add(37.197245);    // B
        latitud.add(37.196974);    // C
        latitud.add(37.196703);    // D
        latitud.add(37.196432);    // E

        longitud.add(-3.624990);    // A
        longitud.add(-3.624709);    // B'
        longitud.add(-3.624428);    // C'
        longitud.add(-3.624147);    // D'
        longitud.add(-3.623866);    // E'
        longitud.add(-3.623585);    // F'
        longitud.add(-3.623304);    // G'
*/

        latitud.add(37.190320);    // A
        latitud.add(37.190160);    // B
        latitud.add(37.189999);    // C
        latitud.add(37.189839);    // D
        latitud.add(37.189678);    // E

        longitud.add(-3.608127);    // A
        longitud.add(-3.607966);    // B'
        longitud.add(-3.607804);    // C'
        longitud.add(-3.607643);    // D'
        longitud.add(-3.607482);    // E'
        longitud.add(-3.607320);    // F'
        longitud.add(-3.607159);    // G'

    }

    private void coordenadasUsuario() {

        // Se comprueba que tenemos permiso de acceso a la ubicación. Si no lo tenemos, lo sulicitamos.
        // Si después de solicitarlo siguen sin consederse, salta una pantalla "de error"
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
              != PackageManager.PERMISSION_GRANTED) {

            setContentView(R.layout.activity_error_permisos_ubicacion);

            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        // Got last known location. In some rare situations this can be null.
                        if (location == null) {
                            setContentView(R.layout.activity_error_acceso);
                            return;
                        }

                        id_poema.clear();

                        double lat = location.getLatitude();
                        double log = location.getLongitude();


                        /* 1.- Se comprueban las zonas no válidas de la matriz (x)

                                o o o o o o
                                o o o o o x
                                o o o o o x
                                x x o o x x
                         */
                        if (lat > latitud.get(0) || lat < latitud.get(4) ||
                                log > longitud.get(6) || log < longitud.get(0) ||
                                (lat < latitud.get(3) && (log < longitud.get(2) || log > longitud.get(4))) ||
                                (lat < latitud.get(1) && log > longitud.get(5))) {

                            setContentView(R.layout.activity_fuera_area);
                            return;
                        }

                        setContentView(R.layout.activity_area);

                        /* 2.- Área 1

                                x x x x x x
                                o o o o o o
                                o o o o o o
                                o o o o o o
                        */
                        if ((lat <= latitud.get(0) && lat > latitud.get(1)) &&
                                (log >= longitud.get(0)) && log <= longitud.get(6)) {

                            id_poema.add(1);

                            botonuno = findViewById(R.id.botonuno);
                            botondos = findViewById(R.id.botondos);

                            botonuno.setBackground(getDrawable(R.drawable.palacio_uno));
                            botondos.setBackground(getDrawable(R.drawable.palacio_dos));

                            return;
                        }

                        /* 3.- Área 2

                                o o o o o o
                                o o o o o o
                                x x x o o o
                                o o x o o o
                        */
                        if ((lat <= latitud.get(2) && lat >= latitud.get(4)) &&
                                (log >= longitud.get(0) && log < longitud.get(3))) {

                            id_poema.add(2);

                            botonuno = findViewById(R.id.botonuno);
                            botondos = findViewById(R.id.botondos);

                            botonuno.setBackground(getDrawable(R.drawable.alcazaba_uno));
                            botondos.setBackground(getDrawable(R.drawable.alcazaba_dos));


                            return;

                        }

                        /* 4.- Área 3

                                o o o o o o
                                o o o o x o
                                o o o x x o
                                o o o x o o
                        */
                        if (((lat <= latitud.get(1) && lat >= latitud.get(3)) &&
                                (log >= longitud.get(4) && log <= longitud.get(5))) ||
                                ((lat <= latitud.get(2) && lat >= latitud.get(4)) &&
                                        (log >= longitud.get(3) && log <= longitud.get(4)))) {

                            id_poema.add(3);

                            botonuno = findViewById(R.id.botonuno);
                            botondos = findViewById(R.id.botondos);

                            botonuno.setBackground(getDrawable(R.drawable.generalife_uno));
                            botondos.setBackground(getDrawable(R.drawable.generalife_dos));


                            return;

                        }

                        /* 5.- Área 4

                                o o o o o o
                                x x x x o o
                                o o o o o o
                                o o o o o o
                        */

                        if ((lat <= latitud.get(1) && lat > latitud.get(2)) &&
                                (log >= longitud.get(0) && log < longitud.get(4))) {

                            id_poema.add(4);

                            botonuno = findViewById(R.id.botonuno);
                            botondos = findViewById(R.id.botondos);

                            botonuno.setBackground(getDrawable(R.drawable.otra_dos));
                            botondos.setBackground(getDrawable(R.drawable.otra_uno));


                            return;

                        }
                    }
                });
    }

    public void sigueInvestigando(View view) {
        setContentView(R.layout.activity_seguir_investigando);
    }

    public void activarCajaFuertePrimera(View view) {

        id_poema.add(1);
        Intent intent = new Intent(this, Acelerometro.class);
        startActivity(intent);
    }

    public void activarCajaFuerteSegunda(View view) {

        id_poema.add(2);
        Intent intent = new Intent(this, Acelerometro.class);
        startActivity(intent);
    }
}
