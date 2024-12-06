package fantasma;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ghost {
    public static void main(String[]args){
     //ghostGame.menuInicio();  // Llamar al menú de inicio
     ghostGame juego = new ghostGame();
    Scanner entrada = new Scanner(System.in);
        int opcion=0;
        
        do {
            try{
            System.out.println("------Menu de Inicio------");
            System.out.println("1. Login");
            System.out.println("2. Crear Player");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            switch (opcion) {
                case 1:
                    juego.login();
                    break;
                case 2:
                    juego.crearPlayer();
                    break;
                case 3:
                    System.out.println("Saliendo del juego...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
            catch(InputMismatchException e){
            System.out.println("Opcion invalida");
            entrada.nextLine(); 
        }
        } while (opcion!=3);
        
        
    }
    }



