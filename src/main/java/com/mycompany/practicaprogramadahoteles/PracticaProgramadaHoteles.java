/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac, javier, mason, gabriel        *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/

package com.mycompany.practicaprogramadahoteles;

import javax.swing.JOptionPane;

public class PracticaProgramadaHoteles {

    public static void main(String[] args) {
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
        //Se utiliza un do while para el menu principal
        int opcion;
        do {
            //Se imprimen las diferentes opciones a elegir
            menu = """
                   1. Listar todos los hoteles de la cadena
                   2. Listar habitaciones disponibles por hotel
                   3. Registrar reservación a una persona automáticamente
                   4. Registrar reservación a una persona manualmente
                   5. Eliminar reservación de una persona
                   6. Eliminar todas las reservaciones por hotel
                   7. Buscar persona por número de cédula/pasaporte
                   8. Validar disponibilidad de una habitación en un hotel
                   9. Salir""";

            opcion = Integer.parseInt(JOptionPane.showInputDialog("Digite la opcion que desea realizar: \n" + menu));
            //Dentro del switch se llama a los metodos necesarios para realizar cada una de las operaciones
            switch (opcion) {
                case 1:
                    h.listarHoteles(hoteles);
                    break;
                case 2:
                    h.submenu1(hoteles);
                    break;
                case 3:
                    //Se verifica que si no hay datos de Persona, se les solicita llamando al metodo correspondiente
                    persona = p.ingresarDatosPersona();
                    h.reservarAutomaticamente(hoteles, persona);
                    break;
                case 4:
                    //Se verifica que si no hay datos de Persona, se les solicita llamando al metodo correspondiente
                    if (persona != null) {
                        p.ingresarDatosPersona();
                    }
                    //Se utiliza un Try Catch por si hay un error ingresando los datos solicitados
                    try {
                        int numHotel = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de hotel: "));
                        int numTorre = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de torre: "));
                        int numPiso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de piso: "));
                        int numHabitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de habitacion: "));
                        Hotel.reservarManualmente(hoteles, persona, numHotel, numTorre, numPiso, numHabitacion);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Datos ingresados erroneamente!\n\nVolviendo al menu principal", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                    }
                    break;
                case 8:
                    // Solicitar datos al usuario 
                    String inputHotel = JOptionPane.showInputDialog("Ingrese el número del hotel:");
                    int indiceHotel = Integer.parseInt(inputHotel) - 1; // Convierte a int y ajusta para índice basado en cero

                    String inputTorre = JOptionPane.showInputDialog("Ingrese el número de torre:");
                    int numeroTorre = Integer.parseInt(inputTorre);

                    String inputPiso = JOptionPane.showInputDialog("Ingrese el número de piso:");
                    int numeroPiso = Integer.parseInt(inputPiso);

                    String inputHabitacion = JOptionPane.showInputDialog("Ingrese el número de habitación:");
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
                case 9:
                    System.out.println("Saliendo del programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            //Se sale del ciclo do while
        } while (opcion != 9);
    }
}