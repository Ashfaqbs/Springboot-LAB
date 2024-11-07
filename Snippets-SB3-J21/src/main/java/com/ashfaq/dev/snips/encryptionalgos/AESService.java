
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class AESService {

	private final SecretKey secretKey;

	public AESService() throws Exception {
		// Uncomment the following lines to generate a random key
		 KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		 keyGen.init(128);
		 this.secretKey = keyGen.generateKey();

		// Use a custom-defined secret key
//		String customKey = "1234567890123456"; // Must be 16 bytes for AES-128
//		this.secretKey = new SecretKeySpec(customKey.getBytes(), "AES");
	}

	public String encrypt(String data) throws Exception {
		Cipher encryptCipher = Cipher.getInstance("AES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = encryptCipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decrypt(String encryptedData) throws Exception {
		Cipher decryptCipher = Cipher.getInstance("AES");
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
		return new String(decryptCipher.doFinal(decodedBytes));
	}
}
