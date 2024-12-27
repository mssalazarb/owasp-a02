package com.a02.owasp.domain.ports.in;

public interface EncryptionService {
    public String encrypt(String data) throws Exception;

    public String decrypt(String encryptedData) throws Exception;
}
