package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
    public String getHome(Authentication auth, @ModelAttribute("file") File file, Model model){
        User user =userService.getUser(auth.getName());
        model.addAttribute("files",this.fileService.getListOfFilesForUsers(user.getUserId()));
        return "redirect:/home";
    }

    @PostMapping("file-upload")
    public String handleFileUpload(Authentication auth, @RequestParam("fileUpload")MultipartFile fileUpload,
    @ModelAttribute("files") File file,Model model) throws IOException {
        String username = auth.getName();
        Integer userId = userService.getUser(username).getUserId();
        fileService.addFiles(fileUpload.getOriginalFilename(),fileUpload.getContentType(), fileUpload.getSize(),userId,fileUpload.getBytes());


        model.addAttribute("files", fileService.getListOfFilesForUsers(userId));

        return "redirect:/home";



    }


    @GetMapping("/delete-file/{fileId}")
    public String removeFile(@PathVariable(value = "fileId") Integer fileId, Authentication auth,  Model model){
        fileService.deleteFile(fileId);
        return "redirect:/home";

    }

}


