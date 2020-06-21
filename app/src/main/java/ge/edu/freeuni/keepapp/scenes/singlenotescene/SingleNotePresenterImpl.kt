package ge.edu.freeuni.keepapp.scenes.singlenotescene

import ge.edu.freeuni.keepapp.customviews.TaskTopActionsBarCustomVIew
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.TaskItem

class SingleNotePresenterImpl(private val singleNoteView: SingleNote.View) : SingleNote.Presenter {

    override fun onBackArrowAction() {
        singleNoteView.goToPreviousFragment()
    }

    override fun onPinUnPinClick(taskTopActionsBar: TaskTopActionsBarCustomVIew) {
        singleNoteView.pinnedStatusChanged(taskTopActionsBar)
    }

    override fun onItemCheck(taskItem: TaskItem) {
        singleNoteView.moveItemToChecked(taskItem)
    }

    override fun onItemRemoveFromCurrent(taskItem: TaskItem) {
        singleNoteView.removeItemFromCurrent(taskItem)
    }

    override fun onItemUnCheck(taskItem: TaskItem) {
        singleNoteView.moveItemFromCheckedToCurrent(taskItem)
    }

    override fun onCheckedItemDelete(taskItem: TaskItem) {
        singleNoteView.removeItemFromChecked(taskItem)
    }
}


