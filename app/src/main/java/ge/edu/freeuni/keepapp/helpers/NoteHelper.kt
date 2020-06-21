package ge.edu.freeuni.keepapp.helpers

import ge.edu.freeuni.keepapp.model.Note
import ge.edu.freeuni.keepapp.model.NoteEntity

fun toEntity(note: Note): NoteEntity {
    return NoteEntity(
        id = note.id,
        title = note.title,
        currentTasks = note.currentTasks,
        checkedTasks = note.checkedTasks,
        createTime = note.createTime,
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
        createTime = noteEntity.createTime,
        lastUpdateTime = noteEntity.lastUpdateTime,
        pinned = noteEntity.pinned
    )
}