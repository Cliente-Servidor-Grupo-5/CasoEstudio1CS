/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac, javier, mason, gabriel        *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/
package com.mycompany.practicaprogramadahoteles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Persona {
    //Se crean los atributos
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int cedula;
    private String nacionalidad;
    private int telefono;
    private String correo;
    Habitacion habitacionAsignada;

    //Se crea un constructor
    public Persona(String nombre, String apellido1, String apellido2, int cedula, String nacionalidad, int telefono,
            String correo, Habitacion habitacionAsignada) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.correo = correo;
        this.habitacionAsignada = habitacionAsignada;
    }

    //Se crea un constructor vacio para las instancias
    public Persona() {
    }
    
        public String getNombre() {
        return nombre;
    }
        public int getCedula() {
        return cedula;
    }
    //Se crea un metodo para solicitar los datos de la persona
    public Persona ingresarDatosPersona() {
        // Validación de la cédula
        boolean cedulaValida = false;
        while (!cedulaValida) {
            String inputCedula = JOptionPane.showInputDialog("Digite la cédula de la persona: ");
            try {
                this.cedula = Integer.parseInt(inputCedula);
                if (!existePersona(this.cedula)) {
                    cedulaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una persona con la misma cédula. Por favor, ingrese una cédula diferente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cédula.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Se solicita la información de la persona mediante JOptionPane
        this.nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona:");
        this.apellido1 = JOptionPane.showInputDialog("Ingrese el primer apellido de la persona:");
        this.apellido2 = JOptionPane.showInputDialog("Ingrese el segundo apellido de la persona:");
        this.nacionalidad = JOptionPane.showInputDialog("Digite la nacionalidad de la persona:");
        this.correo = JOptionPane.showInputDialog("Ingrese el correo electrónico de la persona:");
        
        
        // Validación del teléfono
        boolean telefonoValido = false;
        while (!telefonoValido) {
            String inputTelefono = JOptionPane.showInputDialog("Digite el número de teléfono de la persona: ");
            try {
                this.telefono = Integer.parseInt(inputTelefono);
                telefonoValido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el teléfono.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Se guarda la información de la persona en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("personas.txt", true))) {
            // Formato de los datos en el archivo de texto
            writer.write(String.format("Nombre: %s\nApellido 1: %s\nApellido 2: %s\nCédula: %d\nNacionalidad: %s\nTeléfono: %d\nCorreo: %s\n",
                    this.nombre, this.apellido1, this.apellido2, this.cedula, this.nacionalidad, this.telefono, this.correo));
            writer.write("-----------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Los datos de la persona se han guardado correctamente.");
        return new Persona(nombre, apellido1, apellido2, cedula, nacionalidad, telefono, correo, habitacionAsignada);
    }
    
    // Método para verificar si ya existe una persona con la misma cédula en personas.txt
    private boolean existePersona(int cedula) {
        try {
            // Leemos el contenido del archivo personas.txt
            String personas = new String(Files.readAllBytes(Paths.get("personas.txt")));
            // Verificamos si el contenido contiene la cédula
            return personas.contains("Cédula: " + cedula);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

        // Método para asignar el número de habitación después de la reserva
    public void asignarNumeroHabitacion(int numeroHabitacion) {
        try {
            // Nombre del archivo de personas
            String nombreArchivo = "personas.txt";
            
            // Leer el archivo y buscar la entrada correspondiente a esta persona
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder contenido = new StringBuilder();
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null) {
                // Si la línea contiene la cédula de esta persona
                if (linea.contains("Cédula: " + this.cedula)) {
                    // Agregar la línea con la habitación asignada actualizada
                    contenido.append(linea).append("\n");
                    contenido.append("Habitación Asignada: ").append(numeroHabitacion).append("\n");
                    encontrado = true;
                } else {
                    contenido.append(linea).append("\n");
                }
            }
            br.close();
            
            if (!encontrado) {
                // Si no se encontró la persona en el archivo, lanzar una excepción o manejar el caso según sea necesario
                throw new IOException("No se encontró la persona en el archivo.");
            }
            
            // Escribir el contenido actualizado de vuelta al archivo
            FileWriter fw = new FileWriter(nombreArchivo);
            fw.write(contenido.toString());
            fw.close();
            
            // Actualizar el atributo habitacionAsignada
            this.habitacionAsignada = new Habitacion(numeroHabitacion);
            
            System.out.println("Número de habitación asignado correctamente.");
        } catch (IOException e) {
            // Manejar cualquier excepción de IO
            System.err.println("Error al asignar el número de habitación: " + e.getMessage());
        }
    }
    
}
