package ge.edu.freeuni.keepapp.manager

import ge.edu.freeuni.keepapp.helpers.toEntity
import ge.edu.freeuni.keepapp.helpers.toModels
import ge.edu.freeuni.keepapp.model.Note
import ge.edu.freeuni.keepapp.model.NoteEntity
import java.util.*
import kotlin.collections.ArrayList

class NotesManagerImpl : NotesManager {

    companion object {
        val notes: MutableList<NoteEntity> = ArrayList()
        var idGenerator: Int = 1
    }


    override fun add(note: Note) {
        if (note.id == 0) {
            note.id = idGenerator++
        }
        note.createTime = Date()
        note.lastUpdateTime = Date()

        val noteEntity: NoteEntity = toEntity(note)

        notes.removeIf { it.id == note.id }
        notes.add(noteEntity)
    }

    override fun remove(note: Note) {
        val noteEntity: NoteEntity = toEntity(note)

        notes.removeIf { it.id == noteEntity.id }
    }

    override fun getAll(): List<Note> {
        return toModels(notes)
    }
}