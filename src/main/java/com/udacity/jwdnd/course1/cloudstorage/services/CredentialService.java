package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    public void addCredentials( String  url,String username,String key, String password, Integer userid) {
        Integer userId = userMapper.getUser(username).getUserId();
        Credential credential = new Credential(1, url, username, key,password, userid);
        credentialMapper.insert(credential);
    }


    public void modifyCredential(Integer credentialId, String url, String username, String password) {
        credentialMapper.updateCredentials(url, username, password);
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.removeCredential(credentialId);
    }



    public List<Credential> getCredentials (Integer userId){
        return  credentialMapper.getCredentialsForUser(userId);
    }








}
