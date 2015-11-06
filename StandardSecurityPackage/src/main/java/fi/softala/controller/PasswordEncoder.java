package fi.softala.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//luokka hashattujen salasanojen generointiin
public class PasswordEncoder {
	public static void main(String[] args) {

		String password = "12345";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword1 = passwordEncoder.encode(password);
		System.out.println(hashedPassword1);
	}
}
