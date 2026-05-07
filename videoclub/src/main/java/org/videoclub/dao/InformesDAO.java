package org.videoclub.dao;

import org.videoclub.conexion.Conexion;

import java.sql.*;

public class InformesDAO {

    public static void peliculasMasAlquiladas() {
        String sql = "SELECT p.titulo, COUNT(a.id_alquiler) AS total_alquileres " +
                "FROM peliculas p " +
                "JOIN stock s ON p.id_pelicula = s.id_pelicula " +
                "JOIN alquileres a ON s.id_articulo = a.id_copia " +
                "GROUP BY p.titulo " +
                "ORDER BY total_alquileres DESC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("PELÍCULAS MÁS ALQUILADAS");
            while (rs.next()) {
                System.out.println(rs.getString("titulo") + " | Alquileres: " + rs.getInt("total_alquileres"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener películas más alquiladas: " + e.getMessage());
        }
    }

    public static void clientesConMasAlquileres() {
        String sql = "SELECT c.nombre, c.apellidos, COUNT(a.id_alquiler) AS total_alquileres " +
                "FROM clientes c " +
                "JOIN alquileres a ON c.id_cliente = a.id_cliente " +
                "GROUP BY c.nombre, c.apellidos " +
                "ORDER BY total_alquileres DESC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("CLIENTES CON MÁS ALQUILERES");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " | Alquileres: " + rs.getInt("total_alquileres"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes con más alquileres: " + e.getMessage());
        }
    }

    public static void devolucionesTardiasPendientes() {
        String sql = "SELECT c.nombre, c.apellidos, p.titulo, d.dias_retraso, d.importe " +
                "FROM devoluciones_tardias d " +
                "JOIN alquileres a ON d.id_alquiler = a.id_alquiler " +
                "JOIN clientes c ON a.id_cliente = c.id_cliente " +
                "JOIN stock s ON a.id_copia = s.id_articulo " +
                "JOIN peliculas p ON s.id_pelicula = p.id_pelicula " +
                "WHERE d.pagado = false " +
                "ORDER BY d.importe DESC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("DEVOLUCIONES TARDÍAS PENDIENTES");
            while (rs.next()) {
                System.out.println(
                        rs.getString("nombre") + " " + rs.getString("apellidos") + " | " +
                                rs.getString("titulo") + " | " +
                                rs.getInt("dias_retraso") + " días | " +
                                rs.getDouble("importe") + "€"
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener devoluciones tardías pendientes: " + e.getMessage());
        }
    }

    public static void peliculasSinStock() {
        String sql = "SELECT p.titulo, COUNT(s.id_articulo) AS copias_disponibles " +
                "FROM peliculas p " +
                "LEFT JOIN stock s ON p.id_pelicula = s.id_pelicula AND s.estado = 'disponible' " +
                "GROUP BY p.titulo " +
                "HAVING COUNT(s.id_articulo) = 0 " +
                "ORDER BY p.titulo";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("PELÍCULAS SIN STOCK DISPONIBLE");
            boolean hayResultados = false;
            while (rs.next()) {
                System.out.println(rs.getString("titulo"));
                hayResultados = true;
            }
            if (!hayResultados) {
                System.out.println("Todas las películas tienen copias disponibles.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener películas sin stock: " + e.getMessage());
        }
    }
}