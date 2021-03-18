package act4_psp;

import java.util.Scanner;

/**
 *
 * @author Raúl
 */
public class Actividad_4_PSP {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        ejecutarMenu();
    }

    public static void ejecutarMenu() {
        
        Ejercicio_1 e1 = new Ejercicio_1();
        Ejercicio_2 e2 = new Ejercicio_2();
        
        int opcion;

        do {
            imprimirMenu();
            opcion = sc.nextInt();

            if (opcion == 1) e1.ejecutar();
            else if (opcion == 2) e2.ejecutar();
            else if (opcion == 3)  System.exit(0);
            else System.out.println("Selecciona una opcion del menu");
        
        } while (true);

    }

    public static void imprimirMenu() {

        System.out.println("\n+--------------------+");
        System.out.println("|       MENU         |");
        System.out.println("|--------------------|");
        System.out.println("| 1. Ejercicio 1     |");
        System.out.println("| 2. Ejercicio 2     |");
        System.out.println("|--------------------|");
        System.out.println("| 3. SALIR           |");
        System.out.println("+--------------------+");
    }
}
