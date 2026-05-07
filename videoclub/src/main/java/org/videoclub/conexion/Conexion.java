package org.videoclub.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/videoclub";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }

        return conexion;
    }
}