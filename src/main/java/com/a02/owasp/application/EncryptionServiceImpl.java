package com.a02.owasp.application;

import com.a02.owasp.domain.constants.GeneralConstants;
import com.a02.owasp.domain.ports.in.EncryptionService;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

public class EncryptionServiceImpl implements EncryptionService {

    private static final Logger logger = Logger.getLogger(EncryptionServiceImpl.class.getName());

    private SecretKey secretKey;

    public EncryptionServiceImpl() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            logger.severe("Error while generating AES key");
        }
    }

    @Override
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(GeneralConstants.INSECURE_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), GeneralConstants.INSECURE_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(GeneralConstants.INSECURE_ALGORITHM);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), GeneralConstants.INSECURE_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }
}
