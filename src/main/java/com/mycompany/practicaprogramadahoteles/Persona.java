/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac                                *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;

import javax.swing.JOptionPane;


public class Persona {
    //Se crean los atributos
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int cedula;
    private int nacionalidad;
    private int telefono;
    private String correo;
    Habitacion habitacionAsignada;
    //Se crea un constructor
    public Persona(String nombre, String apellido1, String apellido2, int cedula, int nacionalidad, int telefono, String correo) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.correo = correo;
        
    }

    //Se crea un constructor vacio para las instancias
    public Persona() {
    }
    
    //Se crea un metodo para solicitar los datos de la persona
     public Persona ingresarDatosPersona() {
        JOptionPane.showMessageDialog(null,"Ingrese los datos de la persona:","DATOS",JOptionPane.INFORMATION_MESSAGE);
        String nombre = JOptionPane.showInputDialog("Inserte el nombre de la persona:");
        String nacionalidad = JOptionPane.showInputDialog("Inserte la nacionalidad de la persona:");
        String apellidos = JOptionPane.showInputDialog("Inserte el primer y segundo de la persona:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo de la persona: ");
        try{
        int cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula de la persona: "));
        int telefono = Integer.parseInt(JOptionPane.showInputDialog("Digite el numero de telefono de la persona: "));
        }catch(NumberFormatException e){
        }finally{
            JOptionPane.showMessageDialog(null, "Datos ingresados erroneamente!\n\nVolviendo al menu principal", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
        return new Persona(nombre, apellidos, apellidos, 0, 0, 0, correo);
    }

    
    
    
}
