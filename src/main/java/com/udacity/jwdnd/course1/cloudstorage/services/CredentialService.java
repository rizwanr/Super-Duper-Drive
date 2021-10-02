package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private EncryptionService encryptionService;
    private CredentialMapper credentialMapper;
    private UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper,  EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService=encryptionService;
    }

    public void addCredentials( String  url,String username, String password, Integer userid) {
        String encodedKey = encodedKey();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        Credential credential = new Credential(1,url, username,encodedKey,encryptedPassword, userid);
        credentialMapper.insert(credential);
    }

    private String encodedKey(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;

    }



    public void updateCredential(Integer credentialId, String url, String username,String password) {
        String encodedKey = encodedKey();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credentialMapper.updateCredentials(credentialId, url, username, encodedKey, encryptedPassword);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.removeCredential(credentialId);
    }



    public List<Credential> getCredentials (Integer userId){
        return  credentialMapper.getCredentialsForUser(userId);
    }

    public Credential getCredentialByUsername(String username){
        return credentialMapper.getCredentialByUsername(username);
    }







}
