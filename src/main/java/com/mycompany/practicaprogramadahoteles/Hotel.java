/** *********************************************
 * Universidad Fidelitas                        *
 * Programacion Cliente Servidor Concurrente
 *
 *
 * @author isaac, javier, mason, gabriel * Fecha: 02/02/2024 * Nombre:
 * ClaseSemana3 *
 *********************************************** */
package com.mycompany.practicaprogramadahoteles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    //Se crean 2 atributos, nombre y una array de tipo Torre que almacena el numero de torres
    String nombre;
    private Torre[] torres;

    //Se crea un constructor, el cual tiene dentro ambos atributos y tiene tambien el numero de torres, pisos y habitaciones dentro, para que se guarden dentro del objeto cuando se cree
    public Hotel(String nombre, int numTorres, int numPisos, int numHabitaciones) {
        this.nombre = nombre;
        //Se crea un array llamado torres que tendra una longitud igual al numero especificado
        this.torres = new Torre[numTorres];
        //Este ciclo for itera atraves de cada torre
        for (int i = 0; i < numTorres; i++) {
            //En cada iteracion se crea una nueva instancia de la clase Torre y se asigna una posicion, ademas de que se inicializa el numPisos y numHabitaciones como parametro
            torres[i] = new Torre(numPisos, numHabitaciones, i + 1);
        }
    }

    public Hotel() {
    }

    public Torre[] getTorres() {
        return torres;
    }

    public static void listarHoteles(Hotel[] hoteles) {
        for (Hotel hotel : hoteles) {
            System.out.println("Hotel" + hotel.nombre);
        }
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
            for (int j = 0; j < hotel.torres[i].getPisos().length; j++) {
                // Este bucle recorre cada habitación del piso
                for (int k = 0; k < hotel.torres[i].getPisos()[j].getHabitaciones().length; k++) {
                    // Obtiene la referencia de la habitación actual
                    Habitacion habitacion = hotel.torres[i].getPisos()[j].getHabitaciones()[k];
                    // Imprime la representación visual de la habitación
                    if (habitacion.isDisponible()) {
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
            for (Torre torre : hotelSeleccionado.torres) {
                for (Piso piso : torre.getPisos()) {
                    for (Habitacion habitacion : piso.getHabitaciones()) {
                        if (habitacion.isDisponible()) {
                            habitacion.setDisponible(false);
                            persona.habitacionAsignada = habitacion;
                            JOptionPane.showMessageDialog(null, "Reserva automática realizada con éxito.");

                            // Formar el nombre del archivo de reservas para el hotel seleccionado
                            String nombreArchivo = "ReservasHotel_" + hotelSeleccionado.nombre + ".txt";

                            // Escribir en el archivo de reservas del hotel
                            try {
                                escribirEnArchivo(nombreArchivo, obtenerInformacionReserva(hotelSeleccionado, torre, piso, habitacion, persona));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de reservas: " + e.getMessage());
                            }

                            return;
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Lo siento, no hay habitaciones disponibles para la reserva automática.");
        } else {
            JOptionPane.showMessageDialog(null, "Selección de hotel cancelada.");
        }
    }

    public static void escribirEnArchivo(String nombreArchivo, String informacion) throws IOException {
        boolean archivoExiste = new File(nombreArchivo).exists();
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            // Escribe la información en bloques
            writer.write(informacion + "\n---\n");
        }
    }

    public static String obtenerInformacionReserva(Hotel hotel, Torre torre, Piso piso, Habitacion habitacion, Persona persona) {
        return String.format("Reserva realizada:\nHotel: %s\nTorre: %d\nPiso: %d\nHabitación: %d\nPersona: %s\n\n",
                hotel.nombre, torre.getNumero(), piso.getNumero(), habitacion.getNumero(), persona.getCedula());
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
        Piso piso = torre.getPisos()[numPiso - 1];
        Habitacion habitacion = piso.getHabitaciones()[numHabitacion - 1];

        if (habitacion.isDisponible()) {
            habitacion.setDisponible(false);
            persona.habitacionAsignada = habitacion;
            JOptionPane.showMessageDialog(null, "Reserva realizada con éxito.");

            // Formar el nombre del archivo de reservas para el hotel seleccionado
            String nombreArchivo = "ReservasHotel_" + hotel.nombre + ".txt";

            // Escribir en el archivo de reservas del hotel
            try {
                escribirEnArchivo(nombreArchivo, obtenerInformacionReserva(hotel, torre, piso, habitacion, persona));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de reservas: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, la habitación seleccionada no está disponible.");
        }
    }

    public static boolean esHabitacionDisponible(Hotel[] hoteles, int indiceHotel, int numeroTorre, int numeroPiso, int numeroHabitacion) {
        Hotel hotel = hoteles[indiceHotel]; // Obtiene el hotel específico según el índice proporcionado
        // Accede directamente a la habitación específica y retorna su disponibilidad
        boolean disponible = hotel.torres[numeroTorre - 1].getPisos()[numeroPiso - 1].getHabitaciones()[numeroHabitacion - 1].isDisponible();
        return disponible;
    }

    public static void eliminarReservas(Hotel[] hoteles) {
        String[] nombresHoteles = obtenerNombresHoteles(hoteles);

        String inputHotel = (String) JOptionPane.showInputDialog(null, "Seleccione un hotel para eliminar las reservas:",
                "Eliminar Reservas", JOptionPane.QUESTION_MESSAGE, null, nombresHoteles, nombresHoteles[0]);

        int numHotel = obtenerIndiceHotel(inputHotel, nombresHoteles);

        if (numHotel != -1) {
            // Formar el nombre del archivo de reservas para el hotel seleccionado
            String nombreArchivo = "ReservasHotel_" + hoteles[numHotel].nombre + ".txt";

            // Eliminar el contenido del archivo de reservas para el hotel seleccionado
            try {
                eliminarContenidoArchivo(nombreArchivo);
                JOptionPane.showMessageDialog(null, "Reservas eliminadas con éxito para el hotel: " + hoteles[numHotel].nombre);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar reservas: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selección de hotel cancelada.");
        }
    }

    private static void eliminarContenidoArchivo(String nombreArchivo) throws IOException {
        FileWriter writer = new FileWriter(nombreArchivo, false);
        writer.write(""); // Limpiar el contenido del archivo
        writer.close();
    }

    private static String[] obtenerNombresHoteles(Hotel[] hoteles) {
        String[] nombresHoteles = new String[hoteles.length];
        for (int i = 0; i < hoteles.length; i++) {
            nombresHoteles[i] = hoteles[i].nombre;
        }
        return nombresHoteles;
    }

    public static void eliminarReservasPorCedula(Hotel[] hoteles) {
        // Mostrar lista de hoteles para seleccionar
        String[] nombresHoteles = obtenerNombresHoteles(hoteles);
        String nombreHotelSeleccionado = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione un hotel:",
                "Selección de Hotel",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresHoteles,
                nombresHoteles[0]);

        int indiceHotelSeleccionado = obtenerIndiceHotelPorNombre(hoteles, nombreHotelSeleccionado);

        if (indiceHotelSeleccionado != -1) {
            Hotel hotelSeleccionado = hoteles[indiceHotelSeleccionado];
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula de la persona para eliminar sus reservas:");

            String nombreArchivo = "ReservasHotel_" + hotelSeleccionado.nombre + ".txt";
            try {
                // Leer el contenido actual del archivo
                String contenido = Archivo.leerContenido(nombreArchivo);

                // Buscar y eliminar todas las reservas asociadas a la cédula ingresada
                String[] bloques = contenido.split("---");
                StringBuilder nuevoContenido = new StringBuilder();

                for (int i = 0; i < bloques.length; i++) {
                    if (!bloques[i].contains(cedula)) {
                        // Conservar bloques no asociados a la cédula
                        nuevoContenido.append(bloques[i].trim());
                        if (i < bloques.length - 1) {
                            nuevoContenido.append("\n---\n"); // Restaurar separador
                        }
                    }
                }

                // Escribir el contenido modificado en el archivo
                Archivo.escribirContenido(nombreArchivo, nuevoContenido.toString());

                JOptionPane.showMessageDialog(null, "Reservas eliminadas con éxito para la cédula: " + cedula);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al manipular el archivo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selección de hotel cancelada.");
        }
    }

    private static int obtenerIndiceHotel(String nombreHotel, String[] nombresHoteles) {
        int indiceHotelSeleccionado = -1;
        for (int i = 0; i < nombresHoteles.length; i++) {
            if (nombresHoteles[i].equals(nombreHotel)) {
                indiceHotelSeleccionado = i;
                break;
            }
        }
        return indiceHotelSeleccionado;
    }

    private static int obtenerIndiceHotelPorNombre(Hotel[] hoteles, String nombreHotel) {
        for (int i = 0; i < hoteles.length; i++) {
            if (hoteles[i].nombre.equals(nombreHotel)) {
                return i;
            }
        }
        return -1; // No se encontró el hotel
    }

    public static void eliminarReservaManualmente(Hotel[] hoteles) {
        String[] nombresHoteles = obtenerNombresHoteles(hoteles);

        // Mostrar lista de hoteles para seleccionar
        String inputHotel = (String) JOptionPane.showInputDialog(null, "Seleccione un hotel para eliminar una reserva:",
                "Eliminar Reserva Manualmente", JOptionPane.QUESTION_MESSAGE, null, nombresHoteles, nombresHoteles[0]);

        int numHotel = obtenerIndiceHotel(inputHotel, nombresHoteles);

        if (numHotel != -1) {
            Hotel hotelSeleccionado = hoteles[numHotel];

            // Obtener la cédula de la persona para buscar la reserva
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula de la persona para eliminar su reserva:");

            // Buscar y eliminar la reserva asociada a la cédula ingresada
            boolean reservaEncontrada = false;

            for (Torre torre : hotelSeleccionado.getTorres()) {
                for (Piso piso : torre.getPisos()) {
                    for (Habitacion habitacion : piso.getHabitaciones()) {
                        if (!habitacion.isDisponible()) {
                            // Actualizar la disponibilidad de la habitación
                            habitacion.setDisponible(true);
                            // Limpiar la referencia de la persona en la habitación

                            reservaEncontrada = true;
                            break;
                        }
                    }
                    if (reservaEncontrada) {
                        break;
                    }
                }
                if (reservaEncontrada) {
                    break;
                }
            }

            if (reservaEncontrada) {
                JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito para la cédula: " + cedula);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada a la cédula: " + cedula);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selección de hotel cancelada.");
        }
    }

          public static List<String> buscarReservaPorCedulaEnArchivos(String cedula, List<String> nombresArchivos) {
        List<String> reservasEncontradas = new ArrayList<>();

        for (String nombreArchivo : nombresArchivos) {
            try {
                List<String> reservasEnArchivo = buscarEnArchivo(cedula, nombreArchivo);
                reservasEncontradas.addAll(reservasEnArchivo);
            } catch (IOException e) {
                System.err.println("Error al buscar en el archivo " + nombreArchivo + ": " + e.getMessage());
            }
        }

        return reservasEncontradas;
    }


        public static List<String> buscarEnArchivo(String cedula, String nombreArchivo) throws IOException {
        List<String> reservasEncontradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String reservaActual = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Reserva realizada") && !reservaActual.isEmpty()) {
                    // Se encontró una nueva reserva, agregamos la anterior a la lista
                    reservasEncontradas.add(reservaActual);
                    reservaActual = ""; // Limpiamos la reserva actual
                }
                reservaActual += linea + "\n"; // Concatenamos la línea a la reserva actual
            }
            // Agregamos la última reserva
            if (!reservaActual.isEmpty()) {
                reservasEncontradas.add(reservaActual);
            }
        }

        return reservasEncontradas;
    }
}

        
    




