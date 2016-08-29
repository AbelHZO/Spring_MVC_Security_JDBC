package com.abelhzo.spring.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypto {

	public static String BCryto(String pass) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		return encoder.encode(pass);
	}

}
