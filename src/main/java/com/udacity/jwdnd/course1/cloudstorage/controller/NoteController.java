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
@RequestMapping("note")
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
    public String addNotes(Authentication auth, @ModelAttribute("noteForm") Note note, Model model){
        String username = auth.getName();
        String newTitle = note.getNoteTitle();
        String newDescription = note.getNoteDescription();
        Integer noteId = note.getNoteId();
        if (noteId == null){
            noteService.addNotes(newTitle, newDescription, username);
        }
        else{
            noteService.updateNote(noteId, newTitle, newDescription);
        }

        Integer userId = userService.getUser(username).getUserId();
        model.addAttribute("notes", noteService.getNotes(userId));

        return "redirect:/home";
    }

//
//    @PostMapping("/edit-note/{noteId}")
//    public String updateNote(@PathVariable(value = "noteId") Integer noteId, Authentication auth, @ModelAttribute("note") Note note, Model model){
//        System.out.println("Edit button called");
//        String username = auth.getName();
//        String updateTitle = note.getNoteTitle();
//        String updateDescription = note.getNoteDescription();
//        noteService.updateNote(noteId,updateTitle, updateDescription);
//        Integer userId = userService.getUser(username).getUserId();
//        model.addAttribute("notes", noteService.getNotes(userId));
//        return "redirect:/home";
//    }


    @GetMapping("/delete-note/{noteId}")
    public String removeNote(@PathVariable(value = "noteId") Integer noteId, Authentication auth,  Model model){
    noteService.deleteNote(noteId);
    return "redirect:/home";

    }






}
