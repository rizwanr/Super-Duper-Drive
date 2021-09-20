package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
    public interface FileMapper{

        @Insert("INSERT INTO FILES  ( filename, contenttype, filesize, userid, filedata) VALUES( #{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
        @Options(useGeneratedKeys = true, keyProperty = "fileId")
        int  insertFile(File file);


        @Select("SELECT * FROM FILES WHERE userid = #{userId}")
        List<File>  getFileForUsers(Integer userId);

        @Delete("DELETE from FILES WHERE fileid=#{fileid} ")
        void removeFile(Integer fileid);



    }


