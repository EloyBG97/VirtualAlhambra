package com.virtualalhambra;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;



import java.util.ArrayList;
import java.util.HashMap;

public class MultiTouchView extends View {

    private static final int STROKE_WIDTH = 10;
    private static final int CIRCLE_RADIUS = 15;
    private static final int PRECISION_UMBRAL = 70;

    private Paint drawingPointsPaint = null;
    private Paint drawingLinesPaint = null;

    // Contendrá los vertices de la figura a dibujar
    private ArrayList<PointF> pointsToTouch;
    // Contedrá la lista de aristas a pintar en la figura
    private ArrayList<Boolean> mustDrawLine;
    // Map que contiene los puntos adyacentes.
    private HashMap<PointF, Pair<PointF, PointF>> adjacentPoints;

    private PointF inicial = null;
    private PointF actual = null;
    private PointF pos_ant = null;
    private PointF pos_sig = null;
    private boolean atributo;


    public MultiTouchView(Context context) {
        super(context);
        this.atributo = false;
        initialize();
    }

    public MultiTouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.atributo = false;
        initialize();
    }

    public MultiTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.atributo = false;
        initialize();
    }

    private void initialize(){
        // 1. Indicar lista de puntos.
        pointsToTouch = new ArrayList();
        pointsToTouch.add(new PointF(375f,1515f));
        pointsToTouch.add(new PointF(560f,1429f));
        pointsToTouch.add(new PointF(610f,1310f));
        pointsToTouch.add(new PointF(530f,1195f));
        pointsToTouch.add(new PointF(615f,910f));
        pointsToTouch.add(new PointF(816f,655f));
        pointsToTouch.add(new PointF(931f,554f));
        pointsToTouch.add(new PointF(990f,350f));
        pointsToTouch.add(new PointF(890f, 180f));
        pointsToTouch.add(new PointF(743f,64f));
        pointsToTouch.add(new PointF(540f, 190f));
        pointsToTouch.add(new PointF(227f,513f));
        pointsToTouch.add(new PointF(187f,630f));
        pointsToTouch.add(new PointF(248f,688f));
        pointsToTouch.add(new PointF(368f,587f));
        pointsToTouch.add(new PointF(517f,546f));
        pointsToTouch.add(new PointF(608f,586f));
        pointsToTouch.add(new PointF(374f,835f));
        pointsToTouch.add(new PointF(240f,1221f));
        pointsToTouch.add(new PointF(414f,1424f));



        // Tomamos el número de puntos en una variable (por comodidad)
        int n = pointsToTouch.size();
        // 2. Generar map con puntos adyacentes.
        adjacentPoints = new HashMap<>();
        // 2.a El primer punto se une con el segundo y el último (marcha atrás).
        adjacentPoints.put(pointsToTouch.get(0),
                new Pair(pointsToTouch.get(1), pointsToTouch.get(n-1)));
        // 2.b El resto de los puntos excepto el último los podemos tratar en un bucle.
        for (int i = 1; i < n - 1; ++i) {
            adjacentPoints.put(pointsToTouch.get(i),
                    new Pair(pointsToTouch.get(i+1), pointsToTouch.get(i-1)));
        }

        // 2.c El último punto se une con el primero y el penúltimo
        adjacentPoints.put(pointsToTouch.get(n-1),
                           new Pair(pointsToTouch.get(0), pointsToTouch.get(n-2)));

        // 3. Generar estructura de aristas.
        mustDrawLine = new ArrayList<Boolean>();
        for (int i = 0; i < n; ++i){
            mustDrawLine.add(false);
        }


        // 4. Creamos el paint en el que vamos a poner los puntos
        drawingPointsPaint = new Paint();
        drawingPointsPaint.setColor(Color.MAGENTA);
        drawingPointsPaint.setStrokeWidth(STROKE_WIDTH);
        drawingPointsPaint.setStyle(Paint.Style.FILL);
        drawingPointsPaint.setAntiAlias(true);

        // 5. Creamos el paint en el que vamos a poner las lineas
        drawingLinesPaint = new Paint();
        drawingLinesPaint.setColor(Color.BLUE);
        drawingLinesPaint.setStrokeWidth(STROKE_WIDTH);
        drawingLinesPaint.setStyle(Paint.Style.FILL);
        drawingLinesPaint.setAntiAlias(true);
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. Pintamos los puntos en pantalla
        for (PointF point: pointsToTouch) {
            canvas.drawCircle(point.x, point.y, CIRCLE_RADIUS, drawingPointsPaint);
        }

        for (int i = 0; i < mustDrawLine.size() - 1; ++i) {
            if (mustDrawLine.get(i)){
                canvas.drawLine(pointsToTouch.get(i).x, pointsToTouch.get(i).y,
                        pointsToTouch.get(i+1).x, pointsToTouch.get(i+1).y, drawingLinesPaint);
            }
        }
        if (mustDrawLine.get(mustDrawLine.size() - 1)) {
            canvas.drawLine(pointsToTouch.get(mustDrawLine.size() - 1).x, pointsToTouch.get(mustDrawLine.size() - 1).y,
                    pointsToTouch.get(0).x, pointsToTouch.get(0).y, drawingLinesPaint);
        }

    }

    private boolean isAdjacent(PointF point1, PointF point2) {
        Pair<PointF, PointF> adjacents = adjacentPoints.get(point1);

        return point2 == adjacents.first || point2 == adjacents.second;
    }

    private double euclideanDistance(PointF point1, PointF point2) {
        return Math.sqrt(Math.pow((point1.x - point2.x), 2) + Math.pow((point1.y - point2.y), 2));
    }
    private PointF closerPoint(PointF currentPoint) {
        double distancia = 999999999999999.0;
        PointF closerPoint = null;

        for (PointF point: pointsToTouch) {
            if (currentPoint.x <= point.x + PRECISION_UMBRAL &&
                    currentPoint.x >= point.x - PRECISION_UMBRAL &&
                    currentPoint.y <= point.y + PRECISION_UMBRAL &&
                    currentPoint.y >= point.y - PRECISION_UMBRAL) {
                double distancia_nueva = euclideanDistance(currentPoint, point);
                if (distancia_nueva < distancia) {
                    distancia = distancia_nueva;
                    closerPoint = point;
                }

            }
        }
        return closerPoint;

    }

    public void reiniciar() {
        for (int i = 0; i < mustDrawLine.size(); ++i) {
            mustDrawLine.set(i, false);
        }
        invalidate();

    }
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // Define que accion se esta realizando en la pantalla
        // getAction(): clase de acción que se está ejecutando.
        // ACTION_MASK: máscara de bits de partes del código de acción.
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch(action) {
            // Pulsamos
            case MotionEvent.ACTION_DOWN: {
                pos_sig = new PointF(event.getX(0),event.getY(0));
                if(pos_sig != null){
                    inicial = closerPoint(pos_sig);
                    pos_sig = inicial;
                }
                break;
            }
            // Movemos
            case MotionEvent.ACTION_MOVE:   {
                pos_ant = inicial;
                pos_sig = new PointF(event.getX(0),event.getY(0));
                if(pos_sig != null){
                    actual = closerPoint(pos_sig);
                    pos_sig = actual;
                }

                if (pos_ant != null && pos_sig != null) {
                    if (isAdjacent(pos_ant, pos_sig)) {
                        Log.d("INFO", "PAREJA CORRECTA");
                        if (pointsToTouch.indexOf(pos_ant) == 0 && pointsToTouch.indexOf(pos_sig) != 1)
                            mustDrawLine.set(pointsToTouch.indexOf(pos_sig), true);
                        else if (pointsToTouch.indexOf(pos_sig) == 0 && pointsToTouch.indexOf(pos_ant) != 1)
                            mustDrawLine.set(pointsToTouch.indexOf(pos_ant), true);
                        else if (pointsToTouch.indexOf(pos_ant) < pointsToTouch.indexOf(pos_sig))
                            mustDrawLine.set(pointsToTouch.indexOf(pos_ant), true);
                        else
                            mustDrawLine.set(pointsToTouch.indexOf(pos_sig), true);
                        invalidate();
                        if (!mustDrawLine.contains(false)) {
                            setAtributo(true);
                        }

                    }
                    inicial = pos_sig;
                }
                break;
            }
            // Levantamos
            case MotionEvent.ACTION_UP:   {
                for (int i = 0; i < mustDrawLine.size(); ++i) {
                    mustDrawLine.set(i, false);
                }
                invalidate();
                break;
            }

            // Pulsamos con mas de un dedo
            /*
            case MotionEvent.ACTION_POINTER_DOWN: {
                // Si estamos pulsando con dos dedos
                if (event.getPointerCount() == 2) {

                    PointF pos1 = new PointF(event.getX(0), event.getY(0));
                    PointF pos2 = new PointF(event.getX(1), event.getY(1));

                    Log.d("INFO", "PUNTO 1: " + pos1.x + " " + pos1.y);
                    Log.d("INFO", "PUNTO 2: " + pos2.x + " " + pos2.y);
                    // 1. Buscamos si existe un puntos a tocar cerca de las posiciones seleccioandas.
                    PointF punto_inicial = closerPoint(pos1);
                    PointF punto_final = closerPoint(pos2);
                    int idx1 = pointsToTouch.indexOf(punto_inicial);
                    int idx2 = pointsToTouch.indexOf(punto_final);
                    if (punto_inicial != null)
                        Log.d("INFO", "PUNTO 1 (SEL): " + idx1);
                    if (punto_final != null)
                        Log.d("INFO", "PUNTO 2 (SEL): " + idx2);

                    if (punto_inicial != null && punto_final != null) {
                        if (isAdjacent(punto_inicial, punto_final)) {
                            Log.d("INFO", "PAREJA CORRECTA");
                            if (idx1 == 0 && idx2 != 1)
                                mustDrawLine.set(idx2, true);
                            else if (idx2 == 0 && idx1 != 1)
                                mustDrawLine.set(idx1, true);
                            else if (idx1 < idx2)
                                mustDrawLine.set(idx1, true);
                            else
                                mustDrawLine.set(idx2, true);
                            invalidate();
                            if (!mustDrawLine.contains(false)) {
                                correctPattern();
                            }
                            if (mustDrawLine.get(0))
                                Log.d("INFO", "ARISTA 0");
                            if (mustDrawLine.get(1))
                                Log.d("INFO", "ARISTA 1");
                        }
                        else if (punto_inicial.x != punto_final.x && punto_inicial.y != punto_final.y) {
                            Log.d("INFO", "PAREJA INCORRECTA");
                            for (int i = 0; i < mustDrawLine.size(); ++i) {
                                mustDrawLine.set(i, false);
                            }
                            invalidate();
                        }
                    }
                }
                break;
            }
            // Levantamos
            case MotionEvent.ACTION_POINTER_UP:   {
                // Borra la pantalla
                break;
            }
            */
        }


        return true;
    }

    public boolean getAtributo(){
        return  this.atributo;
    }
    
    private void setAtributo(boolean b) {
        this.atributo = b;
    }

    private PointF getMidPoint(float x1, float y1, float x2, float y2) {
        PointF point = new PointF();
        float x = x1 + x2;
        float y = y1 + y2;
        point.set(x / 2, y / 2);
        return point;
    }

}
