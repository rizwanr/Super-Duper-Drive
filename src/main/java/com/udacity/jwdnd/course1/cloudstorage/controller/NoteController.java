package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;


    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }


    @GetMapping
    public String getHome(Authentication auth, @ModelAttribute("noteForm") NoteForm noteForm, Model model){
        User user =userService.getUser(auth.getName());
        model.addAttribute("notes",this.noteService.getNotes(user.getUserId()));
        return "home";
    };

    @PostMapping("add-note")
    public String addNotes(Authentication auth, @ModelAttribute("noteForm") NoteForm noteForm, Model model){
        String username = auth.getName();
        String newTitle = noteForm.getNotetitle();
        String newDescription = noteForm.getNotedescription();
        String noteId = noteForm.getNoteid();
        if (noteId.isEmpty()){
            noteService.addNotes(newTitle, newDescription, username);
        }else{
            noteService.updateNote(Integer.parseInt(noteId), newTitle, newDescription);
        }

        String userName = auth.getName();
        Integer userId = userService.getUser(userName).getUserId();
        model.addAttribute("notes", noteService.getNotes(userId));

        return "home";
    }
}
