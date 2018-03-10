package tc2.mamendez.tetris;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mauro on 3/9/2018.
 */

public class Linea extends Figura {
    /*Posiciones:
    * 0 Cuadrado de Mas Izquierda   *
    * 1 Cuadrado de Izquierda        *
    * 2 Cuadrado de Derecha           *
    * 3 Cuadrado de Mas Derecha        *
    */

    @Override
    public void iniciar_figura(int[][] logica) {
        int posicion_inicial = (int) logica[0].length / 2;
        Posiciones[0][0] = 0;
        Posiciones[0][1] = posicion_inicial - 1;
        Posiciones[1][0] = 0;
        Posiciones[1][1] = posicion_inicial;
        Posiciones[2][0] = 0;
        Posiciones[2][1] = posicion_inicial + 1;
        Posiciones[3][0] = 0;
        Posiciones[3][1] = posicion_inicial + 2;

        logica[0][posicion_inicial - 1] = color;
        logica[0][posicion_inicial] = color;
        logica[0][posicion_inicial + 1] = color;
        logica[0][posicion_inicial + 2] = color;
    }


    @Override
    public void rotar(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        ArrayList<int[]> coordenadas = new ArrayList<>();
        switch (estado) {
            case 0:
                coordenadas.add(new int[]{Posiciones[0][0], Posiciones[0][1]});
                coordenadas.add(new int[]{Posiciones[1][0] - 1, Posiciones[1][1]-1});
                coordenadas.add(new int[]{Posiciones[2][0] - 2, Posiciones[2][1] - 2});
                coordenadas.add(new int[]{Posiciones[3][0] - 3, Posiciones[3][1] - 3});
                break;

            case 1:
                coordenadas.add(new int[]{Posiciones[0][0], Posiciones[0][1]});
                coordenadas.add(new int[]{Posiciones[1][0] + 1, Posiciones[1][1]+1});
                coordenadas.add(new int[]{Posiciones[2][0] + 2, Posiciones[2][1] + 1});
                coordenadas.add(new int[]{Posiciones[3][0] + 3, Posiciones[3][1] + 2});
                break;
        }
        ArrayList<Integer> contenido = new ArrayList<>();
        for (int[] coordenada : coordenadas) {
            if (coordenada[1] < 0 || coordenada[1] > logica[1].length - 1 || coordenada[0] > logica.length || coordenada[0] < 0 || logica[coordenada[0]][coordenada[1]] != 0)
                accept = false;
        }

        if (accept)
            switch (estado) {
                case 0:
                    Posiciones[1][1] -= 1;
                    Posiciones[1][0] -= 1;
                    Posiciones[2][0] -= 2;
                    Posiciones[2][1] -= 2;
                    Posiciones[3][0] -= 3;
                    Posiciones[3][1] -= 3;
                    estado = 1;
                    break;
                case 1:
                    Posiciones[1][1] += 1;
                    Posiciones[1][0] += 1;
                    Posiciones[2][0] += 2;
                    Posiciones[2][1] += 2;
                    Posiciones[3][0] += 3;
                    Posiciones[3][1] += 3;
                    estado = 0;
                    break;
            }
        actualizar_Posiciones(logica);

    }
}
