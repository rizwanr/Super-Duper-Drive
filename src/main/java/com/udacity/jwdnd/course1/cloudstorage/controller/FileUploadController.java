//package com.udacity.jwdnd.course1.cloudstorage.controller;
//
//import com.udacity.jwdnd.course1.cloudstorage.model.File;
//import com.udacity.jwdnd.course1.cloudstorage.model.User;
//import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
//import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//
//@Controller
//@RequestMapping("fileupload")
//public class FileUploadController {
//
//    private final FileService fileService;
//    private final UserService userService;
//
//    public FileUploadController(FileService fileService, UserService userService) {
//        this.fileService = fileService;
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String getHome(Authentication auth, @ModelAttribute("file") File file, Model model){
//        User user =userService.getUser(auth.getName());
//        model.addAttribute("files",this.fileService.getListOfFilesForUsers(user.getUserId()));
//        return "redirect:/home";
//    }
//
//    @PostMapping("file-upload")
//    public String handleFileUpload(Authentication auth, @RequestParam("fileUpload")MultipartFile fileUpload,
//    @ModelAttribute("file") File file,Model model) throws IOException {
//        String username = auth.getName();
//        String fileName = file.getFileName();
//        file.getContentType();
//
//        return "redirect:/home";
//
//    }
//}
//
//
