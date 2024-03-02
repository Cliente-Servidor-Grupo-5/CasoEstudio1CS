/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac, javier, mason, gabriel        *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;


public class Torre {
    
    private int numero;
    private Piso[] pisos;

    public Torre(int numPisos, int numHabitaciones, int numero) {
        this.numero = numero;
        this.pisos = new Piso[numPisos];
        for (int i = 0; i < numPisos; i++) {
            pisos[i] = new Piso(numHabitaciones, i+1);
        }
    }

    public int getNumero() {
        return numero;  
    }

    public Piso[] getPisos() {
        return pisos;
    }
    
}
