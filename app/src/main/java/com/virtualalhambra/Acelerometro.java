package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    private static final long ROTATION_WAIT_TIME_MS = 50;
    private static final int XOBJETIVO = 40;    // [-60,60]
    private static final int YOBJETIVO = 15;    // [-50,50]
    private static final int ZOBJETIVO = -32;   // [-90,90]

    private SensorManager sensor_manager;
    private Sensor giroscopo;
    private Sensor orientacion;

    private TextView x_fin, y_fin, z_fin, x, y, z;

    long mRotationTime;

    private int fase;

    ImageView flecha;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_fuerte);

        x_fin = findViewById(R.id.textView6);
        y_fin = findViewById(R.id.textView8);
        z_fin = findViewById(R.id.textView9);

        x_fin.setText(Integer.toString(XOBJETIVO));
        y_fin.setText(Integer.toString(YOBJETIVO));
        z_fin.setText(Integer.toString(ZOBJETIVO));

        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        giroscopo = sensor_manager.getDefaultSensor(Sensor./*TYPE_GYROSCOPE*/TYPE_GAME_ROTATION_VECTOR);
        orientacion = sensor_manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        fase = 0;

        flecha = findViewById(R.id.flecha);
        flecha.setVisibility(View.INVISIBLE);

        boton = findViewById(R.id.button3);
        boton.setVisibility(View.INVISIBLE);


}

    // Se actualiza el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR/*TYPE_GYROSCOPETYPE_MAGNETIC_FIELD*/) {

            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

                x.setText(R.string.act_main_no_acuracy);
                y.setText(R.string.act_main_no_acuracy);
                z.setText(R.string.act_main_no_acuracy);

                return;
            }

            x = findViewById(R.id.textView10);
            y = findViewById(R.id.textView11);
            z = findViewById(R.id.textView12);

            switch (fase) {

                case 0:
                    x.setText(Integer.toString((int)(event.values[0]*100)));
                    y.setText("0");
                    z.setText("0");

                    break;

                case 1:
                    x.setText(Integer.toString(XOBJETIVO));
                    y.setText(Integer.toString((int)(event.values[1]*100)));
                    z.setText("0");

                    break;

                case 2:
                    y.setText(Integer.toString(YOBJETIVO));
                    z.setText(Integer.toString((int)(event.values[2]*100)));

                    break;

                case 3:
                    z.setText(Integer.toString(ZOBJETIVO));

                    break;
            }

            detectRotation(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor_manager.registerListener(this, giroscopo, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor_manager.unregisterListener(this);
    }

    private void detectRotation(SensorEvent event) {
        long now = System.currentTimeMillis();


        if ((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
            mRotationTime = now;

            switch (fase) {
                case 0:

                    if ((int)(event.values[0]*100) == XOBJETIVO)
                        fase = 1;

                    break;

                case 1:

                    if ((int)(event.values[1]*100) == YOBJETIVO)
                        fase = 2;

                    break;

                case 2:

                    if ((int)(event.values[2]*100) == ZOBJETIVO)
                        fase = 3;
                    break;

                case 3:

                    abrirCajaFuerte();
                    break;
            }
        }
    }

    private void abrirCajaFuerte(){

        flecha.setVisibility(View.VISIBLE);
        boton.setVisibility(View.VISIBLE);
    }

    public void mostrarPoema(View view) {

        Intent intent = new Intent(this, Poemas.class);
        startActivity(intent);
    }
}
