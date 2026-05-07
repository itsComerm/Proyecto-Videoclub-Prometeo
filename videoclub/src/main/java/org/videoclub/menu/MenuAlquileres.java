package org.videoclub.menu;

import org.videoclub.dao.AlquilerDAO;
import org.videoclub.dao.ClienteDAO;
import org.videoclub.dao.StockDAO;
import org.videoclub.dao.DevolucionTardiaDAO;
import org.videoclub.modelo.Alquiler;
import org.videoclub.modelo.Cliente;
import org.videoclub.modelo.DevolucionTardia;
import org.videoclub.modelo.Stock;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuAlquileres {

    public static void mostrar(Scanner scanner) {
        AlquilerDAO alquilerDAO = new AlquilerDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        StockDAO stockDAO = new StockDAO();
        DevolucionTardiaDAO devolucionTardiaDAO = new DevolucionTardiaDAO();

        while (true) {
            System.out.println(" ");
            System.out.println("ALQUILERES");
            System.out.println("1: Listar todos");
            System.out.println("2: Listar activos");
            System.out.println("3: Nuevo alquiler");
            System.out.println("4: Registrar devolución");
            System.out.println("5: Ver devoluciones tardías");
            System.out.println("6: Volver");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        List<Alquiler> alquileres = alquilerDAO.listarTodos();
                        if (alquileres.isEmpty()) {
                            System.out.println("Error: No hay alquileres registrados.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | ID Cliente | Copia | Desde | Límite | Devolución | Precio");
                            alquileres.forEach(a -> System.out.println(
                                    a.getId_alquiler() + " | " +
                                            "ID Cliente: " + a.getId_cliente() + " | " +
                                            "ID Copia: " + a.getId_copia() + " | " +
                                            "Desde: " + a.getFecha_alquiler() + " | " +
                                            "Límite: " + a.getFecha_limite() + " | " +
                                            "Devolución: " + (a.getFecha_devolucion() != null ? a.getFecha_devolucion() : "Pendiente") + " | " +
                                            a.getPrecio() + "€"
                            ));
                        }
                        break;
                    case 2:
                        List<Alquiler> activos = alquilerDAO.listarActivos();
                        if (activos.isEmpty()) {
                            System.out.println("Atención: No hay alquileres activos.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | ID Cliente | ID Copia | Desde | Límite");
                            activos.forEach(a -> System.out.println(
                                    a.getId_alquiler() + " | " +
                                            "ID Cliente: " + a.getId_cliente() + " | " +
                                            "ID Copia: " + a.getId_copia() + " | " +
                                            "Desde: " + a.getFecha_alquiler() + " | " +
                                            "Límite: " + a.getFecha_limite()
                            ));
                        }
                        break;
                    case 3:
                        System.out.print("ID del cliente: ");
                        int idCliente = scanner.nextInt();
                        Cliente cliente = clienteDAO.buscarPorId(idCliente);
                        if (cliente == null) {
                            System.out.println("Atención: Cliente no encontrado.");
                            break;
                        }
                        System.out.print("ID de la copia: ");
                        int idCopia = scanner.nextInt();
                        Stock copia = stockDAO.buscarPorId(idCopia);
                        if (copia == null) {
                            System.out.println("Error: Copia no encontrada.");
                            break;
                        }
                        if (copia.getEstado().equals("alquilada")) {
                            System.out.println("Atención: Esta copia ya ha sido alquilada.");
                            break;
                        }
                        LocalDate hoy = LocalDate.now();
                        LocalDate limite = hoy.plusDays(7);
                        alquilerDAO.insertar(new Alquiler(0, idCliente, idCopia, hoy, limite, null, 2.00));
                        stockDAO.actualizarEstado(idCopia, "alquilada");
                        System.out.println("Alquiler registrado correctamente. Fecha límite: " + limite);
                        break;
                    case 4:
                        System.out.print("Introduce el ID del alquiler a devolver: ");
                        int idAlquiler = scanner.nextInt();
                        Alquiler alquiler = alquilerDAO.buscarPorId(idAlquiler);
                        if (alquiler == null) {
                            System.out.println("Error: Alquiler no encontrado.");
                            break;
                        }
                        if (alquiler.getFecha_devolucion() != null) {
                            System.out.println("Atención: Este alquiler ya fue devuelto.");
                            break;
                        }

                        Cliente clienteAlquiler = clienteDAO.buscarPorId(alquiler.getId_cliente());
                        LocalDate fechaDevolucion = LocalDate.now();
                        int diasRetraso = 0;
                        double importe = 0.0;

                        if (fechaDevolucion.isAfter(alquiler.getFecha_limite())) {
                            diasRetraso = (int) alquiler.getFecha_limite().until(fechaDevolucion).getDays();
                            importe = diasRetraso * 0.50;
                        }

                        System.out.println("RESUMEN DE DEVOLUCIÓN");
                        System.out.println("Cliente: " + clienteAlquiler.getNombre() + " " + clienteAlquiler.getApellidos());
                        System.out.println("Fecha alquiler: " + alquiler.getFecha_alquiler());
                        System.out.println("Fecha límite: " + alquiler.getFecha_limite());
                        System.out.println("Fecha devolución: " + fechaDevolucion);
                        System.out.println("Precio alquiler: 2.00€");
                        if (diasRetraso > 0) {
                            System.out.println("Días de retraso: " + diasRetraso);
                            System.out.println("Recargo por retraso: " + importe + "€");
                            System.out.println("TOTAL A PAGAR: " + (2.00 + importe) + "€");
                        } else {
                            System.out.println("Sin recargo por retraso.");
                            System.out.println("TOTAL A PAGAR: 2.00€");
                        }

                        System.out.print("\n¿Deseas confirmar la devolución? (Y/N): ");
                        scanner.nextLine();
                        String confirmacion = scanner.nextLine().trim().toUpperCase();

                        if (confirmacion.equals("Y")) {
                            alquilerDAO.registrarDevolucion(idAlquiler, fechaDevolucion);
                            stockDAO.actualizarEstado(alquiler.getId_copia(), "disponible");
                            if (diasRetraso > 0) {
                                devolucionTardiaDAO.insertar(new DevolucionTardia(0, idAlquiler, diasRetraso, importe, false));
                            }
                            System.out.println("Devolución confirmada correctamente.");
                        } else {
                            System.out.println("Devolución cancelada. No se han registrado cambios.");
                        }
                        break;
                    case 5:
                        List<DevolucionTardia> devoluciones = devolucionTardiaDAO.listarNoPagadas();
                        if (devoluciones.isEmpty()) {
                            System.out.println("No hay devoluciones tardías pendientes de pago.");
                        } else {
                            System.out.println(" ");
                            System.out.println("ID | ID Alquiler | Días retraso | Importe | Pagado");
                            devoluciones.forEach(d -> System.out.println(
                                    d.getId_incidencia() + " | " +
                                            "ID Alquiler: " + d.getId_alquiler() + " | " +
                                            "Días retraso: " + d.getDias_retraso() + " | " +
                                            "Importe: " + d.getPrecio() + "€ | " +
                                            "Pagado: " + (d.isPagado() ? "Sí" : "No")
                            ));
                        }
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