package org.videoclub.modelo;

public class Stock {
    private int id_articulo;
    private int id_pelicula;
    private String estado;

    public Stock(int id_articulo, int id_pelicula, String estado) {
        this.id_articulo = id_articulo;
        this.id_pelicula = id_pelicula;
        this.estado = estado;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
