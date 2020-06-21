package ge.edu.freeuni.keepapp.scenes.noteslistscene.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.model.Note

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var note: Note
    private var titleTextView: TextView = view.findViewById(R.id.note_wrapper_custom_view_title)


    fun setData(note: Note) {
        this.note = note
        updateView()
    }

    private fun updateView() {
        titleTextView.text = note.title
    }

}