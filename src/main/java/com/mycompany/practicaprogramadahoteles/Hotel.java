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
            torres[i] = new Torre( numPisos, numHabitaciones, i+1);
        }
    }

    public Hotel() {
    }

    //A traves de este metodo se recorre un array de hoteles
    public static void listarHoteles(Hotel[] hoteles) {
        for (Hotel hotel : hoteles) {
            System.out.println("Hotel" + hotel.nombre);
        }
    }

    public static void submenu1(Hotel[] hoteles) {
        String menu;
        int opcion;
        do {
            // Se imprimen las diferentes opciones a elegir
            menu = """
                1. Listar Hotel Continental de New York
                2. Listar Hotel Continental de Roma
                3. Listar Hotel Continental de Marruecos
                4. Listar Hotel Continental de Osaka Tokyo
                5. Listar todos los hoteles
                6. Salir""";
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Digite la opción que desea realizar: \n" + menu));
            // Según la opción elegida, se llama a la función correspondiente y se pasa como parametro el indice del hotel respectivo 
            switch (opcion) {
                case 1:
                    listarHabitacionesDisponibles(hoteles, 0); // Índice del hotel Continental de New York en el array hoteles
                    break;
                case 2:
                    listarHabitacionesDisponibles(hoteles, 1); // Índice del hotel Continental de Roma en el array hoteles
                    break;
                case 3:
                    listarHabitacionesDisponibles(hoteles, 2); // Índice del hotel Continental de Marruecos en el array hoteles
                    break;
                case 4:
                    listarHabitacionesDisponibles(hoteles, 3); // Índice del hotel Continental de Osaka Tokyo en el array hoteles
                    break;
                case 5:
                    listarHabitacionesDisponiblesTodos(hoteles); // Llama a la función para listar todos los hoteles
                    break;
                case 6:
                    // Opción para salir del submenu
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
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

    public static void reservarAutomaticamente(Hotel[] hoteles, Persona persona) throws IOException {
        //Este bucle itera sobre cada hotel
        for (Hotel hotel : hoteles) {
            //Este sobre cada torre
            for (Torre torre : hotel.torres) {
                //Este sobre cada piso
                for (Piso piso : torre.pisos) {
                    //Este itera sobre cada habtiacion de ese piso
                    for (Habitacion habitacion : piso.habitaciones) {
                        //Verifica si la habitacion esta disponible
                        if (habitacion.disponible) {
                            habitacion.disponible = false;//Marca la habitacion como ocupada
                            persona.habitacionAsignada = habitacion;//Asigna el campo de habitacion a la persona
                            System.out.println("Reserva automática realizada con éxito.");

                            String nombreArchivo = "Reservas Hotel" + hotel.nombre + ".txt";
                            
                            boolean archivoExiste = new File(nombreArchivo).exists();
                            try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
                            // Escribe la información de la reserva en el archivo
                            writer.write("Reserva automática realizada con éxito:\n");
                            writer.write("  Hotel: \"" + hotel.nombre + "\"\n");
                            writer.write("  Torre: \"" + torre.getNumero() + "\"\n");
                            writer.write("  Piso: \"" + piso.getNumero() + "\"\n");
                            writer.write("  Habitación: \"" + habitacion.getNumero() + "\"\n");
                            writer.write("  Persona: \"" + persona.getNombre()+ "\"\n\n");

                            System.out.println("Reserva automática realizada con éxito.");
                        } catch (IOException e) {
                            System.out.println("Error al escribir en el archivo: " + e.getMessage());
                        }


                        }

                        return; // Salir después de realizar la reserva

                    }
                }
            }
        }
        System.out.println("Lo siento, no hay habitaciones disponibles para la reserva automática.");
    }

    public static void reservarManualmente(Hotel[] hoteles, Persona persona, int numHotel, int numTorre, int numPiso, int numHabitacion) {
        Hotel hotel = hoteles[numHotel - 1];  //Obtiene el hotel deseado
        Torre torre = hotel.torres[numTorre - 1]; //Obtiene la torre deseada
        Piso piso = torre.pisos[numPiso - 1];   //Obtiene el piso deseado
        Habitacion habitacion = piso.habitaciones[numHabitacion - 1];   //Obtiene la habitacion deseada
        //Valida la disponibilidad
        if (habitacion.disponible) {
            habitacion.disponible = false;
            persona.habitacionAsignada = habitacion;
            System.out.println("Reserva realizada con éxito.");
        } else {
            System.out.println("Lo siento, la habitación seleccionada no está disponible.");
        }

    }

    public static boolean esHabitacionDisponible(Hotel[] hoteles, int indiceHotel, int numeroTorre, int numeroPiso, int numeroHabitacion) {
        Hotel hotel = hoteles[indiceHotel]; // Obtiene el hotel específico según el índice proporcionado
        // Accede directamente a la habitación específica y retorna su disponibilidad
        boolean disponible = hotel.torres[numeroTorre - 1].pisos[numeroPiso - 1].habitaciones[numeroHabitacion - 1].disponible;
        return disponible;
    }

}
