package org.videoclub.menu;

import org.videoclub.dao.StockDAO;
import org.videoclub.dao.PeliculaDAO;
import org.videoclub.modelo.Stock;
import org.videoclub.modelo.Pelicula;

import java.util.List;
import java.util.Scanner;

public class MenuStock {

    public static void mostrar(Scanner scanner) {
        StockDAO stockDAO = new StockDAO();
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        while (true) {
            System.out.println(" ");
            System.out.println("STOCK");
            System.out.println("1: Listar todas las copias");
            System.out.println("2: Buscar copia por ID");
            System.out.println("3: Añadir copia");
            System.out.println("4: Cambiar estado de copia");
            System.out.println("5: Eliminar copia");
            System.out.println("6: Volver");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        List<Stock> copias = stockDAO.listarTodos();
                        if (copias.isEmpty()) {
                            System.out.println("No hay copias registradas.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | Película | Estado");
                            copias.forEach(s -> {
                                Pelicula p = peliculaDAO.buscarPorId(s.getId_pelicula());
                                String titulo = p != null ? p.getTitulo() : "Desconocida";
                                System.out.println(
                                        s.getId_articulo() + " | " +
                                        titulo + " | " +
                                        s.getEstado()
                                );
                            });
                        }
                        break;
                    case 2:
                        System.out.print("Introduce el ID de la copia: ");
                        int id = scanner.nextInt();
                        Stock s = stockDAO.buscarPorId(id);
                        if (s == null) {
                            System.out.println("Copia no encontrada.");
                        } else {
                            Pelicula p = peliculaDAO.buscarPorId(s.getId_pelicula());
                            String titulo = p != null ? p.getTitulo() : "Desconocida";
                            System.out.println(
                                    s.getId_articulo() + " | " +
                                            titulo + " | " +
                                            s.getEstado()
                            );
                        }
                        break;
                    case 3:
                        System.out.print("Introduce el ID de la película: ");
                        int idPelicula = scanner.nextInt();
                        Pelicula pelicula = peliculaDAO.buscarPorId(idPelicula);
                        if (pelicula == null) {
                            System.out.println("Película no encontrada.");
                        } else {
                            stockDAO.insertar(new Stock(0, idPelicula, "disponible"));
                            System.out.println("Copia añadida correctamente.");
                        }
                        break;
                    case 4:
                        System.out.print("Introduce el ID de la copia: ");
                        int idEstado = scanner.nextInt();
                        Stock sMod = stockDAO.buscarPorId(idEstado);
                        if (sMod == null) {
                            System.out.println("Copia no encontrada.");
                        } else {
                            System.out.println("Estado actual: " + sMod.getEstado());
                            System.out.println("1. disponible");
                            System.out.println("2. alquilada");
                            System.out.print("Nuevo estado: ");
                            int estadoOpcion = scanner.nextInt();
                            String nuevoEstado = estadoOpcion == 1 ? "disponible" : "alquilada";
                            stockDAO.actualizarEstado(idEstado, nuevoEstado);
                        }
                        break;
                    case 5:
                        System.out.print("Introduce el ID de la copia a eliminar: ");
                        int idElim = scanner.nextInt();
                        stockDAO.eliminar(idElim);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Error: Por favor, introduce un número entre 1 y 6.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("ERROR: Introduce un número válido.");
                scanner.nextLine();
            }
        }
    }
}