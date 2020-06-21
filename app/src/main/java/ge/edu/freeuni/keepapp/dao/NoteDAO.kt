package ge.edu.freeuni.keepapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ge.edu.freeuni.keepapp.model.NoteEntity

@Dao
interface NoteDAO {

    @Query("SELECT * FROM Note")
    fun getAllNotes(): List<NoteEntity>

    @Insert
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

}