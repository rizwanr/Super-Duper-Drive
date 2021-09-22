package com.udacity.jwdnd.course1.cloudstorage.controller;

//import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private EncryptionService encryptionService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService,UserService userService, FileService fileService, CredentialService credentialService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService=fileService;
        this.encryptionService = encryptionService;
        this.credentialService =credentialService;
    }


    @GetMapping()
    public String getHome(Authentication auth, @ModelAttribute("noteForm") Note note , @ModelAttribute("file") File file, @ModelAttribute("credentials") Credential credential,
                          @ModelAttribute("encryptionservice") EncryptionService encryptionService, Model model){
     User user =userService.getUser(auth.getName());
     model.addAttribute("files", this.fileService.getListOfFilesForUsers(user.getUserId()));
     model.addAttribute("notes",this.noteService.getNotes(user.getUserId()));
     model.addAttribute("encryptionService",encryptionService);
     model.addAttribute("credentials",this.credentialService.getCredentials(user.getUserId()));
     return "home";

    };






}
