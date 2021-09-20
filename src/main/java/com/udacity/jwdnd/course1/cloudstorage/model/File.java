package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.ArrayList;
import java.util.List;

public class File {

    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private List<Byte> filedata;;

    public File(Integer fileId, String filename, String contenttype, String filesize, Integer userId, ArrayList<Byte> filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userId;
        this.filedata = filedata;
    }



    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String fileName) {
        this.filename = fileName;
    }

    public String getContentType() {
        return contenttype;
    }

    public void setContentType(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFileSize() {
        return filesize;
    }

    public void setFileSize(String fileSize) {
        this.filesize = fileSize;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public List<Byte> getFileData() {
        return filedata;
    }

    public void setFileData(List<Byte> fileData) {
        this.filedata = fileData;
    }



}
