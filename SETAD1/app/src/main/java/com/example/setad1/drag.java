package com.example.setad1;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class drag extends AppCompatActivity {

    public CriarImagem imagemTeste;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagemTeste = new CriarImagem(R.drawable.resistor, 1, 150, 150, 150, 150);
        TextView textView = findViewById(R.id.textView);
        textView.setText(Integer.toString(imagemTeste.PosicaoX()));
        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(Integer.toString(imagemTeste.PosicaoY()));
        //DragAndDrop ImagemTeste = new DragAndDrop("imageView", 150, 150);
        //DragAndDrop ImagemTestes = new DragAndDrop("imageViews", 150, 150);
        /*rootLayout = (ViewGroup) findViewById(R.id.view_root);
        img = (ImageView) rootLayout.findViewById(R.id.imageView);
        imgs = (ImageView) rootLayout.findViewById(R.id.imageViews);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(700, 150);
        RelativeLayout.LayoutParams layoutParamss = new RelativeLayout.LayoutParams(1500, 150);
        img.setLayoutParams(layoutParams);
        img.setOnTouchListener(new ChoiceTouchListener(_xDelta,_yDelta, rootLayout));
        imgs.setLayoutParams(layoutParamss);
        imgs.setOnTouchListener(new ChoiceTouchListener());*/
    }
    public class CriarImagem{
        public int _img;
        public int _imgId;
        public ViewGroup rootLayout;
        public int _xDelta;
        public int _yDelta;
        public int _posicao;
        public int _tamanho;
        public DragAndDrop dragAndDrop;

        public CriarImagem(int img, int imgId, int xDelta, int yDelta, int posicao, int tamanho){
            this._img = img;
            this._imgId = imgId;
            this._xDelta = xDelta;
            this._yDelta = yDelta;
            this._posicao = posicao;
            this._tamanho = tamanho;

            rootLayout = (ViewGroup) findViewById(R.id.view_root);

            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setId(this._imgId);
            imageView.setImageResource(this._img);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(this._posicao, this._tamanho));
            rootLayout.addView(imageView);
            this.dragAndDrop = new DragAndDrop(this._imgId, this._xDelta, this._yDelta, this._posicao, this._tamanho);
        }

        public int PosicaoX(){
            return this.dragAndDrop.PosicaoX();
        }

        public int PosicaoY(){
            return this.dragAndDrop.PosicaoY();
        }
    }

    public class DragAndDrop{
        public ImageView img;
        public ViewGroup rootLayout;
        public int _xDelta;
        public int _yDelta;
        public int imgId;
        public int _posicao;
        public int _tamanho;
        public ChoiceTouchListener listener;

        public DragAndDrop(int idImagem, int xDelta, int yDelta, int posicao, int tamanho){
            this.imgId = idImagem;
            this._xDelta = xDelta;
            this._yDelta = yDelta;
            this._posicao = posicao;
            this._tamanho = tamanho;

            rootLayout = (ViewGroup) findViewById(R.id.view_root);
            this.img = (ImageView) rootLayout.findViewById(this.imgId); //Alterar id
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this._posicao, this._tamanho);
            this.img.setLayoutParams(layoutParams);
            this.listener = new ChoiceTouchListener(this._xDelta, this._yDelta, rootLayout);
            this.img.setOnTouchListener(this.listener);
        }

        public int PosicaoX(){
            return this.listener.PosicaoX();
        }

        public int PosicaoY(){
            return this.listener.PosicaoY();
        }

    }

    private static final class ChoiceTouchListener implements View.OnTouchListener {
        public ViewGroup rootLayout;
        public int _xDelta;
        public int _yDelta;

        public ChoiceTouchListener(int xDelta, int yDelta, ViewGroup _rootLayout){
            this._xDelta = xDelta;
            this._yDelta = yDelta;
            this.rootLayout = _rootLayout;
        }

        public int PosicaoX(){
            return _xDelta;
        }

        public int PosicaoY(){
            return _yDelta;
        }

        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    this._xDelta = X - lParams.leftMargin;
                    this._yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - this._xDelta;
                    layoutParams.topMargin = Y - this._yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }

            this.rootLayout.invalidate();
            return true;
        }
    }
   /* private final class ChoiceTouchListeners implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDeltas = X - lParams.leftMargin;
                    _yDeltas = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDeltas;
                    layoutParams.topMargin = Y - _yDeltas;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }
            rootLayout.invalidate();
            return true;
        }
    }*/
}

