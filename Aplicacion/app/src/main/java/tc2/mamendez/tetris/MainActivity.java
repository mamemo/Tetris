package tc2.mamendez.tetris;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> ImageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.gridLayout);

        inicializarGrid(grid);

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

    }

    public void mover_abajo(){

    }

    public void mover_derecha(){

    }

    public void rotar(){

    }


    public void inicializarGrid(GridLayout grid){
        int col = grid.getColumnCount();
        int row = grid.getRowCount();

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
        r = 0; c=0;
        for (; c<col; c++){
            ImageView iv = new ImageView(this.getApplicationContext());
            iv.setImageResource(R.drawable.boxie_gris);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(r);
            params.columnSpec = GridLayout.spec(c);
            iv.setLayoutParams(params);
            grid.addView(iv);
        }
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
        r=1; c=1;
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
}
