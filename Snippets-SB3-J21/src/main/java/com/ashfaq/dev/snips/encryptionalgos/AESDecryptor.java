package com.ashfaq.example;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESDecryptor {

	private final SecretKeySpec secretKey;

	public AESDecryptor(String secret) {
		byte[] key = secret.getBytes();
		this.secretKey = new SecretKeySpec(key, "AES");
	}

	public String decrypt(String encryptedText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
		byte[] decryptedBytes = cipher.doFinal(decodedBytes);
		return new String(decryptedBytes);
	}
}
