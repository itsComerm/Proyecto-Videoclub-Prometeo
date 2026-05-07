package org.videoclub.menu;

import org.videoclub.dao.InformesDAO;

import java.util.Scanner;

public class MenuInformes {
    public static void mostrar(Scanner scanner) {
            while (true) {
                System.out.println(" ");
                System.out.println("INFORMES");
                System.out.println("1: Películas más alquiladas");
                System.out.println("2: Clientes con más alquileres");
                System.out.println("3: Devoluciones tardías pendientes");
                System.out.println("4: Peliculas sin stock");
                System.out.println("5: Volver");
                System.out.print("Elige una opción: ");
        try {
            int opción = scanner.nextInt();
            switch (opción) {
                case 1:
                    System.out.println(" ");
                    InformesDAO.peliculasMasAlquiladas();
                    break;
                case 2:
                    System.out.println(" ");
                    InformesDAO.clientesConMasAlquileres();
                    break;
                case 3:
                    System.out.println(" ");
                    InformesDAO.devolucionesTardiasPendientes();
                    break;
                case 4:
                    System.out.println(" ");
                    InformesDAO.peliculasSinStock();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Error: Por favor, introduce un número entre 1 y 5.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: Introduce un número válido.");
            scanner.nextLine();
        }
    }
}
}
