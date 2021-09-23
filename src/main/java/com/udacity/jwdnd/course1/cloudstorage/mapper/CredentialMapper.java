package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  CredentialMapper {

        @Insert("INSERT INTO CREDENTIALS ( url, username,key,password,userid) VALUES( #{url}, #{username}, #{key},#{password},#{userid})")
        @Options(useGeneratedKeys = true, keyProperty = "credentialId")
        int insert(Credential credential);


        @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
        List<Credential> getCredentialsForUser(Integer userId);

        @Delete("DELETE from CREDENTIALS WHERE credentialid=#{credentialId} ")
        void removeCredential(Integer credentialid);


        @Update("UPDATE CREDENTIALS  SET url = #{url}, username = #{username}, password =#{password} WHERE credentialid = #{credentialId}")
        void updateCredentials(String url, String username, String password);




}
