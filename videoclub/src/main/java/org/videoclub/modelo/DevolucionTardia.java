package org.videoclub.modelo;

public class DevolucionTardia {
    private int id_incidencia;
    private int id_alquiler;
    private int dias_retraso;
    private double precio;
    private boolean pagado;

    public DevolucionTardia(int id_incidencia, int id_alquiler, int dias_retraso, double precio, boolean pagado) {
        this.id_incidencia = id_incidencia;
        this.id_alquiler = id_alquiler;
        this.dias_retraso = dias_retraso;
        this.precio = precio;
        this.pagado = pagado;
    }

    public int getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(int id_incidencia) {
        this.id_incidencia = id_incidencia;
    }

    public int getId_alquiler() {
        return id_alquiler;
    }

    public void setId_alquiler(int id_alquiler) {
        this.id_alquiler = id_alquiler;
    }

    public int getDias_retraso() {
        return dias_retraso;
    }

    public void setDias_retraso(int dias_retraso) {
        this.dias_retraso = dias_retraso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
