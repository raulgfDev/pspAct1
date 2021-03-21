package act01;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raúl
 */
public class Ejercicio2 {
 
    public static void main(String[] args) {
        
        Ejercicio2.ejecutar();
        System.out.println("prueba");
        
    }
    
    public static void ejecutar() {
        
        int nMovimientos = 10;
        
        int entrada;
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("EJERCICIO --> 2");
        System.out.println("_______________________\n");
        
        System.out.println("Bienvenido a nuestro banco");
        System.out.println("Intro la cantidad con la que quiere abrir la cuenta");
        
        entrada = sc.nextInt();
        
        CuentaBancaria miCuenta = new CuentaBancaria(entrada);
        
        System.out.println("");
        
        while(nMovimientos > 0){
            
            if(nMovimientos % 2 == 0){

                System.out.println("\nIntro cantidad a retirar");
                entrada = sc.nextInt();
                
                new Retirada(miCuenta,entrada);
               
            }else{
           
                System.out.println("\nIntro cantidad a ingresar");
                entrada = sc.nextInt();
                new Ingreso(miCuenta,entrada);
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
            }
            nMovimientos--;
        }
        
        //con metodo exit aseguramos que finalice el programa
        //aun habiendo un algun hilo a la espera
        System.exit(0);
    } 
}

class CuentaBancaria{

    Scanner sc = new Scanner(System.in);
    
    private int saldo;
  
    public CuentaBancaria(int apertura) {
        
        this.saldo = apertura;
    }
    
    synchronized public void retirar(int cantidad,Retirada r){
        
        
        System.out.println("Desea retirar " + cantidad + "€. Su saldo es "
                + "de " + saldo + "€");
        
        while(saldo < cantidad) {
            
            try {
                System.out.println("-------Duermo " + r.nombreHilo + 
                        ": saldo inferior a " + cantidad + "------\n");
                wait();
                if(saldo >= cantidad){
                    System.out.println("\n-------Despierto "+ r.nombreHilo + "-----");
                }
                
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        saldo -= cantidad;
        System.out.println("APROVADA: Retirada de " + cantidad + "€");
        System.out.println("Su saldo es de " + saldo + "€");
        notify();
    }
    
    synchronized public void ingresar(int cantidad){
        
        saldo += cantidad;
        System.out.println("Ingreso de " + cantidad + "€");
        System.out.println("Su saldo es de " + saldo + "€");
        
        //utilizo metodo notifyAll (en vez de notify) ya que puede
        //que haya varias retiradas esperando y algunas puedan hacerse y 
        //otras no porque no haya saldo suficiente
        notifyAll();
    } 
}

class Ingreso implements Runnable{
    
    CuentaBancaria c;
    int cantidad;
    
    public Ingreso(CuentaBancaria c,int cantidad) {
        
        this.c = c;
        this.cantidad = cantidad;
        new Thread(this).start();  
    }

    @Override
    public void run() {
        c.ingresar(cantidad);
    }   
}

class Retirada implements Runnable{
    
    private CuentaBancaria c;
    private int cantidad;
    
    private static int contador = 1;
    protected  String nombreHilo;
    
    public Retirada(CuentaBancaria c, int cantidad) {
        
        this.c = c;
        this.cantidad = cantidad;
        nombreHilo = "Retirada nº " + contador;
        Thread h = new Thread(this);
        h.start();
        contador++;
    }

    @Override
    public void run() {
        c.retirar(cantidad,this);
    }   
}
