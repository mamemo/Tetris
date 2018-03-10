package tc2.mamendez.tetris;

/**
 * Created by Mauro on 3/9/2018.
 */

public class Cuadrado extends Figura {

    /*Posiciones:
    * 0 Cuadrado de Izquierda Arriba   *
    * 1 Cuadrado de Derecha Arriba       *
    * 2 Cuadrado de Izquierda Abajo    *
    * 3 Cuadrado de Derecha Abajo        *
    *
    */

    @Override
    public void iniciar_figura(int[][] logica) {
        int posicion_inicial = (int) logica[0].length / 2;
        Posiciones[0][0] = 0;
        Posiciones[0][1] = posicion_inicial-1;
        Posiciones[1][0] = 0;
        Posiciones[1][1] = posicion_inicial;
        Posiciones[2][0] = 1;
        Posiciones[2][1] = posicion_inicial-1;
        Posiciones[3][0] = 1;
        Posiciones[3][1] = posicion_inicial;

        actualizar_Posiciones(logica);
    }

    @Override
    public void rotar(int[][] logica) {

    }
}
