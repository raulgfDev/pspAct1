package act4_psp;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Raúl
 */
public class Ejercicio_1 {

    private static Scanner sc = new Scanner(System.in);

    public void ejecutar() {

        System.err.println("Intro texto a encriptar");
        String paraEncriptar = sc.nextLine();

        System.out.println("\n" + encriptarAES(paraEncriptar) + "\n");
        System.out.println(encriptar3DES(paraEncriptar) + "\n");
        System.out.println(encriptarRSA(paraEncriptar) + "\n");
        System.out.println(funcionHASH(paraEncriptar) + "\n");
    }

    public static String encriptarAES(String textoClaro) {

        String salida = null;

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey clave = kg.generateKey();

            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, clave);

            byte[] textoEntrada = textoClaro.getBytes();
            byte[] textoCifrado = c.doFinal(textoEntrada);

            salida = new String(textoCifrado);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        return "Cifrado AES:\nMensaje cifrado --> " + salida;
    }

    public static String encriptar3DES(String textoClaro) {

        String salida = null;

        try {
            KeyGenerator kg = KeyGenerator.getInstance("DESede");
            kg.init(168);
            SecretKey clave = kg.generateKey();

            Cipher c = Cipher.getInstance("DESede");
            c.init(Cipher.ENCRYPT_MODE, clave);

            byte[] textoEntrada = textoClaro.getBytes();
            byte[] textoCifrado = c.doFinal(textoEntrada);

            salida = new String(textoCifrado);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        return "Cifrado Triple Des:\nMensaje cifrado --> " + salida;
    }

    public static String encriptarRSA(String textoClaro) {

        String salida = null;
        String salida2 = null;

        try {

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(512);
            KeyPair clavesRSA = kpg.genKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            byte[] mensaje = textoClaro.getBytes();

            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, clavePublica);

            byte[] textoCifrado = c.doFinal(mensaje);

            salida = new String(textoCifrado);

            //desencriptar
            c.init(Cipher.DECRYPT_MODE, clavePrivada);

            byte[] txDescifrado = c.doFinal(textoCifrado);

            salida2 = new String(txDescifrado);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Cifrado RSA:\nMensaje cifrado --> " + salida
                + "\n\nAhora el texto descifrazo RSA: \n" + 
                "Mensaje descifrado --> " + salida2;
    }

    public static String funcionHASH(String textoClaro) {

        String salida = null;

        try {

            MessageDigest md = MessageDigest.getInstance("SHA");

            byte[] datosBytes = textoClaro.getBytes();

            md.update(datosBytes);

            byte[] resumen = md.digest();

            salida = new String(resumen);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Calcular Resumen: \nResumen --> " + salida;
    }
}
