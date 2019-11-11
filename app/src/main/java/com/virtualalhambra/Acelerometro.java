package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    private static final long ROTATION_WAIT_TIME_MS = 100;
    private static final int XOBJETIVO = 5;
    private static final int YOBJETIVO = 5;
    private static final int ZOBJETIVO = 5;

    private SensorManager sensor_manager;
    private Sensor giroscopo;
    private Sensor orientacion;

    private TextView x, y, z;

    long mRotationTime;

    private boolean abierto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_fuerte);

        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        giroscopo = sensor_manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        orientacion = sensor_manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        abierto = false;

    }

    // Se actualiza el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE/*TYPE_MAGNETIC_FIELD*/&& !abierto) {

            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

                x.setText(R.string.act_main_no_acuracy);
                y.setText(R.string.act_main_no_acuracy);
                z.setText(R.string.act_main_no_acuracy);

                return;
            }

            x = findViewById(R.id.textView10);
            y = findViewById(R.id.textView11);
            z = findViewById(R.id.textView12);


            x.setText("x = " + Integer.toString((int)event.values[0]*100));
            y.setText("y = " + Integer.toString((int)event.values[1]*100));
            z.setText("z = " + Integer.toString((int)event.values[2]*100));


            detectRotation(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor_manager.registerListener(this, orientacion, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor_manager  .unregisterListener(this);
    }

    private void detectRotation(SensorEvent event) {
        long now = System.currentTimeMillis();


        if ((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
            mRotationTime = now;

            /*if (Math.abs(event.values[0]) > ROTATION_THRESHOLD ||
                Math.abs(event.values[1]) > ROTATION_THRESHOLD ||
                Math.abs(event.values[2]) > ROTATION_THRESHOLD) {

                soundGyro.start();
            }
            */

            if ((int)event.values[0] == XOBJETIVO &&
                (int)event.values[1] == YOBJETIVO &&
                (int)event.values[2] == ZOBJETIVO) {

                abierto = true;
                abrirCajaFuerte();
            }
        }
    }

    private void abrirCajaFuerte(){

        abierto = false;

        setContentView(R.layout.activity_poema);


    }
}
