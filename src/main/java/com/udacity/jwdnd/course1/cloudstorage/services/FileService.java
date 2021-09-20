package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileService {


    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public void addFiles(String fileName, String contentType, Long fileSize, Integer userId, byte[] fileData){
        fileMapper.insertFile(new File(1, fileName,contentType,fileSize,userId,  fileData));

    }

    public File getFileById(Integer fileId){
        return fileMapper.getFileById(fileId);
    }

    public List<File> getListOfFilesForUsers(Integer userId){
       return  fileMapper.getFileForUsers(userId);
    };

    public void deleteFile(Integer fileId){
        fileMapper.removeFile(fileId);
    }


}
