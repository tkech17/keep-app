package ge.edu.freeuni.keepapp.manager

import ge.edu.freeuni.keepapp.model.NoteEntity

interface NotesManager {

    fun add(note: NoteEntity)

    fun remove(note: NoteEntity)

    fun getAll(): List<NoteEntity>

}