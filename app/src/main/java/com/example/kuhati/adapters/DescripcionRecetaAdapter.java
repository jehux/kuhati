package com.example.kuhati.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuhati.R;
import com.example.kuhati.Receta;
import com.example.kuhati.models.DescripcionReceta;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DescripcionRecetaAdapter extends RecyclerView.Adapter<DescripcionRecetaAdapter.ViewHolder> {
    private int resource;
    private ArrayList<DescripcionReceta> DescripcionRecetaList;
    private Context mContext;
    private DescripcionReceta descripcionReceta;

    public DescripcionRecetaAdapter(Context context, ArrayList<DescripcionReceta> DescripcionRecetaList, int resource ){
        this.DescripcionRecetaList = DescripcionRecetaList;
        this.resource = resource;
        this.mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        descripcionReceta = DescripcionRecetaList.get(position);
        //inserta el titulo al cuadro de receta
        holder.textViewTitulo.setText(descripcionReceta.getTitulo());
        //inserta la imagen url al ImageButton del cuadro receta
        Picasso.get()
                .load(descripcionReceta.getImagen())
                .error(R.drawable.chilaquiles)
                .fit()
                .centerInside()
                .into(holder.imageButtonReceta);

        holder.imageButtonReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, Receta.class);
                mIntent.putExtra("imagen",descripcionReceta.getImagen());
                mIntent.putExtra("nombre",descripcionReceta.getTitulo());
                mIntent.putExtra("descripcion",descripcionReceta.getDescripcion());
                mIntent.putExtra("procedimiento",descripcionReceta.getProcedimiento());
                mIntent.putExtra("ingredientes",descripcionReceta.getIngredientes());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DescripcionRecetaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitulo;
        private ImageButton imageButtonReceta;
        public View view;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            this.textViewTitulo = (TextView) view.findViewById(R.id.textViewDescripcionReceta);
            this.imageButtonReceta = (ImageButton) view.findViewById(R.id.imageButton3);
        }

    }
}
