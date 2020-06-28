package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class TaskCustomViewReadOnly (context: Context?, attr: AttributeSet) : ConstraintLayout(context, attr) {

    private lateinit var taskName: TextView

    init {
        inflate(context, R.layout.task_item_custom_view_read_only, this)
        taskName = findViewById(R.id.task_item_custom_view_read_only_task_name)

        visibility = View.INVISIBLE
    }

    fun setTaskName(taskName: String) {
        this.taskName.text = taskName
        visibility = View.VISIBLE
    }

}