package ge.edu.freeuni.keepapp.scenes.singlenotescene

import ge.edu.freeuni.keepapp.customviews.TaskTopActionsBar
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.TaskItem

interface SingleNote {

    interface View {

        fun moveItemToChecked(taskItem: TaskItem)
        fun goToPreviousFragment()
        fun pinnedStatusChanged(taskTopActionsBar: TaskTopActionsBar)
        fun removeItemFromCurrent(taskItem: TaskItem)
        fun removeItemFromChecked(taskItem: TaskItem)
        fun moveItemFromCheckedToCurrent(taskItem: TaskItem)

    }

    interface Presenter {

        fun onBackArrowAction()
        fun onPinUnPinClick(taskTopActionsBar: TaskTopActionsBar)
        fun onItemCheck(taskItem: TaskItem)
        fun onItemRemoveFromCurrent(taskItem: TaskItem)
        fun onItemUnCheck(taskItem: TaskItem)
        fun onCheckedItemDelete(taskItem: TaskItem)

    }

}