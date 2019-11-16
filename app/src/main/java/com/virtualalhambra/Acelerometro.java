package com.virtualalhambra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    private static final long ROTATION_WAIT_TIME_MS = 50;

    private int xobjetivo = 40;    // [-60,60]
    private int yobjetivo = 15;    // [-50,50]
    private int zobjetivo = -32;   // [-90,90]

    private SensorManager sensor_manager;
    private Sensor giroscopo;
    private Sensor orientacion;

    private TextView x_fin, y_fin, z_fin, x, y, z;

    long mRotationTime;

    private int fase;

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_fuerte);

        Random random = new Random();

        xobjetivo = random.nextInt(80 + 80) - 80;
        yobjetivo = random.nextInt(80 + 80) - 80;
        zobjetivo = random.nextInt(80 + 80) - 80;

        x_fin = findViewById(R.id.xfija);
        y_fin = findViewById(R.id.yfija);
        z_fin = findViewById(R.id.zfija);

        x_fin.setText(Integer.toString(xobjetivo));
        y_fin.setText(Integer.toString(yobjetivo));
        z_fin.setText(Integer.toString(zobjetivo));

        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        giroscopo = sensor_manager.getDefaultSensor(Sensor./*TYPE_GYROSCOPE*/TYPE_GAME_ROTATION_VECTOR);
        orientacion = sensor_manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        fase = 0;

        boton = findViewById(R.id.abrecaja);
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

            x = findViewById(R.id.xvariable);
            y = findViewById(R.id.yvariable);
            z = findViewById(R.id.zvariable);

            switch (fase) {

                case 0:
                    x.setText(Integer.toString((int)(event.values[0]*100)));
                    y.setText("0");
                    z.setText("0");

                    break;

                case 1:
                    x.setText(Integer.toString(xobjetivo));
                    y.setText(Integer.toString((int)(event.values[1]*100)));
                    z.setText("0");

                    break;

                case 2:
                    y.setText(Integer.toString(yobjetivo));
                    z.setText(Integer.toString((int)(event.values[2]*100)));

                    break;

                case 3:
                    z.setText(Integer.toString(zobjetivo));

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

                    if ((int)(event.values[0]*100) == xobjetivo)
                        fase = 1;

                    break;

                case 1:

                    if ((int)(event.values[1]*100) == yobjetivo)
                        fase = 2;

                    break;

                case 2:

                    if ((int)(event.values[2]*100) == zobjetivo)
                        fase = 3;
                    break;

                case 3:

                    abrirCajaFuerte();
                    break;
            }
        }
    }

    private void abrirCajaFuerte(){

        boton.setVisibility(View.VISIBLE);
    }

    public void mostrarPoema(View view) {

        Intent intent = new Intent(this, Poemas.class);
        startActivity(intent);
    }
}
