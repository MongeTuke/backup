package com.example.setad1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class menuDeTrabalho extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button botaoVoltar;
    Button botaoresistor;
    Button botaofonte;
    Button botaotrilha;
    Button botaotrilhad;
    public int contador = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_de_trabalho);

        botaoVoltar=findViewById(R.id.botaoVoltar);
        drawerLayout=findViewById(R.id.drawer_layout);
        botaoresistor=findViewById(R.id.botaoresistor);
        botaofonte=findViewById(R.id.botaofonte);
        botaotrilha=findViewById(R.id.botaotrilha);
        botaotrilhad=findViewById(R.id.botaotrilhad);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botaoresistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addresistor(view);
            }
        });
        botaofonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Addvccc(view);
            }
        });
        botaotrilha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addtrilha(view);
            }
        });
        botaotrilhad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Addtrilhad(view);
            }
        });
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

            //
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
        public int PosicaoY() {
            return this.dragAndDrop.PosicaoY();
        }
    }

    public int contador(){
        contador++;
        return contador;
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

    private final class ChoiceTouchListener implements View.OnTouchListener {
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
        //Usa da posição X e Y para fazer o movimento na tela
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

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
       closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void Addresistor(View view){
        new CriarImagem(R.drawable.resistor,contador(),150,150,1500,150);
    }
    public void Addcapacitor(View view){
        new CriarImagem(R.drawable.capacitor,contador(),150,150,1500,150 );
    }
    public void Adddiodo(View view){
        new CriarImagem(R.drawable.diodo,contador(),150,150,1500,150);
    }
    public void Addledc(View view){
        new CriarImagem(R.drawable.ledc,contador(),150,150,1500,150);
    }
    public void Addvcc(View view){
        new CriarImagem(R.drawable.vcc,contador(),150,150,1500,150);
    }
    public void Addvccc(View view){
        new CriarImagem(R.drawable.vccc,contador(),150,150,1500,150);
    }
    public void Addgnd(View view){
        new CriarImagem(R.drawable.gnd,contador(),150,150,1500,150);
    }
    public void Addtrilha(View view){
        new CriarImagem(R.drawable.wiree,contador(),150,150,1500,150);
    }
    public void Addtrilhad(View view){
        new CriarImagem(R.drawable.wired,contador(),150,150,1500,150);
    }
}