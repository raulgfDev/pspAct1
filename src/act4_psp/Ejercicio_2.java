package act4_psp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import javax.crypto.Cipher;

/**
 *
 * @author Raúl
 */
public class Ejercicio_2 {

    Scanner sc = new Scanner(System.in);

    public void ejecutar() {

        try {
            
// GENERO CLAVES PUBLICA Y PRIVADA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            keyGen.initialize(512);

            KeyPair clavesRSA = keyGen.generateKeyPair();

            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            FileOutputStream salidaPublica = new FileOutputStream("Clave_publica");
            BufferedOutputStream bufferPublico = new BufferedOutputStream(salidaPublica);

            String cadena = clavePublica.toString();
            byte[] bytesPublicos = cadena.getBytes();
            bufferPublico.write(bytesPublicos);
            
            FileOutputStream salidaPrivada = new FileOutputStream("Clave_privada");
            BufferedOutputStream bufferPrivado = new BufferedOutputStream(salidaPrivada);
            
            cadena = clavePrivada.toString();
            byte [] bytesPrivados = cadena.getBytes();
            bufferPrivado.write(bytesPrivados);
            
            bufferPublico.close();
            bufferPrivado.close();
            
            salidaPublica.close();
            salidaPrivada.close();
            
// ENCRIPTO FICHERO Y LO GRABO EN EL DISCO
            byte [] array = encriptar(clavePublica);

 // DESENCRIPTO FICHERO Y LO GRABO EN EL DISCO
            desencriptar(clavePrivada,array);
  
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public byte[] encriptar(PublicKey publicKey) {
        
        byte[] mensajeCifrado = null;
        
        try {
            
            System.out.println("Intro texto a exportar y cifrar");
            String cadena = sc.nextLine();

            FileOutputStream fos = null;
            fos = new FileOutputStream("cifrado.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte cadenaByte[] = cadena.getBytes();
            
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE,publicKey);
            mensajeCifrado = c.doFinal(cadenaByte);
            
            bos.write(mensajeCifrado);
            
            bos.close();
            fos.close();
  
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return mensajeCifrado;
    }
    
    public void desencriptar(PrivateKey privateKey, byte[] mensajeCifrado){

        try {
            
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] descifrado = c.doFinal(mensajeCifrado);
            
            FileOutputStream fos = new FileOutputStream("Descifrado.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            
            bos.write(descifrado);
  
            bos.close();
            fos.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }
}
