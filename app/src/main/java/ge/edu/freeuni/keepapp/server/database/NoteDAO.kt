package ge.edu.freeuni.keepapp.server.database

import androidx.room.*
import ge.edu.freeuni.keepapp.server.model.NoteEntity

@Dao
interface NoteDAO {

    @Query("SELECT * FROM Note")
    fun getAllNotes(): List<NoteEntity>

    @Insert
    fun insertNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

}