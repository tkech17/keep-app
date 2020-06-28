package ge.edu.freeuni.keepapp.server.manager

import ge.edu.freeuni.keepapp.server.model.Note

interface NotesManager {

    fun add(note: Note)

    fun remove(note: Note)

    fun getItemsFiltered(pinned: Boolean, title: String?): List<Note>

}