# Algoritmo RC4

El algoritmo RC4 (Rivest Cipher 4) es un algoritmo de cifrado de flujo desarrollado por Ronald Rivest de RSA Security en 1987. Aunque originalmente era un algoritmo propietario, el código fuente de RC4 fue filtrado en 1994 y desde entonces ha sido ampliamente analizado y utilizado en una variedad de sistemas de seguridad.

## ¿Cómo funciona?

El algoritmo RC4 genera un flujo de bits pseudoaleatorio (también conocido como "keystream") que luego se combina con los bits del texto plano para producir el texto cifrado. Este proceso se realiza mediante una operación XOR bit a bit.

El algoritmo RC4 consta de dos partes principales:

1. **Inicialización del vector de estado**: RC4 comienza con una matriz de estado de 256 bytes que se inicializa en un orden específico, y luego se mezcla en función de la clave secreta.

2. **Generación de keystream**: Después de la inicialización, RC4 genera el keystream, que se utiliza para cifrar el texto plano. Durante este proceso, la matriz de estado se mezcla aún más.

## ¿Cómo se utiliza?

En Java, puedes utilizar el algoritmo RC4 a través de la clase `Cipher` proporcionada por el paquete `javax.crypto`. Aquí hay un ejemplo de cómo se puede utilizar:

```java
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CifradorRC4 {

    // Ruta del archivo y clave secreta para la encriptación y desencriptación
    static String rutaArchivo = "C:\\Users\\Felix\\Desktop\\texto.txt";
    static String key = "clave secreta";

    // Método para leer el contenido de un archivo como bytes
    public byte[] readFile(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

    // Método para encriptar o desencriptar datos usando el algoritmo RC4
    public byte[] cipherData(byte[] data, String key, int mode) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "RC4");
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(mode, secretKey);
        return cipher.doFinal(data);
    }

    // Método para escribir datos en bytes a un archivo
    public void writeFile(String filePath, byte[] data) throws Exception {
        Files.write(Paths.get(filePath), data);
    }

    public static void main(String[] args) {
        try {
            CifradorRC4 rc4Cipher = new CifradorRC4();

            // Leyendo el contenido del archivo
            byte[] data = rc4Cipher.readFile(rutaArchivo);
            // Encriptando el contenido del archivo
            byte[] encryptedData = rc4Cipher.cipherData(data, key, Cipher.ENCRYPT_MODE);
            // Escribiendo los datos encriptados a un nuevo archivo
            rc4Cipher.writeFile(rutaArchivo + ".enc", encryptedData);

            // Desencriptando los datos encriptados
            byte[] decryptedData = rc4Cipher.cipherData(encryptedData, key, Cipher.DECRYPT_MODE);
            // Escribiendo los datos desencriptados a un nuevo archivo
            rc4Cipher.writeFile(rutaArchivo + ".dec", decryptedData);

            // Verificando si los datos originales y los datos desencriptados son iguales
            if (Arrays.equals(data, decryptedData)) {
                System.out.println("La encriptación y desencriptación se han realizado correctamente.");
            } else {
                System.out.println("La encriptación y desencriptación no se han realizado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Fuentes

1. [Wikipedia - RC4](https://es.wikipedia.org/wiki/RC4)
2. [Java Cryptography Architecture (JCA) Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html)
3. [Java Code Examples for javax.crypto.Cipher](https://www.programcreek.com/java-api-examples/?class=javax.crypto.Cipher&method=getInstance)
