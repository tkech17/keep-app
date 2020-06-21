package ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.scenes.singlenotescene.SingleNote

class CheckedTasksRecyclerViewAdapter(private val presenter: SingleNote.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TaskItemsAdapter {

    private val tasks: MutableList<TaskItem> = mutableListOf()
    private var idGenerator: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item_custom_view, parent, false) as View

        return CurrentTaskItemViewHolder(view, this, true)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun setData(taskNames: List<String>) {
        val taskItems: List<TaskItem> = taskNames.map { TaskItem(id = idGenerator++, taskName = it) }
        this.tasks.addAll(taskItems)

        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        val taskViewHolder: CurrentTaskItemViewHolder = holder as CurrentTaskItemViewHolder
        taskViewHolder.setData(task)
    }

    override fun onItemDelete(taskItem: TaskItem) {
        removeItem(taskItem)
    }

    override fun onItemCheckUnCheck(taskItem: TaskItem) {
        removeItem(taskItem)
        presenter.onItemCheck(taskItem)
    }

    private fun removeItem(taskItem: TaskItem) {
        tasks.removeIf { it.id == taskItem.id }
        this.notifyDataSetChanged()
    }


}