package fr.eservices.drive.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Qualifier("MD5")
public class MD5Checker implements PasswordChecker {
	
	MessageDigest digest;

	@Override
	public String encode(String login, String password) {
		String toEncode=login+password;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(toEncode.getBytes());
		byte[] rawData = digest.digest();
		byte[] encoded = Base64.getEncoder().encode(rawData);
		String retValue = new String(encoded);
		return retValue;
	}
	
}
