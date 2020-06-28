package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene

import ge.edu.freeuni.keepapp.server.model.Note

class NotesListPresenterImpl(private val notesListView: NotesList.View) : NotesList.Presenter {

    override fun onNoteClick(note: Note) {
        notesListView.moveToTask(note)
    }

}