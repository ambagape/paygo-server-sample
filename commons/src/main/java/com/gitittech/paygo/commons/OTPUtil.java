package com.gitittech.paygo.commons;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class OTPUtil {
    final Duration timeStep = Duration.ofMinutes(15);
    private TimeBasedOneTimePasswordGenerator totp;

    public OTPUtil() {
        try {
            totp = new TimeBasedOneTimePasswordGenerator(timeStep);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SecretKey generateOTPKey(String key) throws NoSuchAlgorithmException {
        byte[] bytesArray = key.getBytes();
        return new SecretKeySpec(bytesArray, totp.getAlgorithm());
    }

    public int generateOTP(String uniqueId) throws Exception {
        SecretKey key = key(uniqueId);
        Instant now = Instant.now();
        return generate(key, now);
    }

    public SecretKey key(String key) throws NoSuchAlgorithmException {
        byte[] bytesArray = key.getBytes();
        return new SecretKeySpec(bytesArray, totp.getAlgorithm());
    }

    Integer generate(SecretKey secretKey, Instant instant) throws Exception {
        Integer otp = null;
        otp = totp.generateOneTimePassword(secretKey, instant);
        return otp;
    }
}
