/** *********************************************
 * Universidad Fidelitas                        *
 * Programacion Cliente Servidor Concurrente
 *
 *
 * @author isaac, javier, mason, gabriel * Fecha: 02/03/2024 * Nombre:
 * ClaseSemana3 *
 ***********************************************
 */
package com.mycompany.practicaprogramadahoteles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PracticaProgramadaHoteles {

    public static void main(String[] args) throws IOException {
        String menu;
        //Se crea una instancia de hotel
        Hotel h = new Hotel();
        //Se crea una instancia de persona
        Persona p = new Persona();
        //Se crea un areglo que va a almacenar los 4 hoteles
        Hotel[] hoteles = new Hotel[4];
        hoteles[0] = new Hotel(" Continental de New York", 2, 5, 10);
        hoteles[1] = new Hotel(" Continental de Roma ", 2, 5, 10);
        hoteles[2] = new Hotel(" Continental de Marruecos", 2, 5, 10);
        hoteles[3] = new Hotel(" Continental de Osaka Tokyo", 2, 5, 10);

        Persona persona = null; // Variable para almacenar los datos de la persona
        boolean salir = false;

        do {
            String[] opcionesMenu = {
                "Listar todos los hoteles de la cadena",
                "Listar habitaciones disponibles por hotel",
                "Registrar reservación a una persona automáticamente",
                "Registrar reservación a una persona manualmente",
                "Eliminar reservación de una persona",
                "Eliminar todas las reservaciones por hotel",
                "Buscar persona por número de cédula/pasaporte",
                "Validar disponibilidad de una habitación en un hotel",
                "Salir"
            };

            String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la opción que desea realizar:",
                    "Menú Principal",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesMenu,
                    opcionesMenu[0]
            );

            if (opcionSeleccionada != null) {
                switch (opcionSeleccionada) {
                    case "Listar todos los hoteles de la cadena":
                        // Listar todos los hoteles de la cadena
                        h.listarHoteles(hoteles);
                        break;
                    case "Listar habitaciones disponibles por hotel":
                        // Listar habitaciones disponibles por hotel
                        h.submenu1(hoteles);
                        break;
                    case "Registrar reservación a una persona automáticamente":
                        // Registrar reservación a una persona automáticamente
                        Persona personaAuto = p.ingresarDatosPersona();
                        h.reservarAutomaticamente(hoteles, personaAuto);
                        break;  
                    case "Registrar reservación a una persona manualmente":
                        // Registrar reservación a una persona manualmente
                        Persona personaManual = p.ingresarDatosPersona();
                        h.reservarManualmente(hoteles, personaManual);
                        break;
                    case "Eliminar reservación de una persona":
                        h.eliminarReservasPorCedula(hoteles);
                        h.eliminarReservaManualmente(hoteles);
                        break;
                    case "Eliminar todas las reservaciones por hotel":
                        h.eliminarReservas(hoteles);
                        break;
                    case "Buscar persona por número de cédula/pasaporte":
                        List<String> nombresArchivos = new ArrayList<>();
                        nombresArchivos.add("ReservasHotel_ Continental de Marruecos.txt");
                        nombresArchivos.add("ReservasHotel_ Continental de New York.txt");
                        nombresArchivos.add("ReservasHotel_ Continental de Osaka Tokyo.txt");
                        nombresArchivos.add("ReservasHotel_ Continental de Roma .txt");

                        String cedula = JOptionPane.showInputDialog("Ingrese la cédula que desea encontrar");

                        List<String> reservasEncontradas = h.buscarReservaPorCedulaEnArchivos(cedula, nombresArchivos);

                        if (reservasEncontradas.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron reservas para la cédula especificada.");
                        } else {
                            StringBuilder mensaje = new StringBuilder("Reservas encontradas:\n");
                            for (String reserva : reservasEncontradas) {
                                mensaje.append(reserva).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, mensaje.toString());
                        }

                        break;

                    case "Validar disponibilidad de una habitación en un hotel":
                        // Solicitar datos al usuario 
                        String inputHotel = JOptionPane.showInputDialog("Ingrese el número del hotel (1-4):");
                        int indiceHotel = Integer.parseInt(inputHotel) - 1; // Convierte a int y ajusta para índice basado en cero

                        String inputTorre = JOptionPane.showInputDialog("Ingrese el número de torre (1-2):");
                        int numeroTorre = Integer.parseInt(inputTorre);

                        String inputPiso = JOptionPane.showInputDialog("Ingrese el número de piso (1-5):");
                        int numeroPiso = Integer.parseInt(inputPiso);

                        String inputHabitacion = JOptionPane.showInputDialog("Ingrese el número de habitación (1-10):");
                        int numeroHabitacion = Integer.parseInt(inputHabitacion);

                        // Verifica si la habitación está disponible
                        boolean disponible = Hotel.esHabitacionDisponible(hoteles, indiceHotel, numeroTorre, numeroPiso, numeroHabitacion);

                        // Mostrar resultado
                        if (disponible) {
                            JOptionPane.showMessageDialog(null, "La habitación está disponible.");
                        } else {
                            JOptionPane.showMessageDialog(null, "La habitación NO está disponible.");
                        }

                        break;
                    case "Salir":

                        JOptionPane.showMessageDialog(null, "Saliendo del programa");
                        salir = true; // Establece salir como verdadero para salir del bucle
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
                }
            }

        } while (!salir);
    }
}
