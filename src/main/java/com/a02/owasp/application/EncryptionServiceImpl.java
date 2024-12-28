package com.a02.owasp.application;

import com.a02.owasp.domain.ports.in.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EncryptionServiceImpl implements EncryptionService {
    private static final String ALGORITHM = "AES/GCM/NoPadding";

    @Override
    public String encrypt(String data) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        System.out.println("Fecha y hora del sistema: " + formattedDateTime);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }
}
