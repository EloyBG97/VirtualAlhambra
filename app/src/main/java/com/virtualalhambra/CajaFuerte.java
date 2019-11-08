package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class CajaFuerte extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private ArrayList<Double> latitud = new ArrayList<Double>();
    private ArrayList<Double> longitud = new ArrayList<Double>();

    private int cuadrante;

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
                        A   B   C
                          D   E
                            F
         */
        latitud.add(37.197695);    // A
        latitud.add(37.197620);    // B
        latitud.add(37.197545);    // C
        latitud.add(37.196850);    // D
        latitud.add(37.196775);    // E
        latitud.add(37.196004);    // F

        longitud.add(-3.625459);    // A
        longitud.add(-3.624170);    // B
        longitud.add(-3.622880);    // C
        longitud.add(-3.624924);    // D
        longitud.add(-3.623634);    // E
        longitud.add(-3.624388);    // F
    }

    private void coordenadasUsuario() {

        int cuadrante;

        // Se comprueba que tenemos permiso de acceso a la ubicación. Si no lo tenemos, lo sulicitamos.
        // Si después de solicitarlo siguen sin consederse, salta una pantalla "de error"
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
              != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
              != PackageManager.PERMISSION_GRANTED) {

            setContentView(R.layout.activity_error_permisos_ubicacion);

            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        int cuadrante = -1;

                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            setContentView(R.layout.activity_error_acceso);
                            return;
                        }

                        // CAMBIAR
                        for (int i = 0; i < 3; ++i){

                                // Si se encuentra el cuadrante al que pertenece, se devuelve el identidicador
                                if (location.getLatitude() >= latitud.get(i) &&
                                    location.getLatitude() < latitud.get(i+1) &&
                                    location.getLongitude() >= longitud.get(j) &&
                                    location.getLongitude() < longitud.get(j+1)) {

                                    cuadrante = i*3 + j;
                                    break;
                                }
                            }
                        }

                        switch (cuadrante) {
                            case -1:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 0:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 1:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 2:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 3:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 4:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 5:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 6:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 7:
                                setContentView(R.layout.activity_error_acceso);
                                break;

                            case 8:
                                setContentView(R.layout.activity_error_acceso);
                                break;
                        }

                    }
                });




        setContentView(R.layout.activity_caja_fuerte);
        return 0;

    }
}
