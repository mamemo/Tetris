package tc2.mamendez.tetris;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> ImageViews = new ArrayList<>();
    int[][] matriz_logica;
    Figura figura_actual;
    boolean oportunidad = true;
    int score = 0;
    int puntaje_por_linea = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_left).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mover_izquierda();
            }
        });
        findViewById(R.id.button_down).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mover_abajo();
            }
        });
        findViewById(R.id.button_right).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mover_derecha();
            }
        });
        findViewById(R.id.button_rotate).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rotar();
            }
        });

        final TextView txt_score = findViewById(R.id.score);
        txt_score.setText(Integer.toString(score));

        GridLayout grid = findViewById(R.id.gridLayout);
        int col = grid.getColumnCount();
        int row = grid.getRowCount();

        inicializarGrid(grid, col, row);
        matriz_logica = new int[row - 1][col - 2];

        crearFigura();

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        figura_actual.mover_abajo(matriz_logica);
                        actualizar_grid();
                        if (pararFigura()) {
                            lineas_horizontales();
                            txt_score.setText(Integer.toString(score));
                            //lineas_verticales();
                            crearFigura();
                        }
                    }
                });

            }
        }, 1000, 1000);

    }

    public void lineas_horizontales(){
        ArrayList<Integer> rows = new ArrayList<>();
        int cont;
        for (int i = 0; i < matriz_logica.length; i++){
            cont = 0;
            for (int j = 0; j<matriz_logica[i].length; j++){
                if(matriz_logica[i][j]!=0)
                    cont++;
            }
            if(cont == matriz_logica[i].length)
                rows.add(i);
        }

        for(Integer row : rows){
            for(int j =0; j<matriz_logica[row].length; j++){
                matriz_logica[row][j] = 0;
            }
            for(int i = row-1; i>=0; i-- ){
                for(int j=0; j<matriz_logica[i].length; j++){
                    if(matriz_logica[i][j] != 0){
                        matriz_logica[i+1][j] = matriz_logica[i][j];
                        matriz_logica[i][j] = 0;
                    }
                }
            }
            score += puntaje_por_linea;
            actualizar_grid();
        }
    }

    public void crearFigura() {
        figura_actual = null;
        switch (new Random().nextInt(7)){
            case 0:
                figura_actual = new Cuadrado();
                break;
            case 1:
                figura_actual = new LDerecha();
                break;
            case 2:
                figura_actual = new Linea();
                break;
            case 3:
                figura_actual = new LIzquierda();
                break;
            case 4:
                figura_actual = new SDerecha();
                break;
            case 5:
                figura_actual = new SIzquierda();
                break;
            case 6:
                figura_actual = new Triangulo();
                break;
        }
        figura_actual.iniciar_figura(matriz_logica);
        actualizar_grid();
    }

    public boolean pararFigura() {
        if (figura_actual.hayDebajo(matriz_logica)){
            if(oportunidad){
                oportunidad = false;
                return false;
            }else{
                oportunidad = true;
                return true;
            }
        }
        return false;
    }

    public void mover_izquierda() {
        figura_actual.mover_izquierda(matriz_logica);
        actualizar_grid();
    }

    public void mover_abajo() {
        figura_actual.mover_abajo(matriz_logica);
        actualizar_grid();
    }

    public void mover_derecha() {
        figura_actual.mover_derecha(matriz_logica);
        actualizar_grid();
    }

    public void rotar() {
        figura_actual.rotar(matriz_logica);
        actualizar_grid();
    }


    public void inicializarGrid(GridLayout grid, int col, int row) {

        //Columna Izquierda
        int r = 0, c = 0;
        for (; r < row; r++) {
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }

        //Columna derecha
        r = 0;
        c = col - 1;
        for (; r < row; r++) {
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }

        //Fila de arriba
//        r = 0; c=0;
//        for (; c<col; c++){
//            ImageView iv = new ImageView(this.getApplicationContext());
//            iv.setImageResource(R.drawable.boxie_gris);
//            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//            params.rowSpec = GridLayout.spec(r);
//            params.columnSpec = GridLayout.spec(c);
//            iv.setLayoutParams(params);
//            grid.addView(iv);
//        }
        //Fila de abajo
        r = row - 1;
        c = 0;
        for (; c < col; c++) {
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }

        //Tablero
        r = 0;
        c = 1;
        for (; r < row - 1; r++) {
            for (; c < col - 1; c++) {
                ImageView iv = new ImageView(this.getApplicationContext());
                iv.setImageResource(R.drawable.boxie_blanca);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(r);
                params.columnSpec = GridLayout.spec(c);
                iv.setLayoutParams(params);
                grid.addView(iv);
                ImageViews.add(iv);
            }
            c = 1;
        }
    }


    public void actualizar_grid() {
        for (int i = 0; i < matriz_logica.length; i++) {
            for (int j = 0; j < matriz_logica[i].length; j++) {
                int image = 0;
                switch (matriz_logica[i][j]) {
                    case 0:
                        image = R.drawable.boxie_blanca;
                        break;
                    case 1:
                        image = R.drawable.boxie_azul;
                        break;
                    case 2:
                        image = R.drawable.boxie_morada;
                        break;
                    case 3:
                        image = R.drawable.boxie_roja;
                        break;
                    case 4:
                        image = R.drawable.boxie_verde;
                        break;
                }
                ImageViews.get(i * matriz_logica[i].length + j).setImageResource(image);
            }
        }
    }
}
