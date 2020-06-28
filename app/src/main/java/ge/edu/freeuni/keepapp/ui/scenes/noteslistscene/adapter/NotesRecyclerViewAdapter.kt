package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.ui.scenes.noteslistscene.NotesList

class NotesRecyclerViewAdapter(presenter: NotesList.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_wrapper_custom_view, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task: Note = notes[position]
        val noteHolder: NoteViewHolder = holder as NoteViewHolder
        noteHolder.setData(task)
    }


    fun setData(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)

        this.notifyDataSetChanged()
    }

}
