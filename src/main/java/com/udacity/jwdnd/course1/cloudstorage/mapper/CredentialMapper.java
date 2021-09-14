package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface  CredentialMapper {



        @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key,password, userid) VALUES(#{credentialid}, #{url}, #{username},#{key}, #{password}, #{userid})")
        @Options(useGeneratedKeys = true, keyProperty = "credentialid")
        int insert(Credential note);


        @Select("SELECT * FROM CREDENTIALS WHERE noteid = #{noteid}")
        Credential  getNote(Integer credentialid);

        @Delete("DELETE from CREDENTIALS WHERE noteid=#{noteid} ")
        Credential removeFile(Integer credentialid);


}
