/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac                                *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;


public class Torre {
    //Se crea un atributo array de pisos
    Piso[] pisos;
    //Se crea un constructor y por medio de un ciclo for se crean las habitaciones
    public Torre(int numPisos, int numHabitaciones){
        this.pisos = new Piso[numPisos];
        for (int i = 0; i < numPisos; i++) {
            pisos[i] = new Piso(numHabitaciones);
        }
    }
    
}
