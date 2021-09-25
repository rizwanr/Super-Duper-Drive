package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper messageMapper, UserMapper userMapper) {
        this.noteMapper = messageMapper;
        this.userMapper= userMapper;
    }


    public int addNotes(String title,String description, String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        Note note = new Note(1, title, description, userId);
        return noteMapper.insert(note);
    }

    public int updateNote(Integer noteId, String title, String description) {
       return noteMapper.updateNote(noteId, title, description);
    }

    public void deleteNote(Integer noteId){
        noteMapper.removeNote(noteId);
    }



    public List<Note> getNotes (Integer userId){
        return  noteMapper.getNotesForUser(userId);
    }
}
