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

public class Piso {
    private int numero;
    private Habitacion[] habitaciones;

    public Piso(int numHabitaciones, int numero) {
        this.numero = numero;
        this.habitaciones = new Habitacion[numHabitaciones];
        for (int i = 0; i < numHabitaciones; i++) {
            habitaciones[i] = new Habitacion();
        }
    }

    public int getNumero() {
        return numero;  
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }
    
    
}
