package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("file")
public class FileUploadController {

    private final FileService fileService;
    private final UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping
    public String getHome(Authentication auth, @ModelAttribute("file") File file, Model model) {
        User user = userService.getUser(auth.getName());
        model.addAttribute("files", this.fileService.getListOfFilesForUsers(user.getUserId()));
        return "redirect:/home";
    }


//    @PostMapping("file-upload")
//    public String handleFileUpload(Authentication auth, @RequestParam("fileUpload")MultipartFile fileUpload,
//                                   @ModelAttribute("files") File file,Model model) throws IOException {
//        String username = auth.getName();
//        Integer userId = userService.getUser(username).getUserId();
//        fileService.addFiles(fileUpload.getOriginalFilename(),fileUpload.getContentType(), fileUpload.getSize(),userId,fileUpload.getBytes());
//        model.addAttribute("files", fileService.getListOfFilesForUsers(userId));
//        return "redirect:/home";
//
//
//
//    }



    @PostMapping("file-upload")
    public String postNewFile(Authentication auth, @RequestParam("fileUpload")MultipartFile fileUpload,
                                   @ModelAttribute("files") File file,Model model) throws IOException {
        String username = auth.getName();
        Integer userId = userService.getUser(username).getUserId();
        File f = fileService.getFileByName(fileUpload.getOriginalFilename());
        if (f == null){
            fileService.addFiles(fileUpload.getOriginalFilename(),fileUpload.getContentType(), fileUpload.getSize(),userId,fileUpload.getBytes());
        }
        else{
            System.out.println("Incorrect");
        }
        model.addAttribute("files", fileService.getListOfFilesForUsers(userId));
        return "redirect:/home";

    }



        @GetMapping("/delete-file/{fileId}")
        public String removeFile(@PathVariable(value = "fileId") Integer fileId, Authentication auth,  Model model){
            fileService.deleteFile(fileId);
            return "redirect:/home";

        }

//    Return type to ResponseEntity<byte[]> since we will use it so that the file can be displayed or downloaded
//    Use the FileService getFileById method to return the file that matches the submitted id
//    Put the name of the file in a variable named filename to be placed in the org.springframework.http.ResponseEntity composition
//    Use ResponseEntity to display the file or open the download window

        @GetMapping("/view-file/{fieldId}")
        public ResponseEntity<byte[]> viewFile(@PathVariable(value ="fieldId") Integer fieldId, Model model){
            System.out.println("File " + fieldId + " was viewed");
            File file = fileService.getFileById(fieldId);
            String fileName = file.getFileName();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""+ fileName+"\"")
                    .body(file.getFileData());
        }
}


