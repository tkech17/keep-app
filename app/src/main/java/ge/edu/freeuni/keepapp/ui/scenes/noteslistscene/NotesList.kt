package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene

import ge.edu.freeuni.keepapp.server.model.Note

interface NotesList {

    interface View {
        fun moveToTask(task: Note)

    }

    interface Presenter {
        fun onNoteClick(note: Note)
    }

}