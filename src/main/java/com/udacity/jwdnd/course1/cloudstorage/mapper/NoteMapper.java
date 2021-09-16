package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES ( notetitle, notedescription, userid) VALUES( #{notetitle}, #{notedescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);


    @Select("SELECT * FROM MESSAGES")
    List<Note> getAllNotes();


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note  getNote(Integer noteId);

    @Delete("DELETE from NOTES WHERE noteid=#{noteId} ")
    Note removeNote(Integer noteid);


    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    void updateNote(Integer noteId, String title, String description);

}

