package com.hk.abcfund.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Utility class for RSA
 */
public final class RSAUtility {
	/**
	 * Generate new key pair
	 * @return A new generated key pair
	 */
	public static KeyPair genRSAKeyPair() {
		KeyPair keyPair = null;
		
		try {
			// Get generator using RSA
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			
			// Set key size to 1024byte
			keyGen.initialize(1024, new SecureRandom());
			
			// Generate key pair
			keyPair =  keyGen.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// This system does not support RSA algorithm
		}
		
		return keyPair;
	}
	
	/**
	 * Encrypt a plain text using RSA
	 * @param text A plain text
	 * @param publicKey A public key
	 * @return Encrypted text by base64
	 */
	public static String encryptRSAByBase64(String text, PublicKey publicKey) {
		String encrypted = null;
		
		try {
			// Get cipher using RSA
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			
			byte[] byteText = cipher.doFinal(text.getBytes());
			encrypted = Base64.getEncoder().encodeToString(byteText);
		} catch(NoSuchAlgorithmException e) {
			// This system does not support RSA
		} catch(NoSuchPaddingException e) {
			
		} catch(InvalidKeyException e) {
			
		} catch(BadPaddingException e) {
			
		} catch(IllegalBlockSizeException e) {
			
		}
		
		return encrypted;
	}
	
	/**
	 * Decrypt a encrypted text using RSA
	 * @param encrypted A encrypted text
	 * @param privateKey A private key
	 * @return Decrypted text
	 */
	public static String decryptRSAbyBase64(String encrypted, PrivateKey privateKey) {
		String decrypted = null;
		
		try {
			// Get cipher using RSA
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			// Decode using base64 and cipher
	        byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
	        byte[] bytePlain = cipher.doFinal(byteEncrypted);
	        
	        decrypted = new String(bytePlain, "utf-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// This system does not support RSA
		} catch (InvalidKeyException e) {
			
		} catch (BadPaddingException | IllegalBlockSizeException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		}
		
		return decrypted;
	}
}