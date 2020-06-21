package ge.edu.freeuni.keepapp.scenes.singlenotescene

import ge.edu.freeuni.keepapp.customviews.TaskTopActionsBar
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.TaskItem

interface SingleNote {

    interface View {

        fun goToPreviousFragment()
        fun pinnedStatusChanged(taskTopActionsBar: TaskTopActionsBar)

    }

    interface Presenter {

        fun onBackArrowAction()
        fun onPinUnPinClick(taskTopActionsBar: TaskTopActionsBar)
        fun onItemCheck(taskItem: TaskItem)

    }

}