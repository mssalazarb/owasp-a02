package com.a02.owasp.domain.ports.in;

public interface EncryptionService {
    String encrypt(String data) throws Exception;

    String decrypt(String encryptedData) throws Exception;
}
