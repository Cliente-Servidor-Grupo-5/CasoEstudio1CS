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
            habitaciones[i] = new Habitacion(i + 1); // Pasar el número de habitación
        }
    }

    public int getNumero() {
        return numero;  
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }
}

    /*    public Piso(int numHabitaciones, int numero, int inicioNumeroHabitacion) {
        this.numero = numero;
        this.inicioNumeroHabitacion = inicioNumeroHabitacion;
        this.finNumeroHabitacion = inicioNumeroHabitacion + numHabitaciones - 1;
        this.habitaciones = new Habitacion[numHabitaciones];
        for (int i = 0; i < numHabitaciones; i++) {
            habitaciones[i] = new Habitacion(inicioNumeroHabitacion + i);
        }
    }

    public int getNumero() {
        return numero;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public int getInicioNumeroHabitacion() {
        return inicioNumeroHabitacion;
    }

    public int getFinNumeroHabitacion() {
        return finNumeroHabitacion;
    }*/
