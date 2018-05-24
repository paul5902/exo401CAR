package fr.eservices.drive.util;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Hmac")
public class HmacChecker implements PasswordChecker {
	
	@Override
	public String encode(String login, String password) {
		try {

		     Mac sha256_HMAC = Mac.getInstance("HmacSHA1");
		     SecretKeySpec secret_key = new SecretKeySpec(login.getBytes(), "HmacSHA1");
		     sha256_HMAC.init(secret_key);

		     byte[] hash = Base64.getEncoder().encode(sha256_HMAC.doFinal(password.getBytes()));
		     String toReturn = new String(hash);
		     return toReturn;
		    }
		    catch (Exception e){
		     System.out.println("Error");
		     return null;
		    }
		
	}

}
