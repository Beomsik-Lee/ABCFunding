package com.hk.abcfund.model.dto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

/**
 * RSA Class for exponent and modulus
 * @author Beom
 *
 */
public class RSA {
	/**
	 * Public key
	 */
	private PublicKey publicKey;
	
	/**
	 * Private key
	 */
	private PrivateKey privateKey;
	
	/**
	 * A exponent in public key
	 */
	private String exponent;
	
	/**
	 * A modulus in public key
	 */
	private String modulus;
	
	/**
	 * A constructor using key pair
	 * @param keyPair
	 * @throws Exception 
	 */
	public RSA(KeyPair keyPair) throws Exception {
		if (keyPair == null) throw new Exception("The key pair is null");
		
		// Get public and private key
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
		
		// Set KeyFactory to RSA and key spec
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec publicSpec = keyFactory.getKeySpec(this.publicKey, RSAPublicKeySpec.class);
		
		// Get modulus and exponent by hex
		this.exponent = publicSpec.getPublicExponent().toString(16);
		this.modulus = publicSpec.getModulus().toString(16);
	}

	/**
	 * @return the publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the privateKey
	 */
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 * @return the exponent
	 */
	public String getExponent() {
		return exponent;
	}

	/**
	 * @param exponent the exponent to set
	 */
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

	/**
	 * @return the modulus
	 */
	public String getModulus() {
		return modulus;
	}

	/**
	 * @param modulus the modulus to set
	 */
	public void setModulus(String modulus) {
		this.modulus = modulus;
	}
}
