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

		     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		     SecretKeySpec secret_key = new SecretKeySpec(login.getBytes(), "HmacSHA256");
		     sha256_HMAC.init(secret_key);

		     String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(password.getBytes()));
		     System.out.println(hash);
		     return hash;
		    }
		    catch (Exception e){
		     System.out.println("Error");
		     return null;
		    }
		
	}

}
