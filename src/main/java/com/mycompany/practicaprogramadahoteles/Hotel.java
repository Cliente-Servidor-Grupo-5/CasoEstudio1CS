/** *********************************************
 * Universidad Fidelitas                        *
 * Programacion Cliente Servidor Concurrente
 *
 *
 * @author isaac, javier, mason, gabriel * Fecha: 02/02/2024 * Nombre:
 * ClaseSemana3 *
 ***********************************************
 */
package com.mycompany.practicaprogramadahoteles;

import java.io.File;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class Hotel {

    //Se crean 2 atributos, nombre y una array de tipo Torre que almacena el numero de torres
    String nombre;
    Torre[] torres;

    //Se crea un constructor, el cual tiene dentro ambos atributos y tiene tambien el numero de torres, pisos y habitaciones dentro, para que se guarden dentro del objeto cuando se cree
    public Hotel(String nombre, int numTorres, int numPisos, int numHabitaciones) {
        this.nombre = nombre;
        //Se crea un array llamado torres que tendra una longitud igual al numero especificado
        this.torres = new Torre[numTorres];
        //Este ciclo for itera atraves de cada torre
        for (int i = 0; i < numTorres; i++) {
            //En cada iteracion se crea una nueva instancia de la clase Torre y se asigna una posicion, ademas de que se inicializa el numPisos y numHabitaciones como parametro
            torres[i] = new Torre(numPisos, numHabitaciones);
        }
    }

    public Hotel() {
    }

    public static void listarHoteles(Hotel[] hoteles) {
        JOptionPane.showMessageDialog(null, "Hotel Continental de New York\n" +
                "Hotel Continental de Roma\n"+
                "Hotel Continental de Marruecos\n"+
                "Hotel Continental de Osaka Tokyo\n");
    }

    public static void submenu1(Hotel[] hoteles) {
        String menu = """
        1. Listar Hotel Continental de New York
        2. Listar Hotel Continental de Roma
        3. Listar Hotel Continental de Marruecos
        4. Listar Hotel Continental de Osaka Tokyo
        5. Listar todos los hoteles
        6. Salir""";

        int opcion;
        do {
            String input = (String) JOptionPane.showInputDialog(null,
                    "Seleccione una opción:",
                    "Menú",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    menu.split("\n"),
                    "1");

            opcion = Integer.parseInt(input.split("\\.")[0]);

            switch (opcion) {
                case 1:
                    listarHabitacionesDisponibles(hoteles, 0);
                    break;
                case 2:
                    listarHabitacionesDisponibles(hoteles, 1);
                    break;
                case 3:
                    listarHabitacionesDisponibles(hoteles, 2);
                    break;
                case 4:
                    listarHabitacionesDisponibles(hoteles, 3);
                    break;
                case 5:
                    listarHabitacionesDisponiblesTodos(hoteles);
                    break;
                case 6:
                    //salir  
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 6);
    }

    public static void listarHabitacionesDisponiblesTodos(Hotel[] hoteles) {
        System.out.println("-----------------------------Listado de Habitaciones de todos los Hoteles-----------------------------\n");
        // Utiliza un foreach para recorrer el array de hoteles y llama a la función listarHabitacionesDisponibles para cada hotel
        for (int i = 0; i < hoteles.length; i++) {
            listarHabitacionesDisponibles(hoteles, i);
        }
    }

    public static void listarHabitacionesDisponibles(Hotel[] hoteles, int indiceHotel) {
        Hotel hotel = hoteles[indiceHotel]; // Obtiene el hotel específico según el índice proporcionado
        // Imprime el nombre del hotel
        System.out.println("Hotel: " + hotel.nombre);
        // Este bucle recorre cada torre en el hotel actual
        for (int i = 0; i < hotel.torres.length; i++) {
            // Imprime el número de torre
            System.out.println("Torre " + (i + 1) + ":");
            // Este bucle recorre cada piso de la torre
            for (int j = 0; j < hotel.torres[i].pisos.length; j++) {
                // Este bucle recorre cada habitación del piso
                for (int k = 0; k < hotel.torres[i].pisos[j].habitaciones.length; k++) {
                    // Obtiene la referencia de la habitación actual
                    Habitacion habitacion = hotel.torres[i].pisos[j].habitaciones[k];
                    // Imprime la representación visual de la habitación
                    if (habitacion.disponible) {
                        System.out.print("[ ]"); // Habitación disponible
                    } else {
                        System.out.print("[X]"); // Habitación ocupada
                    }
                }
                // Nueva línea después de imprimir todas las habitaciones de un piso
                System.out.println();
            }
            // Nueva línea después de imprimir todas las habitaciones de una torre
            System.out.println();
        }
    }

    public static void reservarAutomaticamente(Hotel[] hoteles, Persona persona) {

        String[] nombresHoteles = {
            "Hotel Continental de New York",
            "Hotel Continental de Roma",
            "Hotel Continental de Marruecos",
            "Hotel Continental de Osaka Tokyo"
        };

        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona un hotel:",
                "Selección de Hotel",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresHoteles,
                nombresHoteles[0]);

        int indiceHotelSeleccionado = -1;
        for (int i = 0; i < nombresHoteles.length; i++) {
            if (nombresHoteles[i].equals(opcionSeleccionada)) {
                indiceHotelSeleccionado = i;
                break;
            }
        }

        if (indiceHotelSeleccionado != -1) {
            Hotel hotelSeleccionado = hoteles[indiceHotelSeleccionado];
            // Iterar sobre las habitaciones del hotel seleccionado
            for (Torre torre : hotelSeleccionado.torres) {
                for (Piso piso : torre.pisos) {
                    for (Habitacion habitacion : piso.habitaciones) {
                        // Verificar si la habitación está disponible
                        if (habitacion.disponible) {
                            habitacion.disponible = false; // Marcar la habitación como ocupada
                            persona.habitacionAsignada = habitacion; // Asignar la habitación a la persona
                            JOptionPane.showMessageDialog(null, "Reserva automática realizada con éxito.");

                            return; // Salir después de realizar la reserva
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Lo siento, no hay habitaciones disponibles para la reserva automática.");
        } else {
            JOptionPane.showMessageDialog(null, "Selección de hotel cancelada.");
        }
    }

    public static void mostrarMenuReserva(Hotel[] hoteles, Persona persona) {
        String[] opcionesMenu = {"Reservar manualmente", "Salir"};
        int opcionSeleccionada = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Menú de Reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcionesMenu,
                opcionesMenu[0]);

        switch (opcionSeleccionada) {
            case 0:
                reservarManualmente(hoteles, persona);
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }

    public static void reservarManualmente(Hotel[] hoteles, Persona persona) {
        String[] nombresHoteles = {
            "Hotel Continental de New York",
            "Hotel Continental de Roma",
            "Hotel Continental de Marruecos",
            "Hotel Continental de Osaka Tokyo"
        };

        String inputHotel = (String) JOptionPane.showInputDialog(null, "Seleccione un hotel:",
                "Selección de Hotel", JOptionPane.QUESTION_MESSAGE, null, nombresHoteles, nombresHoteles[0]);
        int numHotel = 0;
        for (int i = 0; i < nombresHoteles.length; i++) {
            if (inputHotel.equals(nombresHoteles[i])) {
                numHotel = i + 1;
                break;
            }
        }

        int numTorre;
        do {
            String inputTorre = JOptionPane.showInputDialog("Ingrese el número de torre (1 o 2):");
            numTorre = Integer.parseInt(inputTorre);

            if (numTorre != 1 && numTorre != 2) {
                JOptionPane.showMessageDialog(null, "El número de torre debe ser 1 o 2, intente de nuevo.");
            }
        } while (numTorre != 1 && numTorre != 2);

        int numPiso;
        do {
            String inputPiso = JOptionPane.showInputDialog("Ingrese el número de piso (1-5):");
            numPiso = Integer.parseInt(inputPiso);

            if (numPiso < 1 || numPiso > 5) {
                JOptionPane.showMessageDialog(null, "El número de piso debe estar entre 1 y 5. Por favor, intente de nuevo.");
            }
        } while (numPiso < 1 || numPiso > 5);

        int numHabitacion;
        do {
            String inputHabitacion = JOptionPane.showInputDialog("Ingrese el número de habitación (1-10):");
            numHabitacion = Integer.parseInt(inputHabitacion);

            if (numHabitacion < 1 || numHabitacion > 10) {
                JOptionPane.showMessageDialog(null, "El número de habitación debe estar entre 1 y 10. Por favor, intente de nuevo.");
            }
        } while (numHabitacion < 1 || numHabitacion > 10);

        Hotel hotel = hoteles[numHotel - 1];
        Torre torre = hotel.torres[numTorre - 1];
        Piso piso = torre.pisos[numPiso - 1];
        Habitacion habitacion = piso.habitaciones[numHabitacion - 1];

        //Valida la disponibilidad
        if (habitacion.disponible) {
            habitacion.disponible = false;
            persona.habitacionAsignada = habitacion;
            JOptionPane.showMessageDialog(null, "Reserva realizada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, la habitación seleccionada no está disponible.");
        }
    }

    public static boolean esHabitacionDisponible(Hotel[] hoteles, int indiceHotel, int numeroTorre, int numeroPiso, int numeroHabitacion) {
        Hotel hotel = hoteles[indiceHotel]; // Obtiene el hotel específico según el índice proporcionado
        // Accede directamente a la habitación específica y retorna su disponibilidad
        boolean disponible = hotel.torres[numeroTorre - 1].pisos[numeroPiso - 1].habitaciones[numeroHabitacion - 1].disponible;
        return disponible;
    }

}
