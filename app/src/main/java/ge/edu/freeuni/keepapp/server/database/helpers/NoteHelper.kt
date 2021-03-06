package ge.edu.freeuni.keepapp.server.database.helpers

import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.server.model.NoteEntity

fun toEntity(note: Note): NoteEntity {
    return NoteEntity(
        id = note.id,
        title = note.title,
        currentTasks = ArrayList(note.currentTasks),
        checkedTasks = ArrayList(note.checkedTasks),
        lastUpdateTime = note.lastUpdateTime,
        pinned = note.pinned
    )
}

fun toModels(noteEntities: List<NoteEntity>): List<Note> {
    return noteEntities.map { toModel(it) }
}

private fun toModel(noteEntity: NoteEntity): Note {
    return Note(
        id = noteEntity.id,
        title = noteEntity.title,
        currentTasks = noteEntity.currentTasks,
        checkedTasks = noteEntity.checkedTasks,
        lastUpdateTime = noteEntity.lastUpdateTime,
        pinned = noteEntity.pinned
    )
}