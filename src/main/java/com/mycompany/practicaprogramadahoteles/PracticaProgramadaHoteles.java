/***********************************************
* Universidad Fidelitas                        *
* Programacion Cliente Servidor Concurrente    *
* @author isaac, javier, mason, gabriel        *
* Fecha: 02/02/2024                            *
* Nombre: ClaseSemana3                         *
************************************************/

package com.mycompany.practicaprogramadahoteles;

import javax.swing.JOptionPane;


public class PracticaProgramadaHoteles {

    public static void main(String[] args) {
        //Se crea una instancia de hotel
        Hotel h = new Hotel();
        //Se crea una instancia de persona
        Persona p = new Persona();
        //Se crea un areglo que va a almacenar los 4 hoteles
        Hotel[] hoteles = new Hotel[4];
        hoteles[0] = new Hotel(" Continental de New York" , 2 ,5, 10);
        hoteles[1] = new Hotel(" Continental de Roma ",2,5,10 );
        hoteles[2] = new Hotel(" Continental de Marruecos", 2, 5 ,10);
        hoteles[3] = new Hotel(" Continental de Osaka Tokyo",2,5,10);
        
        Persona persona = null; // Variable para almacenar los datos de la persona
        //Se utiliza un do while para el menu principal
        int opcion;
        do{
            //Se imprimen las diferentes opciones a elegir
            System.out.println("1. Listar todos los hoteles de la cadena");
            System.out.println("2. Listar habitaciones disponibles por hotel");
            System.out.println("3. Registrar reservacion a una persona automaticamente");
            System.out.println("4. Registrar reservacion a una persona manualmente");
            System.out.println("5. Salir");
            
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Digite la opcion que desea realizar: "));
            //Dentro del switch se llama a los metodos necesarios para realizar cada una de las operaciones
            switch (opcion) {
                case 1:
                    h.listarHoteles(hoteles);
                    break;
                case 2:
                    h.listarHabitacionesDisponibles(hoteles);
                    break;
                case 3:
                    //Se verifica que si no hay datos de Persona, se les solicita llamando al metodo correspondiente
                    if(persona == null){
                        persona = p.ingresarDatosPersona();
                    }
                    h.reservarAutomaticamente(hoteles, persona);
                    break;
                case 4:
                    //Se verifica que si no hay datos de Persona, se les solicita llamando al metodo correspondiente
                    if(persona == null){
                        persona = p.ingresarDatosPersona();
                    }
                    //Se utiliza un Try Catch por si hay un error ingresando los datos solicitados
                    try{
                    int numHotel = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de hotel: "));
                    int numTorre = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de torre: "));
                    int numPiso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de piso: "));
                    int numHabitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de habitacion: "));
                    Hotel.reservarManualmente(hoteles, persona, numHotel, numTorre, numPiso, numHabitacion);
                    }catch(NumberFormatException e){  
                        JOptionPane.showMessageDialog(null, "Datos ingresados erroneamente!\n\nVolviendo al menu principal", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }finally{
                        JOptionPane.showMessageDialog(null, "Volviendo al menú principal");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            //Se sale del ciclo do while
        }while(opcion != 5);
    }
}
        
        
        
        
    



