package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper messageMapper, UserMapper userMapper) {
        this.noteMapper = messageMapper;
        this.userMapper= userMapper;
    }


    public void addNotes(String title,String description, String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        Note note = new Note(0, title, description, userId);
        noteMapper.insert(note);
    }

    public void updateNote(Integer noteId, String title, String description) {
        noteMapper.updateNote(noteId, title, description);
    }

    public Note getNotes (Integer userId){
        return  noteMapper.getNote(userId);
    }
}
