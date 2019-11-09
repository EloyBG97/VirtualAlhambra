package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    // Sensor
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    // Sensibilidad del sensor
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_personajes);

        // Inicializar la instancia de SensorManager
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Acelerómetro
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Se guarda el sensor
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Anular los registros del sensor
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume () {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor mySensor = event.sensor;
        float x, y, z;

        // Variable para controlar cada cuanto tiempo se recogen los datos
        long curTime = System.currentTimeMillis();

        long diffTime =(curTime - lastUpdate);

        float speed;

        // Se verifica que es el sensor del acelerómetro
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            /*
               Eje x: movimiento horizontal (inclinar la pantalla para delante o detrás).
               Eje y: movimiento vertical (girar el móvil de manera vertical)
               Eje z: apaisar el móvil.
            */

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            if ((curTime - lastUpdate) > 100) {
                diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                // Se calcula la velocidad en la que se ha sacudido
                speed = Math.abs(x + y + z - last_x - last_y - last_z)/diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {

                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}