package ge.edu.freeuni.keepapp.server.manager

import android.content.Context
import ge.edu.freeuni.keepapp.server.database.NoteDAO
import ge.edu.freeuni.keepapp.server.database.NoteDatabase
import ge.edu.freeuni.keepapp.server.database.helpers.toEntity
import ge.edu.freeuni.keepapp.server.database.helpers.toModels
import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.server.model.NoteEntity
import java.util.*

class NotesManagerImpl(appContext: Context) : NotesManager {

    private val noteDAO: NoteDAO = NoteDatabase.getInstance(appContext).getNoteDao()

    override fun add(note: Note) {
        note.createTime = Date()
        note.lastUpdateTime = Date()

        val noteEntity: NoteEntity = toEntity(note)
        if (noteEntity.id == 0) {
            noteDAO.insertNote(noteEntity)
        } else {
            noteDAO.updateNote(noteEntity)
        }

    }

    override fun remove(note: Note) {
        val noteEntity: NoteEntity = toEntity(note)
        noteDAO.deleteNote(noteEntity)
    }

    override fun getCurrentItems(): List<Note> {
        val checkedItems: List<NoteEntity> = noteDAO.getAllNotes().filter { it.pinned }
        return toModels(checkedItems)
    }

    override fun getCheckedItems(): List<Note> {
        val checkedItems: List<NoteEntity> = noteDAO.getAllNotes().filter { !it.pinned }
        return toModels(checkedItems)
    }

}