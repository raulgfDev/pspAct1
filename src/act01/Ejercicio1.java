package act01;

import java.util.Scanner;

/**
 *
 * @author Raúl
 */

class Ejercicio1 extends Thread{
    
    public static void main(String[] args) {
        
        Ejercicio1.ejecutar();
        System.out.println("añadido para git");
    }
    
    private int entrada;

    public Ejercicio1(int entrada) {
        
        this.entrada = entrada;
    }

    @Override
    public void run() {
        
        System.out.println("Tabla de multiplicar del " + entrada + "\n----------------------------");
       
        for(int i = 1; i <= 10;i++){
            
            int multiplicacion = entrada * i;
            
            System.out.println(entrada + " * " + i + " = " + multiplicacion);
        }
    }
    
    public static void ejecutar(){
        
        System.out.println("EJERCICIO --> 1");
        System.out.println("_______________________\n");
        Scanner sc = new Scanner(System.in);
        int entrada;
        
        System.out.println("Visualizador de tres tablas de multiplicar");
        
        System.out.println("Introduce el PRIMER numero:");
        entrada = sc.nextInt();
        Thread uno = new Ejercicio1(entrada);
        
        System.out.println("Introduce el SEGUNDO numero:");
        entrada = sc.nextInt();
        Thread dos = new Ejercicio1(entrada);
    
        System.out.println("Introduce el TERCER numero:");
        entrada = sc.nextInt();
        Thread tres = new Ejercicio1(entrada);
        
        try {
            
            uno.start();
            uno.join();
            
            dos.start();
            tres.start();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        } 
    }
}
