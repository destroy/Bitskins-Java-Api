package spec45as.bitskins.api;

import org.jboss.aerogear.security.otp.Totp;


public class OtpGenerator {
    public static String generatePassword(String secret) {
        return (new Totp(secret)).now();
    }
}
