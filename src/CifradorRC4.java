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