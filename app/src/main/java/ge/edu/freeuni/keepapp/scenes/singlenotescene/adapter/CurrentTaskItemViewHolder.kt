package ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R

class CurrentTaskItemViewHolder(view: View, private val taskItemsAdapter: TaskItemsAdapter, isChecked: Boolean) : RecyclerView.ViewHolder(view) {

    private var deleteImage: ImageView = view.findViewById(R.id.task_item_custom_view_task_delete)
    private var checkBox: CheckBox = view.findViewById(R.id.task_item_custom_view_check_box)
    private val taskName: EditText = view.findViewById(R.id.task_item_custom_view_task_name)
    private lateinit var taskItem: TaskItem

    init {
        checkBox.isChecked = isChecked

        checkBox.setOnClickListener { taskItemsAdapter.onItemCheckUnCheck(taskItem) }
        deleteImage.setOnClickListener { taskItemsAdapter.onItemDelete(taskItem) }
    }

    fun setData(taskItem: TaskItem) {
        taskName.setText(taskItem.taskName)
        this.taskItem = taskItem
    }

}