package org.videoclub.dao;

import org.videoclub.conexion.Conexion;
import org.videoclub.modelo.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {

    public void insertar(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas (titulo, director, anio, genero) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setInt(3, pelicula.getAnio());
            ps.setString(4, pelicula.getGenero());
            ps.executeUpdate();
            System.out.println("Película insertada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar película: " + e.getMessage());
        }
    }

    public List<Pelicula> listarTodos() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas ORDER BY id_pelicula ASC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula p = new Pelicula(
                        rs.getInt("id_pelicula"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getInt("anio"),
                        rs.getString("genero")
                );
                peliculas.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar películas: " + e.getMessage());
        }
        return peliculas;
    }

    public Pelicula buscarPorId(int id) {
        String sql = "SELECT * FROM peliculas WHERE id_pelicula = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Pelicula(
                        rs.getInt("id_pelicula"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getInt("anio"),
                        rs.getString("genero")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar película: " + e.getMessage());
        }
        return null;
    }

    public void actualizar(Pelicula pelicula) {
        String sql = "UPDATE peliculas SET titulo=?, director=?, anio=?, genero=? WHERE id_pelicula=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setInt(3, pelicula.getAnio());
            ps.setString(4, pelicula.getGenero());
            ps.setInt(5, pelicula.getId_pelicula());
            ps.executeUpdate();
            System.out.println("Película actualizada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar película: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM peliculas WHERE id_pelicula = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Película eliminada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar película: " + e.getMessage());
        }
    }
}