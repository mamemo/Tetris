package tc2.mamendez.tetris;

import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Mauro on 3/6/2018.
 */

public abstract class Figura {
    int[][] Posiciones = new int[4][2];
    Random random = new Random();
    int color = random.nextInt(4) + 1;
    int estado = 0;
    int movimientos = 0;

    public abstract void iniciar_figura(int[][] logica);

    public void mover_derecha(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for (int i = 0; i < Posiciones.length; i++) {
            if (Posiciones[i][1] == logica[0].length - 1 || logica[Posiciones[i][0]][Posiciones[i][1] + 1] != 0)
                accept = false;
        }

        if (accept)
            for (int i = 0; i < Posiciones.length; i++) {
                Posiciones[i][1] += 1;
            }
        actualizar_Posiciones(logica);
    }

    public void mover_izquierda(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for (int i = 0; i < Posiciones.length; i++) {
            if (Posiciones[i][1] == 0 || logica[Posiciones[i][0]][Posiciones[i][1] - 1] != 0)
                accept = false;
        }

        if (accept)
            for (int i = 0; i < Posiciones.length; i++) {
                Posiciones[i][1] -= 1;
            }
        actualizar_Posiciones(logica);
    }

    public boolean mover_abajo(int[][] logica) {
        limpiar_Posiciones(logica);

        boolean accept = true;
        for (int i = 0; i < Posiciones.length; i++) {
            if (Posiciones[i][0] == logica.length - 1 || logica[Posiciones[i][0] + 1][Posiciones[i][1]] != 0)
                accept = false;
        }

        if (accept) {
            for (int i = 0; i < Posiciones.length; i++) {
                Posiciones[i][0] += 1;
            }
            movimientos++;
        }

        if(movimientos == 0){
            return false;
        }

        actualizar_Posiciones(logica);
        return true;
    }

    public abstract void rotar(int[][] logica);

    public void limpiar_Posiciones(int[][] logica) {
        logica[Posiciones[0][0]][Posiciones[0][1]] = 0;
        logica[Posiciones[1][0]][Posiciones[1][1]] = 0;
        logica[Posiciones[2][0]][Posiciones[2][1]] = 0;
        logica[Posiciones[3][0]][Posiciones[3][1]] = 0;
    }

    public void actualizar_Posiciones(int[][] logica) {
        logica[Posiciones[0][0]][Posiciones[0][1]] = color;
        logica[Posiciones[1][0]][Posiciones[1][1]] = color;
        logica[Posiciones[2][0]][Posiciones[2][1]] = color;
        logica[Posiciones[3][0]][Posiciones[3][1]] = color;
    }

    public boolean hayDebajo(int[][] logica) {
        for (int i = 0; i < Posiciones.length; i++) {
            if (Posiciones[i][0] + 1 < logica.length) {
                if (logica[Posiciones[i][0] + 1][Posiciones[i][1]] != 0 && !parteDeFigura(Posiciones[i][0] + 1, Posiciones[i][1]))
                    return true;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean parteDeFigura(int x, int y) {
        for (int[] posicion : Posiciones) {
            if (posicion[0] == x && posicion[1] == y)
                return true;
        }
        return false;
    }
}
