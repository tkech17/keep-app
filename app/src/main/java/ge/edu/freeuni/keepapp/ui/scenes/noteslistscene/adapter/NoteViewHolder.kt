package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.ui.customviews.TaskCustomViewReadOnly

class NoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var note: Note
    private var titleTextView: TextView = view.findViewById(R.id.note_wrapper_custom_view_title)


    fun setData(note: Note) {
        this.note = note
        updateView()
    }

    @SuppressLint("SetTextI18n")
    private fun updateView() {
        titleTextView.text = note.title
        for (i in 0..3) {
            if (note.currentTasks.size <= i) {
                break
            }
            when (i) {
                0 -> drawTaskName(note.currentTasks[i], R.id.note_wrapper_custom_view_task_1)
                1 -> drawTaskName(note.currentTasks[i], R.id.note_wrapper_custom_view_task_2)
                2 -> drawTaskName(note.currentTasks[i], R.id.note_wrapper_custom_view_task_3)
                3 -> drawTaskName(note.currentTasks[i], R.id.note_wrapper_custom_view_task_4)
            }
        }

        if(note.checkedTasks.isNotEmpty()) {
            val checkedItemsCount:TextView = view.findViewById(R.id.note_wrapper_custom_view_checked_items_count)
            checkedItemsCount.text = "+ ${note.checkedTasks.size} checked items"
        }

    }

    private fun drawTaskName(task: String, taskTextFieldId: Int) {
        val taskTextView: TaskCustomViewReadOnly = view.findViewById(taskTextFieldId)
        taskTextView.setTaskName(task)
    }

}