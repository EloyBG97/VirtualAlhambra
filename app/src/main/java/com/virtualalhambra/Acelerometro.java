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

    // Cada cuantos milisegundo se comprueba el sensor
    private static final long ROTATION_WAIT_TIME_MS = 50;

    // Valores de coordenadas objetivos
    private int xobjetivo;    // [-80,80]
    private int yobjetivo;    // [-80,80]
    private int zobjetivo;    // [-80,80]

    private SensorManager sensor_manager;           // Controlador del sensor
    private Sensor giroscopo;                       // Sensor

    private TextView x_fin, y_fin, z_fin, x, y, z;  // Visualizadores de texto

    long mRotationTime;

    private int fase;           // Número de coordenadas conseguidas

    Button boton;               // Controlador del botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Se activa el layout correspondiente
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja_fuerte);

        // Generador de números aleatorios
        Random random = new Random();

        // Se generan núemros aleatorios para fijar los obbjetivos
        xobjetivo = random.nextInt(80 + 80) - 80;
        yobjetivo = random.nextInt(80 + 80) - 80;
        zobjetivo = random.nextInt(80 + 80) - 80;

        // se pintan en pantalla los valores conseguidos
        x_fin = findViewById(R.id.xfija);
        y_fin = findViewById(R.id.yfija);
        z_fin = findViewById(R.id.zfija);

        x_fin.setText(Integer.toString(xobjetivo));
        y_fin.setText(Integer.toString(yobjetivo));
        z_fin.setText(Integer.toString(zobjetivo));

        // Se activa el sensor qeu se va a utilizar
        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        giroscopo = sensor_manager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);

        // Se pone a 0 el núemro de coordenadas fijadas
        fase = 0;

        // Se oculta el botón que lleva a la siguiente pantalla
        boton = findViewById(R.id.abrecaja);
        boton.setVisibility(View.INVISIBLE);


}

    // Se actualiza el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        // Se estudia el sensor qeu interesa
        if (event.sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {

            // Si no está disponible, mensaje de error
            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

                x.setText(R.string.act_main_no_acuracy);
                y.setText(R.string.act_main_no_acuracy);
                z.setText(R.string.act_main_no_acuracy);

                return;
            }

            // Se localizan donde se va a escribir los valores
            x = findViewById(R.id.xvariable);
            y = findViewById(R.id.yvariable);
            z = findViewById(R.id.zvariable);

            // En funión de la fase, se cambia el identificador de sensor correspondiente.
            // El restro estarán al valor objetivo o 0
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

            // Se comprueba el resultado conseguido en el sensor
            detectRotation(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {

        // Se consiguen los valores del sensor
        super.onResume();
        sensor_manager.registerListener(this, giroscopo, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {

        // Se suspende el sensor
        super.onPause();
        sensor_manager.unregisterListener(this);
    }

    private void detectRotation(SensorEvent event) {

        // Se guarda el tiempo actual
        long now = System.currentTimeMillis();

        // Si ha pasado suficiente tieempo, se comprueba si se ha llegado al valor deseado. Si se ha
        // llegado se cambia de fase
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

                    // En esta fase se llama al método abrir caja fuerte, porque se han conseguido
                    // fijar todos los valores
                    abrirCajaFuerte();
                    break;
            }
        }
    }

    private void abrirCajaFuerte(){

        // Se activa el botón
        boton.setVisibility(View.VISIBLE);
    }

    public void mostrarPoema(View view) {

        // si se pulsa el boton, se cambia de clase
        Intent intent = new Intent(this, Poemas.class);
        startActivity(intent);
    }
}
