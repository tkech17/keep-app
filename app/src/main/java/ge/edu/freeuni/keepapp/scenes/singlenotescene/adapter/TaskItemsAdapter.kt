package ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter

interface TaskItemsAdapter {

    fun onItemDelete(taskItem: TaskItem)
    fun onItemCheckUnCheck(taskItem: TaskItem)
    fun setData(taskNames: List<String>)
}