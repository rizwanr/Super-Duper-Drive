//package com.udacity.jwdnd.course1.cloudstorage.services;
//
//import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
//import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
//import com.udacity.jwdnd.course1.cloudstorage.model.File;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class FileService {
//
//
//    private FileMapper fileMapper;
//    private UserMapper userMapper;
//
//    public FileService(FileMapper fileMapper, UserMapper userMapper) {
//        this.fileMapper = fileMapper;
//        this.userMapper = userMapper;
//    }
//
//    public void addFiles(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, List<Byte> fileData){
//        fileMapper.insert(new File(fileId,fileName,contentType,fileSize,userId, (ArrayList<Byte>) fileData));
//
//    }
//
//    public List<File> getListOfFilesForUsers(Integer userId){
//       return  fileMapper.getFileForUsers(userId);
//    };
//
//    public void deleteFile(Integer fileId){
//        fileMapper.removeFile(fileId);
//    }
//
//
//}
