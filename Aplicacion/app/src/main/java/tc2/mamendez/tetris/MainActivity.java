package tc2.mamendez.tetris;

import android.graphics.Typeface;
import android.os.CountDownTimer;
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

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> ImageViews = new ArrayList<>();
    int [][] matriz_logica;
    Figura figura_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.gridLayout);
        int col = grid.getColumnCount();
        int row = grid.getRowCount();

        inicializarGrid(grid,col,row);
        Figura triangulo = new LDerecha();
        figura_actual = triangulo;

        matriz_logica= new int[row-1][col-2];

        triangulo.iniciar_figura(matriz_logica);
        actualizar_grid();

        findViewById(R.id.button_left).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mover_izquierda();
            }
        });
        findViewById(R.id.button_down).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mover_abajo();
            }
        });
        findViewById(R.id.button_right).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mover_derecha();
            }
        });
        findViewById(R.id.button_rotate).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                rotar();
            }
        });

    }

    public void mover_izquierda(){
        figura_actual.mover_izquierda(matriz_logica);
        actualizar_grid();
    }

    public void mover_abajo(){
        figura_actual.mover_abajo(matriz_logica);
        actualizar_grid();
    }

    public void mover_derecha(){
        figura_actual.mover_derecha(matriz_logica);
        actualizar_grid();
    }

    public void rotar(){
        figura_actual.rotar(matriz_logica);
        actualizar_grid();
    }


    public void inicializarGrid(GridLayout grid, int col, int row){

        //Columna Izquierda
        int r = 0, c=0;
        for (; r<row; r++){
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }

        //Columna derecha
        r = 0; c=col-1;
        for (; r<row; r++){
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
        r = row-1; c=0;
        for (; c<col; c++){
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }

        //Tablero
        r=0; c=1;
        for(;r<row-1; r++){
            for(;c<col-1;c++){
                ImageView iv = new ImageView(this.getApplicationContext());
                iv.setImageResource(R.drawable.boxie_blanca);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(r);
                params.columnSpec = GridLayout.spec(c);
                iv.setLayoutParams(params);
                grid.addView(iv);
                ImageViews.add(iv);
            }
            c=1;
        }
    }


    public void actualizar_grid(){
        for(int i=0; i<matriz_logica.length; i++){
            for(int j=0; j<matriz_logica[i].length; j++){
                int image=0;
                switch(matriz_logica[i][j]){
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
                ImageViews.get(i*matriz_logica[i].length+j).setImageResource(image);
            }
        }
    }
}
