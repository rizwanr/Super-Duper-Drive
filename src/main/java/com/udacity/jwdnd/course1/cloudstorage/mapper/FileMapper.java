package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;


    @Mapper
    public interface FileMapper{

        @Insert("INSERT INTO FILES  (fileid, filename, contenttype, filesize, userid, filedata) VALUES(#{fileid}, #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
        @Options(useGeneratedKeys = true, keyProperty = "fileid")
        int  insert(File file);


        @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
        File  getFile(Integer fileid);

        @Delete("DELETE from FILES WHERE fileid=#{fileid} ")
        File removeFile(Integer fileid);

    }


