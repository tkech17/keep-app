package ge.edu.freeuni.keepapp.server.database

import androidx.room.*
import ge.edu.freeuni.keepapp.server.model.NoteEntity

@Dao
interface NoteDAO {

    @Query("SELECT * FROM Note n WHERE n.pinned = :pinned AND n.title LIKE '%' || :title ||  '%' ")
    fun filterNotes(pinned: Boolean, title: String): List<NoteEntity>

    @Insert
    fun insertNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

}