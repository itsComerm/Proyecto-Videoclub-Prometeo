package org.videoclub.menu;

import org.videoclub.dao.PeliculaDAO;
import org.videoclub.modelo.Pelicula;

import java.util.List;
import java.util.Scanner;

public class MenuPeliculas {

    public static void mostrar(Scanner scanner) {
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        while (true) {
            System.out.println(" ");
            System.out.println("PELÍCULAS");
            System.out.println("1: Listar todas");
            System.out.println("2: Buscar por ID");
            System.out.println("3: Añadir película");
            System.out.println("4: Modificar película");
            System.out.println("5: Eliminar película");
            System.out.println("6: Volver");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        List<Pelicula> peliculas = peliculaDAO.listarTodos();
                        if (peliculas.isEmpty()) {
                            System.out.println("No hay películas registradas.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | Título | Director | Año | Género");
                            peliculas.forEach(p -> System.out.println(
                                    p.getId_pelicula() + " | " +
                                    p.getTitulo() + " | " +
                                    p.getDirector() + " | " +
                                    p.getAnio() + " | " +
                                    p.getGenero()
                            ));
                        }
                        break;
                    case 2:
                        System.out.print("Introduce el ID de la película: ");
                        int id = scanner.nextInt();
                        Pelicula p = peliculaDAO.buscarPorId(id);
                        if (p == null) {
                            System.out.println("Película no encontrada.");
                        } else {
                            System.out.println(
                                    p.getId_pelicula() + " | " +
                                            p.getTitulo() + " | " +
                                            p.getDirector() + " | " +
                                            p.getAnio() + " | " +
                                            p.getGenero()
                            );
                        }
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Director: ");
                        String director = scanner.nextLine();
                        System.out.print("Año: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Género: ");
                        String genero = scanner.nextLine();
                        peliculaDAO.insertar(new Pelicula(0, titulo, director, anio, genero));
                        break;
                    case 4:
                        System.out.print("Introduce el ID de la película a modificar: ");
                        int idMod = scanner.nextInt();
                        Pelicula pMod = peliculaDAO.buscarPorId(idMod);
                        if (pMod == null) {
                            System.out.println("Esta película no existe.");
                        } else {
                            scanner.nextLine();
                            System.out.print("Nuevo título (" + pMod.getTitulo() + "): ");
                            String nuevoTitulo = scanner.nextLine();
                            System.out.print("Nuevo director (" + pMod.getDirector() + "): ");
                            String nuevoDirector = scanner.nextLine();
                            System.out.print("Nuevo año (" + pMod.getAnio() + "): ");
                            int nuevoAnio = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nuevo género (" + pMod.getGenero() + "): ");
                            String nuevoGenero = scanner.nextLine();
                            peliculaDAO.actualizar(new Pelicula(idMod, nuevoTitulo, nuevoDirector, nuevoAnio, nuevoGenero));
                        }
                        break;
                    case 5:
                        System.out.print("Introduce el ID de la película a eliminar: ");
                        int idElim = scanner.nextInt();
                        peliculaDAO.eliminar(idElim);
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