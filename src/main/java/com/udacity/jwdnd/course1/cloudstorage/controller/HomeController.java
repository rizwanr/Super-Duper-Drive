package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    private UserService userService;
    private NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping()
    public String getHome(Authentication auth, Model model){
     User user =userService.getUser(auth.getName());
     model.addAttribute("notes",this.noteService.getNotes(user.getUserId()));
     return "home";

    };






}
