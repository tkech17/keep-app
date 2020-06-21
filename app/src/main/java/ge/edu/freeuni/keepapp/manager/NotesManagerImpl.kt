package ge.edu.freeuni.keepapp.manager

import ge.edu.freeuni.keepapp.model.NoteEntity

class NotesManagerImpl : NotesManager {

    companion object {
        val notes: MutableList<NoteEntity> = ArrayList()
    }

    override fun add(note: NoteEntity) {
        notes.add(note)
    }

    override fun remove(note: NoteEntity) {
        notes.remove(note)
    }

    override fun getAll(): List<NoteEntity> {
        return ArrayList(notes)
    }
}