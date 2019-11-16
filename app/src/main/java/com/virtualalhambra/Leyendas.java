package com.virtualalhambra;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Leyendas extends AppCompatActivity {

    private TextView titulo,leyenda;

    int random =  (int) (Math.random() * 7) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leyenda);

        titulo = findViewById(R.id.textView7);
        leyenda = findViewById(R.id.textView13);

        switch (random){
            case 1:
                titulo.setText("El Suspiro del Moro");
                leyenda.setText("En el año de 1492 los Reyes Católicos" + "\n" +
                                "conquistaron el reino de Granada."  + "\n" +
                                "Cuenta la leyenda que, tras entregar el" + "\n" +
                                "rey Boabdil las llaves de la ciudad a" + "\n" +
                                "los reyes de Castilla y Aragón, cuando" + "\n" +
                                "alcanzaba la colina así conocida, se" + "\n" +
                                "volvió por fin y, suspirando, rompió" + "\n" +
                                "a llorar, momento en que su madre" + "\n" +
                                "le dijo: 'Llora como mujer lo que no" + "\n" +
                                "has sabido defender como hombre'." + "\n");
                break;

            case 2:
                titulo.setText("La Silla del Moro");
                leyenda.setText("La vida supuestamente disoluta que" + "\n" +
                                "llevaba el rey Boabdil, al parecer" + "\n" +
                                "dio lugar a un amotinamiento en la" + "\n" +
                                "ciudad de Alhambra. Por este " + "\n" +
                                "motivo, el rey tuvo que refugiarse" + "\n" +
                                "en una apartada colina desde la" + "\n" +
                                "que, sentado en su cima, divisaba" + "\n" +
                                "su ciudad sublevada, que por tal"  + "\n" +
                                "razón aún hoy es así conocida." + "\n");
                break;

            case 3:
                titulo.setText("El Reloj de Sol");
                leyenda.setText("Según cuentan, la Alhambra en su" + "\n" +
                                "conjunto puede ser considerada un" + "\n" +
                                "enorme reloj de sol. Esto es así" + "\n" +
                                "ya que, al igual que con un reloj" + "\n" +
                                "de sol podríamos seguir el " + "\n" +
                                "transcurrir de todas las horas" +  "\n" +
                                "del día, en la ciudad granadina" +  "\n" +
                                "podemos hacerlo a través de sus" + "\n" +
                                "estancias. Este fenómeno es" + "\n" +
                                "especialmente evidente al mediodía, " + "\n" +
                                "momento en que las dependencias" + "\n" +
                                "quedan divididas por su mitad como" + "\n" +
                                "efecto de la sombra." + "\n");
                break;

            case 4:
                titulo.setText("La Puerta de la Justicia");
                leyenda.setText("Existen dos leyendas particularmente" + "\n" +
                                "bellas en torno a esta puerta. Tan" + "\n" +
                                "grande era su magnificencia y tan" + "\n" +
                                "ufanos se sentían de ella, que" + "\n" +
                                "aseguraban que si existía un" + "\n" +
                                "caballero en la Tierra que fuese" + "\n" +
                                "capaz, estando montado en su" + "\n" +
                                "caballo, de tocar con la punta de" + "\n" +
                                "su lanza la mano esculpida en lo" + "\n" +
                                "más alto de su arco exterior," + "\n" +
                                "conquistaría el trono de la ciudad.\n" +
                                "\n" +
                                "Considerada por el mundo conocido" + "\n" +
                                "como una fortaleza inexpugnable," + "\n" +
                                "sumamente recia en su construcción," + "\n" +
                                "aseveraban que no caería bajo el" + "\n" +
                                "ataque de mil ejércitos. De este" + "\n" +
                                "modo, el día que la mano de su arco" + "\n" +
                                "exterior llegara a tocar la llave" + "\n" +
                                "del arco interior de esta puerta," + "\n" +
                                "sería porque había llegado el fin" + "\n" +
                                "del mundo, pues la Alhambra estaría" + "\n" +
                                "en ruinas." + "\n" );
                break;

            case 5:
                titulo.setText("El soldado Encantado");
                leyenda.setText("Cuentan de un estudiante salmantino" + "\n" +
                                "que, llegado a Granada con el fin" + "\n" +
                                "de recavar fondos para sufragar sus" + "\n" +
                                "estudios, reparó en un anacrónico" + "\n" +
                                "soldado, vestido con armadura y" + "\n" +
                                "portando una lanza. Acercóse a" + "\n" +
                                "preguntarle y éste le respondió" + "\n" +
                                "que penaba desde 300 años ya por" + "\n" +
                                "una maldición, lanzada por un" + "\n" +
                                "alfaquí musulmán, que le conjuró" + "\n" +
                                "a custodiar por toda la eternidad" + "\n" +
                                "el tesoro de Boabdil, otorgándole" + "\n" +
                                "licencia para salir de la estancia" + "\n" +
                                "del botín sólo una vez cada cien" + "\n" +
                                "años. El estudiante, interesado en" + "\n" +
                                "su problema, y avisado de las" + "\n" +
                                "riquezas que podría hallar en el" + "\n" +
                                "escondrijo, le ofreció su ayuda." + "\n" +
                                "Debía buscar una joven cristiana y" + "\n" +
                                "un sacerdote en ayuno para " + "\n" +
                                "deshacer el hechizo. El joven" + "\n" +
                                "consiguió a la primera sin " + "\n" +
                                "esfuerzo, pero el único cura que" + "\n" +
                                "estuvo dispuesto a acompañarle" + "\n" +
                                "estaba aquejado de gula " + "\n" +
                                "impenitente. A mitad de conjuro," + "\n" +
                                "el clérigo se abalanzó sobre los" + "\n" +
                                "manjares que estaban preparados" + "\n" +
                                "para el final del sortilegio," + "\n" +
                                "rompiendo la tregua del hechizo," + "\n" +
                                "y dejando dentro, de nuevo y para" + "\n" +
                                "siempre, al soldado encantado." + "\n" );
                break;

            case 6:
                titulo.setText("La sala de los Abencerrajes");
                leyenda.setText("En esta sala, que fue alcoba" + "\n" +
                                "del sultán y por tanto carecía" + "\n" +
                                "de ventanas al exterior, fueron" + "\n" +
                                "asesinados los treinta y siete" + "\n" +
                                "caballeros de la familia " + "\n" +
                                "Abencerrajes reunidos con ocasión" + "\n" +
                                "de una fiesta allí celebrada. El" + "\n" +
                                "sultán ordenó decapitarlos, cegado" + "\n" +
                                "por lo celos, insuflados por los" + "\n" +
                                "caballeros de una familia rival," + "\n" +
                                "los Zenetes, que inventaron una" + "\n" +
                                "historia de amor entre uno de los" + "\n" +
                                "Abencerrajes y la sultana. Se" + "\n" +
                                "cuenta que el color rojizo que" + "\n" +
                                "aún se observa en la taza" + "\n" +
                                "existente en esta sala, y el canal" + "\n" +
                                "que lleva su agua hasta la Fuente" + "\n" +
                                "de los Leones, se debe a la sangre" + "\n" +
                                "derramada en aquella fecha." + "\n");
                break;

            case 7:
                titulo.setText("Los azulejos de Mexuar");
                leyenda.setText("Es ésta la sala más antigua del" + "\n" +
                                "palacio. El sultán se situaba en" + "\n" +
                                "ella, dentro de una cámara elevada," + "\n" +
                                "oculta por celosías, con el fin de" + "\n" +
                                "escuchar sin ser visto. Desde allí"  + "\n" +
                                "prestaba audiencia e impartía" + "\n" +
                                "justicia. Es un hecho históricamente" + "\n" +
                                "comprobado que el sultán tenía en la" + "\n" +
                                "época cualidad judicial, y sus" + "\n" +
                                "sentencias eran conocidas por" + "\n" +
                                "ecuánimes e imparciales. En su puerta," + "\n" +
                                "anunciando su razón de ser, había un" + "\n" +
                                "azulejo con un cartel que rezaba: " + "\n" +
                                "“Entra y pide. No temas pedir " + "\n" +
                                "justicia, que hallarla has”." + "\n");
                break;

        }





    }


}