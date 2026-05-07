package org.videoclub.modelo;

import java.time.LocalDate;

public class Alquiler {

    private int id_alquiler;
    private int id_cliente;
    private int id_copia;
    private LocalDate fecha_alquiler;
    private LocalDate fecha_limite;
    private LocalDate fecha_devolucion;
    private double precio;

    public Alquiler(int id_alquiler, int id_cliente, int id_copia, LocalDate fecha_alquiler, LocalDate fecha_limite, LocalDate fecha_devolucion, double precio) {
        this.id_alquiler = id_alquiler;
        this.id_cliente = id_cliente;
        this.id_copia = id_copia;
        this.fecha_alquiler = fecha_alquiler;
        this.fecha_limite = fecha_limite;
        this.fecha_devolucion = fecha_devolucion;
        this.precio = precio;
    }

    public int getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(int id_alquiler) {
        this.id_alquiler = id_alquiler;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_copia() {
        return id_copia;
    }

    public void setId_copia(int id_copia) {
        this.id_copia = id_copia;
    }

    public LocalDate getFecha_alquiler() {
        return fecha_alquiler;
    }

    public void setFecha_alquiler(LocalDate fecha_alquiler) {
        this.fecha_alquiler = fecha_alquiler;
    }

    public LocalDate getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(LocalDate fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
