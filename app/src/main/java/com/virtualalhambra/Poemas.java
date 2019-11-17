package com.virtualalhambra;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.LocationServices;

public class Poemas extends AppCompatActivity {

    private TextView titulo, poema;     // Visualizador de texto
    private View foto;                  // Visualizador de foto

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Se inicia el layout correspondiente
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poema);

        // Se elige el poema que se va a mostrar
        elegirPoema();
    }

    private void elegirPoema() {

        titulo = findViewById(R.id.textView7);
        poema = findViewById(R.id.textView13);

        foto = findViewById(R.id.layaoutpoema);

        // En función a la zona en la que se estaba y el botón que se seleccionó, se muestra un poema u otro.
        switch (CajaFuerte.id_poema.get(0)) {

            case 1:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Alhambra de Granada");

                    poema.setText("\nOh, flor de los mil colores,\nátomo de los mil amores,\n" +
                            "Oh, ruindad llena de gracia,\nalmena de la fragancia.\n\n" +
                            "Reina del poderío,\nfuente del amor mío,\npaloma sin palomar,\n" +
                            "Alhambra del bien amar.\n\nOh, reina más coronada,\n" +
                            "carne de tierra encarnada,\nen bosques oscuros encerrada,\n" +
                            "soltada al cielo, soltada.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_uno));

                } else {

                    titulo.setText("Sueños de la Alhambra");

                    poema.setText("\nEl hermoso zafiro de tus ojos,\nlas palmeras de tus torres y de tu piel,\n" +
                            "el aire que has llenado de fragancias,\nlas ruideras de las aguas a tus pies.\n\n" +
                            "Eres arroyo de sombras y de musgos,\neres el sueño enredado en el edén,\n" +
                            "eres la ciudad más hermosa de este mundo,\neres el barro perfumado del ayer.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_dos));

                }

                break;

            case 2:
                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("Subida a la Alhambra");

                    poema.setText("\nCómo llegar a ti, hermosa mía, tan alta,\ntan soberana de ti, de ti tan llena,\n" +
                            "como quedar en ti, tan perfumada,\ntan antigua y tan noble, tan llena de Granada.\n" +
                            "\n\nMe suenan a música los cantos de tus aves,\nel ruido de los vientos que no mueven tus árboles;\n" +
                            "en el frondoso bosque, el sol se multiplica,\ny yo me hago más grande, embobado en belleza.\n" +
                            "\nCómo llegar a ti, hermosa mía, ocultada,\ntan profunda en la altura, tan grande y arrobada,\n" +
                            "tan antigua y tan noble, tan llena de Granada.\n\nNunca estuve más cerca del cielo, enamorado,\n" +
                            "el tiempo entre tus torres apenas se detiene;\nla ciudad te acompaña, parada en el abismo,\n" +
                            "la palabra calla, tu belleza resplandece.\n");

                    foto.setBackground(getDrawable(R.drawable.alhambra_tres));

                } else {

                    titulo.setText("La ciudadela del monte");

                    poema.setText("\nAlhambra, vergel de sombras, en el agua encantada,\ncon los oscuros olores de la tierra mojada\n" +
                            "y los claros cristales de su alma intransitada,\nparaíso escondido, en muchos siglos olvidada,\n" +
                            "nacida desde lo mismo y que parece inventada,\ncada día es más nueva, cada día es encontrada,\n" +
                            "apostada frente al viento, defendida, amurallada,\ncon las grietas de los tiempos de la belleza parada,\n" +
                            "que habla de las victorias y que fue la derrotada,\nora estrella, ora surco, ora arquitectura hilada,\n" +
                            "fuente del espejo limpio, quieta luz, inmaculada,\nserrana que señorea sobre la vega azulada,\n" +
                            "con un alfanje invisible, en su mano levantada,\ndefensora de un tesoro vinculado a su Granada.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_cuatro));

                }

                break;

            case 3:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("El agua de la Alhambra");

                    poema.setText("\n¡Qué bien se oye y bien suena el agua de la Alhambra,\n" +
                            "que nace entre azucenas y canta pronto una zambra!\nCuando cae mira al cielo,\n" +
                            "cuando pasa, apenas pasa,\nbajada por los senderos,\nde barandillas y cauchiles,\n" +
                            "pasamanos de labranzas,\ncantos de su agua hermosa,\ntan perfumada y descalza,\n" +
                            "tan profunda y juguetona,\nolvidada y encontrada,\nal sol su rostro en las fuentes,\n" +
                            "en blancos aljibes ocultada,\nenmarañada en los bosques,\nserenada en las estancias,\n" +
                            "enfilada en los palacios,\npor los montes encabritada,\ncomo el sueño de sus ríos,\n" +
                            "que, olvidados de ser ríos,\nentre sus bosques quedaran.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_cinco));

                } else {

                    titulo.setText("Las voces de la Alhambra");

                    poema.setText("\nHe contado a todas, muchas, y entre ellas tú no estás,\n" +
                            "oh silencio de cristal, alas del viento, miradas del pleamar,\n" +
                            "la soledad se acompaña solo de la soledad,\n" +
                            "¿ quién cerrará sus ventanas? ¿ quién sus ojos cerrará?,\n" +
                            "entre los bosques, profunda, tu voz nunca sonará;\n" +
                            "suben sobre las cuestas, perfumadas de arrayán,\n" +
                            "los olvidos que se han muerto, heridos por el leviatán,\n" +
                            "con tarascadas de vientos y sombras de la vanidad;\n" +
                            "he contado a todas, muchas, y entre ellas tú no estás,\n" +
                            "la grandeza de la Alhambra es también tu soledad,\n" +
                            "oh silencio tan capital que llega desde la ciudad,\n" +
                            "los olvidos y los desprecios en tu alma de cristal,\n" +
                            "la soledad que es tan tuya, en tu Alhambra quedará,\n" +
                            "entre los bosques, profunda, tu voz nunca sonará,\n" +
                            "¿quién cerrará sus ventanas? ¿quién sus ojos cerrará?.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_seis));

                }

                break;

            case 4:

                if (CajaFuerte.id_poema.get(1) == 1) {
                    titulo.setText("El patio");

                    poema.setText("\nUna fuente me suena y es poesía,\nreguerillo de un amor que está sonando,\n" +
                            "chapoteo de palabras y de sueños\nenredados en las lunas del mosaico.\n" +
                            "\nCanta su agua ligera, entre los mirtos,\nentre las verdes sombras sube su canto,\n" +
                            "claridad del sol y de las aguas,\nchorrillos de un amor que está cantando.\n" +
                            "\nLe perfuman los cipreses y los arrayanes,\nle dan carne los barros de los patios,\n" +
                            "y los arcos prendidos de la arcilla\nle sostienen primorosos alabastros.\n" +
                            "\nPatio tan andaluz, cortado al cielo,\nsuspiro de un amor que han desterrado,\n" +
                            "por la amplia geometría de la belleza\nsu mirada de tristeza se ha quedado.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_siete));

                } else {

                    titulo.setText("Dale limosna, Granada");

                    poema.setText("\nUna canción regalada\nde una guitarra rasgada,\nel corredor de tu agua\n" +
                            "en arcos solemnizada,\nalgún limón\npara llorar al beberte\ny el frescor\n" +
                            "que de la Alhambra viene dominador,\nla luna también te pido\npara mis noches morenas,\n" +
                            "algo de la alquimia mágica\nque embadurna a las parejas,\ny el corazón\n" +
                            "de rubís de tu Granada.\nNo te pido nada más,\nque el mucho pedir ofende,\n" +
                            "algún perdido rincón\nen donde yo pueda verte.");

                    foto.setBackground(getDrawable(R.drawable.alhambra_ocho));

                }

                break;

            default:

                titulo.setText("No está en un área permitida");

                poema.setText("No está en un área permitida");


                break;

        }
    }
}
