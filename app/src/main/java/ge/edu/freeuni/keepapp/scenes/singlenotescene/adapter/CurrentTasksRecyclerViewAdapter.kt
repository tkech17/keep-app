package ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.scenes.singlenotescene.SingleNote

class CurrentTasksRecyclerViewAdapter(private val presenter: SingleNote.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), TaskItemsAdapter {

    private val tasks: MutableList<TaskItem> = mutableListOf()
    private var idGenerator: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item_custom_view, parent, false)
        return CurrentTaskItemViewHolder(view, this, false)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tasks[position]
        val taskViewHolder: CurrentTaskItemViewHolder = holder as CurrentTaskItemViewHolder
        taskViewHolder.setData(task)
    }


    fun setData(taskNames: List<String>) {
        val taskItems: List<TaskItem> = taskNames.map { TaskItem(id = idGenerator++, taskName = it) }
        this.tasks.addAll(taskItems)

        this.notifyDataSetChanged()
    }

    fun addSingleTaskItem(taskName: String) {
        val taskItem: TaskItem = TaskItem(id = idGenerator++, taskName = taskName)
        tasks.add(taskItem)

        this.notifyDataSetChanged()
    }

    override fun onItemRemove(taskItem: TaskItem) {
        presenter.onItemRemoveFromCurrent(taskItem)

    }

    override fun onItemCheckUnCheck(taskItem: TaskItem) {
        presenter.onItemCheck(taskItem)
    }

    override fun getTasks(): List<String> {
        return tasks.map { it.taskName }
    }

    fun removeItem(taskItem: TaskItem) {
        tasks.removeIf { it.id == taskItem.id }
        this.notifyDataSetChanged()
    }

}