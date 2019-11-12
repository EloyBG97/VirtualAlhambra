package com.virtualalhambra;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.LocationServices;

public class Poemas extends AppCompatActivity {

    private TextView titulo, poema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poema);

        //(int)Math.random()*10

        elegirPoema();
    }

    private void elegirPoema() {
        titulo = findViewById(R.id.textView7);
        poema = findViewById(R.id.textView13);

        switch (CajaFuerte.id_poema.get(0)) {

            case 1:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Poema en el pórtico norte del Generalife");

                    poema.setText("\"Taca en la puerta del salón más feliz \npara servir a Su Alteza en el mirador." +
                            "\n¡Por Dios, qué bella es alzada \na la diestra del rey incomparable!" +
                            "\n\nCuando en ella aparecen los vasos de agua,\nson como doncellas subidas a lo alto." +
                            "\nRegocíjate con Ismail, por quien \nDios te ha honrado y hecho feliz." +
                            "\n\n¡Subsista por él el Islam con fortaleza \ntan poderosa, que sea la defensa del trono!\"");

                } else {

                    titulo.setText("Poema de la puerta de Comares");

                    poema.setText("\"Soy corona en la frente de mi puerta:\n" +
                            "envidia al Occidente en mí el Oriente.\n" +
                            "Al-Gani billah* mándame que aprisa\n" +
                            "paso dé a la victoria apenas llame.\n" +
                            "Siempre estoy esperando ver el rostro\n" +
                            "del rey, alba que muestra el horizonte.\n" +
                            "¡A sus obras Dios haga tan hermosas\n" +
                            "como son su temple y su figura\"");

                }

                break;

            case 2:
                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Poema en el pórtico norte del Generalife");

                    poema.setText("\"Taca en la puerta del salón más feliz \npara servir a Su Alteza en el mirador." +
                            "\n¡Por Dios, qué bella es alzada \na la diestra del rey incomparable!" +
                            "\n\nCuando en ella aparecen los vasos de agua,\nson como doncellas subidas a lo alto." +
                            "\nRegocíjate con Ismail, por quien \nDios te ha honrado y hecho feliz." +
                            "\n\n¡Subsista por él el Islam con fortaleza \ntan poderosa, que sea la defensa del trono!\"");

                } else {

                    titulo.setText("Poema de la puerta de Comares");

                    poema.setText("\"Soy corona en la frente de mi puerta:\n" +
                            "envidia al Occidente en mí el Oriente.\n" +
                            "Al-Gani billah* mándame que aprisa\n" +
                            "paso dé a la victoria apenas llame.\n" +
                            "Siempre estoy esperando ver el rostro\n" +
                            "del rey, alba que muestra el horizonte.\n" +
                            "¡A sus obras Dios haga tan hermosas\n" +
                            "como son su temple y su figura\"");

                }

                break;

            case 3:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Poema en el pórtico norte del Generalife");

                    poema.setText("\"Taca en la puerta del salón más feliz \npara servir a Su Alteza en el mirador." +
                            "\n¡Por Dios, qué bella es alzada \na la diestra del rey incomparable!" +
                            "\n\nCuando en ella aparecen los vasos de agua,\nson como doncellas subidas a lo alto." +
                            "\nRegocíjate con Ismail, por quien \nDios te ha honrado y hecho feliz." +
                            "\n\n¡Subsista por él el Islam con fortaleza \ntan poderosa, que sea la defensa del trono!\"");

                } else {

                    titulo.setText("Poema de la puerta de Comares");

                    poema.setText("\"Soy corona en la frente de mi puerta:\n" +
                            "envidia al Occidente en mí el Oriente.\n" +
                            "Al-Gani billah* mándame que aprisa\n" +
                            "paso dé a la victoria apenas llame.\n" +
                            "Siempre estoy esperando ver el rostro\n" +
                            "del rey, alba que muestra el horizonte.\n" +
                            "¡A sus obras Dios haga tan hermosas\n" +
                            "como son su temple y su figura\"");

                }

                break;

            case 4:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Poema en el pórtico norte del Generalife");

                    poema.setText("\"Taca en la puerta del salón más feliz \npara servir a Su Alteza en el mirador." +
                            "\n¡Por Dios, qué bella es alzada \na la diestra del rey incomparable!" +
                            "\n\nCuando en ella aparecen los vasos de agua,\nson como doncellas subidas a lo alto." +
                            "\nRegocíjate con Ismail, por quien \nDios te ha honrado y hecho feliz." +
                            "\n\n¡Subsista por él el Islam con fortaleza \ntan poderosa, que sea la defensa del trono!\"");

                } else {

                    titulo.setText("Poema de la puerta de Comares");

                    poema.setText("\"Soy corona en la frente de mi puerta:\n" +
                            "envidia al Occidente en mí el Oriente.\n" +
                            "Al-Gani billah* mándame que aprisa\n" +
                            "paso dé a la victoria apenas llame.\n" +
                            "Siempre estoy esperando ver el rostro\n" +
                            "del rey, alba que muestra el horizonte.\n" +
                            "¡A sus obras Dios haga tan hermosas\n" +
                            "como son su temple y su figura\"");

                }

                break;

            default:

                titulo.setText("Entra en el default");

                poema.setText("JUAJAJAJAJ");


                break;

        }
    }
}
