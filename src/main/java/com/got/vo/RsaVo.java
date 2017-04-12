package com.got.vo;

import java.security.PrivateKey;

public class RsaVo {
	private PrivateKey privateKey;
	private String publicKeyModulus;
	private String publicKeyExponent;
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public RsaVo setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
		return this;
	}
	public String getPublicKeyModulus() {
		return publicKeyModulus;
	}
	public RsaVo setPublicKeyModulus(String publicKeyModulus) {
		this.publicKeyModulus = publicKeyModulus;
		return this;
	}
	public String getPublicKeyExponent() {
		return publicKeyExponent;
	}
	public RsaVo setPublicKeyExponent(String publicKeyExponent) {
		this.publicKeyExponent = publicKeyExponent;
		return this;
	}
}
