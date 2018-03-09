package tc2.mamendez.tetris;

import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mauro on 3/6/2018.
 */

public class Triangulo extends Figura {

    /*Posiciones:
    * 0 Cuadrado de Arriba         *
    * 1 Cuadrado de Izquierda    *
    * 2 Cuadrado del Centro        *
    * 3 Cuadrado de Derecha          *
    */

    @Override
    public void iniciar_figura(int[][] logica) {
        int posicion_inicial = (int) logica[0].length/2;
        Posiciones[0][0] = 0;
        Posiciones[0][1] = posicion_inicial;
        Posiciones[1][0] = 1;
        Posiciones[1][1] = posicion_inicial-1;
        Posiciones[2][0] = 1;
        Posiciones[2][1] = posicion_inicial;
        Posiciones[3][0] = 1;
        Posiciones[3][1] = posicion_inicial+1;

        logica[0][posicion_inicial] = color;
        logica[1][posicion_inicial-1] = color;
        logica[1][posicion_inicial] = color;
        logica[1][posicion_inicial+1] = color;
    }

    @Override
    public void mover_derecha(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for(int i = 0; i<Posiciones.length; i++){
            if(Posiciones[i][1]==logica[0].length-1 || logica[Posiciones[i][0]][Posiciones[i][1]+1] != 0)
                accept = false;
        }

        if(accept)
            for(int i = 0; i<Posiciones.length; i++){
                Posiciones[i][1] +=1;
            }
        actualizar_Posiciones(logica);
    }

    @Override
    public void mover_izquierda( int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for(int i = 0; i<Posiciones.length; i++){
            if(Posiciones[i][1]==0 || logica[Posiciones[i][0]][Posiciones[i][1]-1] != 0)
                accept = false;
        }

        if(accept)
            for(int i = 0; i<Posiciones.length; i++){
                Posiciones[i][1] -=1;
            }
        actualizar_Posiciones(logica);
    }

    @Override
    public void mover_abajo( int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for(int i = 0; i<Posiciones.length; i++){
            if(Posiciones[i][0]==logica.length-1 || logica[Posiciones[i][0]+1][Posiciones[i][1]] != 0)
                accept = false;
        }

        if(accept)
            for(int i = 0; i<Posiciones.length; i++){
                Posiciones[i][0] +=1;
            }
        actualizar_Posiciones(logica);

    }

    @Override
    public void rotar(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;

    }
}
