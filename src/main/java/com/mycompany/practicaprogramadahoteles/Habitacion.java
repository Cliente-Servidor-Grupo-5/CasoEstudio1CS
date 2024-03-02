/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac, javier, mason, gabriel        *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;


public class Habitacion {
    private int numero;
    //Se crea un atributo para saber si se encuentra disponible la habitacion
    private boolean disponible = true;
    
    public Habitacion(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
}

   

