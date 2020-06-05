package com.example.kuhati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Receta extends AppCompatActivity {
    public TextView titulo;
    public ImageButton imageButton;
    public TextView TextViewIngredientes;
    public TextView TextViewProcedimiento;
    public TextView TextViewDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        titulo = findViewById(R.id.textViewTitulo);
        imageButton = findViewById(R.id.imageButton);
        TextViewIngredientes = findViewById(R.id.textView4);
        TextViewProcedimiento = findViewById(R.id.textView5);
        TextViewDescripcion = findViewById(R.id.textViewDescripcion);
        getIncomingIntent();
    }
    private void getIncomingIntent(){
        if (getIntent().hasExtra("nombre") && getIntent().hasExtra("imagen")){
            String imagen = getIntent().getStringExtra("imagen");
            String nombre = getIntent().getStringExtra("nombre");
            String descripcion = getIntent().getStringExtra("descripcion");
            String ingredientes = getIntent().getStringExtra("ingredientes");
            String procedimiento = getIntent().getStringExtra("procedimiento");

            titulo.setText(nombre);
            /*Picasso.get()
                    .load(imagen)
                    .error(R.drawable.chilaquiles)
                    .fit()
                    .centerInside()
                    .into(imageButton);*/
            TextViewIngredientes.setText(ingredientes);
            TextViewDescripcion.setText(descripcion);
            TextViewProcedimiento.setText(procedimiento);

        }
    }
}
