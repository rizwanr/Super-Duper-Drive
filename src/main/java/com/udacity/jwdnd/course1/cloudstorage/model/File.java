package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer fileId;
    private String filename;
    private String contenttype;
    private Long filesize;
    private Integer userid;
    private byte [] filedata;;

    public File(Integer fileId, String filename, String contenttype, Long filesize, Integer userId,byte [] filedata) {
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

    public Long getFileSize() {
        return filesize;
    }

    public void setFileSize(Long fileSize) {
        this.filesize = fileSize;
    }

    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public byte[] getFileData() {
        return filedata;
    }

    public void setFileData(byte[] fileData) {
        this.filedata = fileData;
    }



}
