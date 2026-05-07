package org.videoclub.dao;

import org.videoclub.conexion.Conexion;
import org.videoclub.modelo.Stock;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    public void insertar(Stock stock) {
        String sql = "INSERT INTO stock (id_pelicula, estado) VALUES (?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, stock.getId_pelicula());
            ps.setString(2, stock.getEstado());
            ps.executeUpdate();
            System.out.println("Copia insertada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar copia: " + e.getMessage());
        }
    }

    public List<Stock> listarTodos() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stock ORDER BY id_articulo ASC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Stock s = new Stock(
                        rs.getInt("id_articulo"),
                        rs.getInt("id_pelicula"),
                        rs.getString("estado")
                );
                stocks.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar stock: " + e.getMessage());
        }
        return stocks;
    }

    public Stock buscarPorId(int id) {
        String sql = "SELECT * FROM stock WHERE id_articulo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Stock(
                        rs.getInt("id_articulo"),
                        rs.getInt("id_pelicula"),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar copia: " + e.getMessage());
        }
        return null;
    }

    public void actualizarEstado(int id, String estado) {
        String sql = "UPDATE stock SET estado=? WHERE id_articulo=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Estado actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM stock WHERE id_articulo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Copia eliminada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar copia: " + e.getMessage());
        }
    }
}