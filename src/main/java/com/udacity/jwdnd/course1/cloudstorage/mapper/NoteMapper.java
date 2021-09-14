package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (noteid, notetitle, notedescription, userid) VALUES(#{noteid}, #{notetitle}, #{notedescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Select("SELECT * FROM MESSAGES where userid=#{userid}")
    List<Note> getNotesFromUser(Integer userid);


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note  getNote(Integer noteid);

    @Delete("DELETE from NOTES WHERE noteid=#{noteid} ")
    Note removeNote(Integer noteid);

    @Update("Update  NOTES set notetitle = #{notetitle} and notedescription WHERE noteid=#{noteid} ")
    Note updateNote(Integer noteid);
}

