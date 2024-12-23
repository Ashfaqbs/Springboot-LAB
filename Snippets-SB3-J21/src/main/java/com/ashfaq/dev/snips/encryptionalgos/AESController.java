
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aes")
public class AESController {
	private final AESService aesService;

	public AESController(AESService aesService) {
		this.aesService = aesService;
	}

	@GetMapping("/process")
	public String encryptAndDecrypt() {
		String data = "Hello World";
		try {
			String encryptedData = aesService.encrypt(data);
			String decryptedData = aesService.decrypt(encryptedData);
			return "Encrypted Data: " + encryptedData + "\nDecrypted Data: " + decryptedData;
			//encrypted data will be same all the time if  we are using same key
		} catch (Exception e) {
			return "Error during AES processing: " + e.getMessage();
		}
	}
}
