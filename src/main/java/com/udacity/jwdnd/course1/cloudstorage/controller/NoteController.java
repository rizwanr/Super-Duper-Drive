package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;


    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }


    @GetMapping
    public String getHome(Authentication auth, @ModelAttribute("noteForm") Note note, Model model){
        User user =userService.getUser(auth.getName());
        model.addAttribute("notes",this.noteService.getNotes(user.getUserId()));
        return "redirect:/home";
    };

    @PostMapping("add-update-note")
    public String postNewNotes(Authentication auth, @ModelAttribute("noteForm") Note note, Model model){
        Integer queryResult;
        String username = auth.getName();
        String newTitle = note.getNoteTitle();
        String newDescription = note.getNoteDescription();
        Integer noteId = note.getNoteId();
        if (noteId == null){
            queryResult = noteService.addNotes(newTitle, newDescription, username);
        }
        else{
            queryResult = noteService.updateNote(noteId, newTitle, newDescription);
        }

        if (queryResult==1){
        model.addAttribute("successMessage",true);
        model.addAttribute("successMessage", "Your note was added successfully");

        }else{
            model.addAttribute("successMessage",true);
            model.addAttribute("successMessage", "Your note was added successfully");
        }

        Integer userId = userService.getUser(username).getUserId();
        model.addAttribute("notes", noteService.getNotes(userId));

        return "redirect:/result";
    }



    @GetMapping("/delete-note/{noteId}")
    public String removeNote(@PathVariable(value = "noteId") Integer noteId, Authentication auth,  Model model){
    noteService.deleteNote(noteId);
    return "redirect:/result";

    }






}
