package org.videoclub.dao;


import org.videoclub.conexion.Conexion;
import org.videoclub.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellidos, dni, email, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getTelefono());
            ps.executeUpdate();
            System.out.println("Cliente insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY id_cliente ASC";
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getInt("telefono")
                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getInt("telefono")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, apellidos=?, dni=?, email=?, telefono=? WHERE id_cliente=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDni());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getTelefono());
            ps.setInt(6, cliente.getId_cliente());
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM alquileres WHERE id_cliente = ?";
        String sqlDelete = "DELETE FROM clientes WHERE id_cliente = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {

            psCheck.setInt(1, id);
            ResultSet rs = psCheck.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                System.out.println("Error: No se puede eliminar el cliente porque tiene " + count + " alquiler(es) asociado(s).");
                return;
            }

            PreparedStatement psDelete = con.prepareStatement(sqlDelete);
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            System.out.println("Cliente eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

}