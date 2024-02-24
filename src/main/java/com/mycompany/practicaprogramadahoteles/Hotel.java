/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac                                *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;

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
    //A traves de este metodo se recorre un array de hoteles
    public static void listarHoteles(Hotel[] hoteles) {
        for (Hotel hotel : hoteles) {
            System.out.println("Hotel" + hotel.nombre);
        }
    }

    public static void listarHabitacionesDisponibles(Hotel[] hoteles) {
        //Utiliza un for each para recorrer el array hoteles
        for (Hotel hotel : hoteles) {
            //Imprime el nombre del hotel
            System.out.println("Hotel: " + hotel.nombre);
            //Este bucle recorre cada torre en el hotel actual
            for (int i = 0; i < hotel.torres.length; i++) {
                //Este bucle recorre itera cada piso de la torre
                for (int j = 0; j < hotel.torres[i].pisos.length; j++) {
                    //Esta bucle recorre cada habitacion
                    for (int k = 0; k < hotel.torres[i].pisos[j].habitaciones.length; k++) {
                        //Obtiene la referencia de la habitacion actual
                        Habitacion habitacion = hotel.torres[i].pisos[j].habitaciones[k];
                        //Verifica si la habitacion esta disponible
                        if (habitacion.disponible) {
                            System.out.println("Torre: " + (i + 1) + ", Piso: " + (j + 1)
                                    + ", Habitación: " + (k + 1) + " disponible en Hotel " + hotel.nombre);
                        }
                    }
                }
            }
        }
    }

    public static void reservarAutomaticamente(Hotel[] hoteles, Persona persona) {
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
                            return; // Salir después de realizar la reserva
                        }
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
}