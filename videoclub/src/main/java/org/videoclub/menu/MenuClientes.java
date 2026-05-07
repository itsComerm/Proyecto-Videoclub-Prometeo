package org.videoclub.menu;

import org.videoclub.dao.ClienteDAO;
import org.videoclub.modelo.Cliente;

import java.util.List;
import java.util.Scanner;

public class MenuClientes {

    public static void mostrar(Scanner scanner) {
        ClienteDAO clienteDAO = new ClienteDAO();
        while (true) {
            System.out.println(" ");
            System.out.println("CLIENTES");
            System.out.println("1: Listar clientes");
            System.out.println("2: Buscar cliente por ID");
            System.out.println("3: Registrar nuevo cliente");
            System.out.println("4: Modificar cliente");
            System.out.println("5: Eliminar cliente");
            System.out.println("6: Volver");
            System.out.print("Elige una opción: ");


            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        List<Cliente> clientes = clienteDAO.listarTodos();
                        if (clientes.isEmpty()) {
                            System.out.println("Atención: No hay ningún cliente registrado.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | Nombre | DNI | Teléfono | Email");
                            clientes.forEach(c -> System.out.println(
                                    c.getId_cliente() + " | " +
                                    c.getNombre() + " " +
                                    c.getApellidos() + " | " +
                                    c.getDni() + " | " +
                                    c.getTelefono() + " | " +
                                    c.getEmail()
                            ));
                        }
                        break;
                    case 2:
                        System.out.println(" ");
                        System.out.print("Introduce el ID del cliente que quieres buscar: ");
                        int id = scanner.nextInt();
                        Cliente c = clienteDAO.buscarPorId(id);
                        if (c == null) {
                            System.out.println("Atención: No existe ningún cliente asociado a ese ID.");
                        } else {
                            System.out.println(
                                    c.getId_cliente() + " | " +
                                    c.getNombre() + " " +
                                    c.getApellidos() + " | " +
                                    c.getDni() + " | " +
                                    c.getTelefono() + " | " +
                                    c.getEmail()
                            );
                        }
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidos = scanner.nextLine();
                        System.out.print("DNI: ");
                        String dni = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        int telefono = scanner.nextInt();
                        clienteDAO.insertar(new Cliente(0, nombre, apellidos, dni, email, telefono));
                        break;
                    case 4:
                        System.out.print("Introduce el ID del cliente a modificar: ");
                        int idMod = scanner.nextInt();
                        Cliente cMod = clienteDAO.buscarPorId(idMod);
                        if (cMod == null) {
                            System.out.println("Cliente no encontrado.");
                        } else {
                            scanner.nextLine();
                            System.out.print("Nuevo nombre (" + cMod.getNombre() + "): ");
                            String nuevoNombre = scanner.nextLine();
                            System.out.print("Nuevos apellidos (" + cMod.getApellidos() + "): ");
                            String nuevosApellidos = scanner.nextLine();
                            System.out.print("Nuevo DNI (" + cMod.getDni() + "): ");
                            String nuevoDni = scanner.nextLine();
                            System.out.print("Nuevo email (" + cMod.getEmail() + "): ");
                            String nuevoEmail = scanner.nextLine();
                            System.out.print("Nuevo teléfono (" + cMod.getTelefono() + "): ");
                            int nuevoTelefono = scanner.nextInt();
                            clienteDAO.actualizar(new Cliente(idMod, nuevoNombre, nuevosApellidos, nuevoDni, nuevoEmail, nuevoTelefono));
                        }
                        break;
                    case 5:
                        System.out.print("Introduce el ID del cliente a eliminar: ");
                        int idElim = scanner.nextInt();
                        clienteDAO.eliminar(idElim);
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