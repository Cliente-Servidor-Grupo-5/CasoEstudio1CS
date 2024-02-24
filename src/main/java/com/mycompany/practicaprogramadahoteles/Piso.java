/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac                                *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;


public class Piso {
    //Se crea un atributo array de habitacion
    Habitacion [] habitaciones;
    //Se crea el constructor con el parametro de numero de habitaciones y por medio de un ciclo se crea cada una de las habitaciones
    public Piso(int numHabitaciones){
        this.habitaciones = new Habitacion[numHabitaciones];
        for (int i = 0; i < numHabitaciones; i++) {
            habitaciones[i] = new Habitacion();
        }
    }
    
}
