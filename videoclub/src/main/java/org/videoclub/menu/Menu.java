package org.videoclub.menu;

import java.util.Scanner;

public class Menu {
    public static void mostrar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" ");
            System.out.println("==================");
            System.out.println("VIDEOCLUB PROMETEO");
            System.out.println("==================");
            System.out.println("1: Clientes");
            System.out.println("2: Películas");
            System.out.println("3: Stock");
            System.out.println("4: Alquileres");
            System.out.println("5: Informes");
            System.out.println("6: Salir");
            System.out.print("Elige una opción: ");

        try {
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    MenuClientes.mostrar(scanner);
                    break;
                case 2:
                    MenuPeliculas.mostrar(scanner);
                    break;
                case 3:
                    MenuStock.mostrar(scanner);
                    break;
                case 4:
                    MenuAlquileres.mostrar(scanner);
                     break;
                case 5:
                    MenuInformes.mostrar(scanner);
                    break;
                case 6:
                    System.out.println("Hasta pronto.");
                    return;
                default:
                    System.out.println("Error: Por favor, introduce un número entre 1 y 6.");
                }
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("ERROR: Debes introducir un número válido.");
            scanner.nextLine();
        }

        }

    }

}
