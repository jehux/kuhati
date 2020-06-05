package com.example.kuhati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kuhati.adapters.DescripcionRecetaAdapter;
import com.example.kuhati.models.DescripcionReceta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;

public class buscar extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DescripcionRecetaAdapter mAdapter;
    private RecyclerView mRecycleView;
    private ArrayList<DescripcionReceta> mDescripcionRecetaList = new ArrayList<>();
    private EditText barraBuscador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Toast toast = Toast.makeText(getApplicationContext(),
                "Mensaje", Toast.LENGTH_SHORT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //buscador del layout
        barraBuscador = (EditText) findViewById(R.id.editText5);
        //botones del layout
        Button btnReceta = (Button) findViewById(R.id.button5);
        Button btnIngrediente = (Button) findViewById(R.id.button4);
        //recyclerView para mostrar la lista de datos
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerViewRecetas);
        //tipo de muestra de datos, horizontal, vertical, grilla
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        //instancia de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent (v.getContext(), Receta.class);
                //startActivityForResult(intent, 0);
                String buscar = barraBuscador.getText().toString();
                getMessagesFromFirebase(buscar,0);
            }
        });
        btnIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buscar = barraBuscador.getText().toString();
                //Intent intent = new Intent (v.getContext(), Receta.class);
                //startActivityForResult(intent, 0);
                getMessagesFromFirebase(buscar,1);
            }
        });

    }
    protected void getMessagesFromFirebase(final String buscar, final int tipoBusqueda){


        mDatabase.child("recetas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    mDescripcionRecetaList.clear();

                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        String titulo = ds.child("nombre").getValue().toString();
                        String descripcion = ds.child("descripcion").getValue().toString();
                        String urlImagen = ds.child("imagen").getValue().toString();
                        String ingredientes = ds.child("ingredientes").getValue().toString();
                        String procedimiento = ds.child("preparacion").getValue(String.class);

                        switch (tipoBusqueda){
                            case 0: //buscar por receta
                                if (titulo.contains(buscar)){
                                    mDescripcionRecetaList.add(new DescripcionReceta(titulo,urlImagen,descripcion,ingredientes,procedimiento));
                                }
                                break;
                            case 1: //busca por ingrediente
                                if (ingredientes.indexOf(buscar) !=-1){
                                    mDescripcionRecetaList.add(new DescripcionReceta(titulo,urlImagen,descripcion,ingredientes,procedimiento));
                                }
                                break;
                            default:
                                mDescripcionRecetaList.add(new DescripcionReceta(titulo,urlImagen,descripcion,ingredientes,procedimiento));
                                break;
                        }
                    }
                    mAdapter = new DescripcionRecetaAdapter(getApplicationContext(), mDescripcionRecetaList, R.layout.cuadro_receta);
                    mRecycleView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Error 2 en consultar base", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
    }
}
