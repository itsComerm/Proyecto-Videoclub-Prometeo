package org.videoclub.dao;

import org.videoclub.conexion.Conexion;
import org.videoclub.modelo.Alquiler;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO {

    public void insertar(Alquiler alquiler) {
        String sql = "INSERT INTO alquileres (id_cliente, id_copia, fecha_alquiler, fecha_limite, fecha_devolucion, precio) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, alquiler.getId_cliente());
            ps.setInt(2, alquiler.getId_copia());
            ps.setDate(3, Date.valueOf(alquiler.getFecha_alquiler()));
            ps.setDate(4, Date.valueOf(alquiler.getFecha_limite()));
            ps.setDate(5, alquiler.getFecha_devolucion() != null ? Date.valueOf(alquiler.getFecha_devolucion()) : null);
            ps.setDouble(6, alquiler.getPrecio());
            ps.executeUpdate();
            System.out.println("Alquiler insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar alquiler: " + e.getMessage());
        }
    }

    public List<Alquiler> listarTodos() {
        List<Alquiler> alquileres = new ArrayList<>();
        String sql = "SELECT * FROM alquileres ORDER BY id_alquiler ASC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                Alquiler a = new Alquiler(
                        rs.getInt("id_alquiler"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_copia"),
                        rs.getDate("fecha_alquiler").toLocalDate(),
                        rs.getDate("fecha_limite").toLocalDate(),
                        fechaDevolucion != null ? fechaDevolucion.toLocalDate() : null,
                        rs.getDouble("precio")
                );
                alquileres.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alquileres: " + e.getMessage());
        }
        return alquileres;
    }

    public Alquiler buscarPorId(int id) {
        String sql = "SELECT * FROM alquileres WHERE id_alquiler = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                return new Alquiler(
                        rs.getInt("id_alquiler"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_copia"),
                        rs.getDate("fecha_alquiler").toLocalDate(),
                        rs.getDate("fecha_limite").toLocalDate(),
                        fechaDevolucion != null ? fechaDevolucion.toLocalDate() : null,
                        rs.getDouble("precio")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar alquiler: " + e.getMessage());
        }
        return null;
    }

    public void registrarDevolucion(int idAlquiler, LocalDate fechaDevolucion) {
        String sql = "UPDATE alquileres SET fecha_devolucion=? WHERE id_alquiler=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fechaDevolucion));
            ps.setInt(2, idAlquiler);
            ps.executeUpdate();
            System.out.println("Devolución registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar devolución: " + e.getMessage());
        }
    }

    public List<Alquiler> listarActivos() {
        List<Alquiler> alquileres = new ArrayList<>();
        String sql = "SELECT * FROM alquileres WHERE fecha_devolucion IS NULL";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alquiler a = new Alquiler(
                        rs.getInt("id_alquiler"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_copia"),
                        rs.getDate("fecha_alquiler").toLocalDate(),
                        rs.getDate("fecha_limite").toLocalDate(),
                        null,
                        rs.getDouble("precio")
                );
                alquileres.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alquileres activos: " + e.getMessage());
        }
        return alquileres;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM alquileres WHERE id_alquiler = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Alquiler eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar alquiler: " + e.getMessage());
        }
    }
}