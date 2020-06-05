package com.example.kuhati.models;

public class DescripcionReceta  {
    private String titulo;
    private String imagen;
    private String descripcion;
    private String ingredientes;
    private String procedimiento;


    public DescripcionReceta(){
    }
    public DescripcionReceta(String titulo, String imagen, String descripcion, String ingredientes, String procedimiento){
        this.titulo=titulo;
        this.imagen=imagen;
        this.descripcion=descripcion;
        this.ingredientes = ingredientes;
        this.procedimiento = procedimiento;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
