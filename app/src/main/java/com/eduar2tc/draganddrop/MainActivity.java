package com.eduar2tc.draganddrop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView0;
    TextView textView1;
    TextView textView2;
    TextView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView0 = findViewById(R.id.textView0);
        this.textView1 = findViewById(R.id.textView1);
        this.textView2 = findViewById(R.id.textView2);
        this.target = findViewById(R.id.target);

        /*this.textView0.setOnLongClickListener(longClickListener);
        this.textView1.setOnLongClickListener(longClickListener);
        this.textView2.setOnLongClickListener(longClickListener);*/
        /*1) Poner los elementos en modo escucha para moverlos (OnTouch -> Move)*/
        this.textView0.setOnTouchListener(onTouchListener);
        this.textView1.setOnTouchListener(onTouchListener);
        this.textView2.setOnTouchListener(onTouchListener);
        this.target.setOnDragListener( dragListener ); //Elemento en modo de escucha sirve de destino de los elementos arrastrados

    }
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int motionEvent = event.getAction();
            switch ( motionEvent ){
                case MotionEvent.ACTION_MOVE:{
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder( v );
                    v.startDragAndDrop(data, myShadowBuilder, v, 0);
                    break;
                }
            }
            return true;
        }
    };
    /*View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder( v );
            v.startDragAndDrop(data, myShadowBuilder, v, 0);

            return true;
        }
    };*/


    View.OnDragListener dragListener = new View.OnDragListener() { //Evento para poner los elementos en escucha de ser drageados
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState(); //Obtiene el estado de la view
            switch( dragEvent ){
                case DragEvent.ACTION_DRAG_ENTERED: {

                    if( view.getId() == R.id.textView0 ){
                        target.setText(" Text view 0 is Dragged");
                    }
                    else if( view.getId() == R.id.textView1 ){
                        target.setText(" Text view 1 is Dragged");
                    }
                    else if( view.getId() == R.id.textView2 ){
                        target.setText(" Text view 2 is Dragged");
                    }
                    break;
                }
                case DragEvent.ACTION_DRAG_EXITED: {

                    if( view.getId() == R.id.textView0 ){
                        target.setText(" Text view 0 is Exited");
                    }
                    else if( view.getId() == R.id.textView1 ){
                        target.setText(" Text view 1 is Exited");
                    }
                    else if( view.getId() == R.id.textView2 ){
                        target.setText(" Text view 2 is Exited");
                    }
                    break;
                }
                case DragEvent.ACTION_DROP: {

                    if( view.getId() == R.id.textView0 ){
                        target.setText(" Text view 0 is Dropped");
                        textView0.animate()
                                .x(target.getX())
                                .y(target.getY())
                                .setDuration(700).start();
                    }
                    else if( view.getId() == R.id.textView1 ){
                        target.setText(" Text view 1 is Dropped");
                        textView1.animate()
                                .x(target.getX())
                                .y(target.getY())
                                .setDuration(100).start();
                    }
                    else if( view.getId() == R.id.textView2 ){
                        target.setText(" Text view 2 is Dropped");
                        textView2.animate()
                                .x(target.getX())
                                .y(target.getY())
                                .setDuration(1500).start();
                    }
                    /*view.animate()
                            .x(target.getX())
                            .y(target.getY())
                            .setDuration(700).start();*/ /*Anima textView -> Dropdestino*/
                    break;
                }

            }
            return true;
        }
    };
}