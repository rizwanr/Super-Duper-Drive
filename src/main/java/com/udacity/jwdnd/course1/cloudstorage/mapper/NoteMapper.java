package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES ( notetitle, notedescription, userid) VALUES( #{noteTitle}, #{noteDescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);


    @Select("SELECT * FROM MESSAGES")
    List<Note> getAllNotes();


    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
     List<Note>  getNotesForUser(Integer userId);

    @Delete("DELETE from NOTES WHERE noteid=#{noteId} ")
    void removeNote(Integer noteid);


    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteid = #{noteId}")
    int  updateNote(Integer noteId, String noteTitle, String noteDescription);



}

