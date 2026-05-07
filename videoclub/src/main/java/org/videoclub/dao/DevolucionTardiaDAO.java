package org.videoclub.dao;

import org.videoclub.conexion.Conexion;
import org.videoclub.modelo.DevolucionTardia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevolucionTardiaDAO {

    public void insertar(DevolucionTardia devolucion) {
        String sql = "INSERT INTO devoluciones_tardias (id_alquiler, dias_retraso, importe, pagado) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, devolucion.getId_alquiler());
            ps.setInt(2, devolucion.getDias_retraso());
            ps.setDouble(3, devolucion.getPrecio());
            ps.setBoolean(4, devolucion.isPagado());
            ps.executeUpdate();
            System.out.println("Devolución tardía registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar devolución tardía: " + e.getMessage());
        }
    }

    public List<DevolucionTardia> listarTodos() {
        List<DevolucionTardia> devoluciones = new ArrayList<>();
        String sql = "SELECT * FROM devoluciones_tardias";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DevolucionTardia d = new DevolucionTardia(
                        rs.getInt("id_incidencia"),
                        rs.getInt("id_alquiler"),
                        rs.getInt("dias_retraso"),
                        rs.getDouble("importe"),
                        rs.getBoolean("pagado")
                );
                devoluciones.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar devoluciones tardías: " + e.getMessage());
        }
        return devoluciones;
    }

    public DevolucionTardia buscarPorAlquiler(int idAlquiler) {
        String sql = "SELECT * FROM devoluciones_tardias WHERE id_alquiler = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlquiler);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DevolucionTardia(
                        rs.getInt("id_incidencia"),
                        rs.getInt("id_alquiler"),
                        rs.getInt("dias_retraso"),
                        rs.getDouble("importe"),
                        rs.getBoolean("pagado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar devolución tardía: " + e.getMessage());
        }
        return null;
    }

    public void marcarComoPagado(int idAlquiler) {
        String sql = "UPDATE devoluciones_tardias SET pagado=true WHERE id_alquiler=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlquiler);
            ps.executeUpdate();
            System.out.println("Devolución tardía actualizada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar devolución tardía: " + e.getMessage());
        }
    }

    public List<DevolucionTardia> listarNoPagadas() {
        List<DevolucionTardia> devoluciones = new ArrayList<>();
        String sql = "SELECT * FROM devoluciones_tardias WHERE pagado = false";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DevolucionTardia d = new DevolucionTardia(
                        rs.getInt("id_incidencia"),
                        rs.getInt("id_alquiler"),
                        rs.getInt("dias_retraso"),
                        rs.getDouble("importe"),
                        rs.getBoolean("pagado")
                );
                devoluciones.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar devoluciones tardías: " + e.getMessage());
        }
        return devoluciones;
    }
}