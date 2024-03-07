import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CifradorRC4 {

    static String rutaArchivo = "C:\\Users\\Felix\\Desktop\\texto.txt";
    static String key = "clave secreta";

    public byte[] readFile(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }

    public byte[] cipherData(byte[] data, String key, int mode) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "RC4");
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(mode, secretKey);
        return cipher.doFinal(data);
    }

    public void writeFile(String filePath, byte[] data) throws Exception {
        Files.write(Paths.get(filePath), data);
    }

    public static void main(String[] args) {
        try {
            CifradorRC4 rc4Cipher = new CifradorRC4();

            byte[] data = rc4Cipher.readFile(rutaArchivo);
            byte[] encryptedData = rc4Cipher.cipherData(data, key, Cipher.ENCRYPT_MODE);
            rc4Cipher.writeFile(rutaArchivo + ".enc", encryptedData);

            byte[] decryptedData = rc4Cipher.cipherData(encryptedData, key, Cipher.DECRYPT_MODE);
            rc4Cipher.writeFile(rutaArchivo + ".dec", decryptedData);

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