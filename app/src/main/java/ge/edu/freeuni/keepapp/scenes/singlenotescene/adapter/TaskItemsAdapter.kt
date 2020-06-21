package ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter

interface TaskItemsAdapter {

    fun onItemRemove(taskItem: TaskItem)
    fun onItemCheckUnCheck(taskItem: TaskItem)
    fun getTasks(): List<String>

}
