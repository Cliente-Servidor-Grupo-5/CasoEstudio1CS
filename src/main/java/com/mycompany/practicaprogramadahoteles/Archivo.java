/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicaprogramadahoteles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author isaac
 */
public class Archivo {
    
    public static String leerContenido(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            return contenido.toString();
        }
    }

    public static void escribirContenido(String nombreArchivo, String contenido) throws IOException {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenido);
        }
    }
}
    
    

