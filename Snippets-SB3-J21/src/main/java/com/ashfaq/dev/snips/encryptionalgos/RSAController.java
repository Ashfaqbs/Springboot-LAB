
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rsa")
public class RSAController {
	private final RSAService rsaService;

	public RSAController(RSAService rsaService) {
		this.rsaService = rsaService;
	}

	@GetMapping("/process")
	public String encryptAndDecrypt() {
		String data = "Hello World";
		try {
			String encryptedData = rsaService.encrypt(data);
			String decryptedData = rsaService.decrypt(encryptedData);
			return "Encrypted Data: " + encryptedData + "\nDecrypted Data: " + decryptedData;
		} catch (Exception e) {
			return "Error during RSA processing: " + e.getMessage();
		}
	}
}
