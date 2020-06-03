package com.example.kuhati.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuhati.R;
import com.example.kuhati.models.DescripcionReceta;

import java.util.ArrayList;

public class DescripcionRecetaAdapter extends RecyclerView.Adapter<DescripcionRecetaAdapter.ViewHolder> {
    private int resource;
    private ArrayList<DescripcionReceta> DescripcionRecetaList;

    public DescripcionRecetaAdapter(ArrayList<DescripcionReceta> DescripcionRecetaList, int resource ){
        this.DescripcionRecetaList = DescripcionRecetaList;
        this.resource = resource;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DescripcionReceta descripcionReceta = DescripcionRecetaList.get(position);
        holder.textViewTitulo.setText(descripcionReceta.getTitulo());
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
        }

    }
}
