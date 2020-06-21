package ge.edu.freeuni.keepapp.manager

import ge.edu.freeuni.keepapp.model.Note

interface NotesManager {

    fun add(note: Note)

    fun remove(note: Note)

    fun getAll(): List<Note>

}