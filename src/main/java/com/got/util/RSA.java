package com.got.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSA {
	
	private static final int KEY_SIZE = 1024;
	
	public static final String PRIVATE_KEY = "privateKey";
	public static final String PUBLIC_KEY_MODULUS = "modulus";
	public static final String PUBLIC_KEY_EXPONENT = "exponent";
	
	public static Map<String, Object> generateKey() {
		Map<String, Object> rsaKey = new HashMap<>();
		try{
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(KEY_SIZE);
			
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			RSAPublicKeySpec publicKeySpec = (RSAPublicKeySpec)keyFactory.getKeySpec(publicKey,RSAPublicKeySpec.class);
			String publicModulus = publicKeySpec.getModulus().toString(16);
			String publicExponent = publicKeySpec.getPublicExponent().toString(16);
			
			rsaKey.put(PRIVATE_KEY, privateKey);
			rsaKey.put(PUBLIC_KEY_MODULUS, publicModulus);
			rsaKey.put(PUBLIC_KEY_EXPONENT, publicExponent);
		}catch(NoSuchAlgorithmException e){System.out.println(e);
		}catch(InvalidKeySpecException e){System.out.println(e);}

		return rsaKey;
	}
	
	public static String decryptRsa(String securedValue, PrivateKey privateKey){
		Cipher cipher = null;
		String decryptedValue = null;
		try {
			cipher = Cipher.getInstance("RSA");
			byte[] encryptedBytes = hexTobyteArray(securedValue);
			cipher.init(Cipher.DECRYPT_MODE,privateKey);
			byte[] decryptedBytes;
			decryptedBytes = cipher.doFinal(encryptedBytes);
			decryptedValue = new String(decryptedBytes, "UTF-8");
		} catch (GeneralSecurityException e) {
			System.err.println(e);
		} catch (UnsupportedEncodingException e) {
			System.err.println(e);
		} 
		return decryptedValue;
	}
	
	private static byte[] hexTobyteArray(String hex){
		byte[] bytes = new byte[hex.length()/2];
		for(int i = 0; i < hex.length(); i+=2){
			byte val = (byte)Integer.parseInt(hex.substring(i, i+2), 16);
			bytes[(int)Math.floor(i/2)] = val;
		}
		return bytes;
	}
}
