package ge.edu.freeuni.keepapp.scenes.singlenotescene

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.App
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.customviews.CheckedItemsCustomView
import ge.edu.freeuni.keepapp.customviews.TaskTopActionsBarCustomVIew
import ge.edu.freeuni.keepapp.model.Note
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.CheckedTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.CurrentTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.TaskItem

class SingleNoteFragment : Fragment(), SingleNote.View {

    private lateinit var presenter: SingleNote.Presenter
    private lateinit var taskTopActionsBar: TaskTopActionsBarCustomVIew
    private lateinit var title: EditText
    private lateinit var currentTaskRecyclerViewAdapter: CurrentTasksRecyclerViewAdapter
    private lateinit var checkedTaskRecyclerViewAdapter: CheckedTasksRecyclerViewAdapter
    private lateinit var checkedItemsCountView: TextView
    private lateinit var checkedItemsCountViewWrapper: CheckedItemsCustomView

    private val noteId: Int = 0

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.single_note_fragment, null)
        presenter = SingleNotePresenterImpl(this);

        title = view.findViewById(R.id.single_note_fragment_title)

        initTaskTopActionsBar(view)
        initCurrentTasksRecyclerView(view)
        initAddItem(view)
        initCheckTasksRecyclerView(view)

        checkedItemsCountView = view.findViewById(R.id.checked_items_custom_view_items_count)
        checkedItemsCountViewWrapper = view.findViewById(R.id.single_note_fragment_checked_items_count)
        updateCheckedItemsCount()

        return view
    }

    private fun updateCheckedItemsCount() {
        if (checkedTaskRecyclerViewAdapter.itemCount != 0) {
            checkedItemsCountView.text = "${checkedTaskRecyclerViewAdapter.itemCount} Checked items"
            checkedItemsCountViewWrapper.visibility = View.VISIBLE
        } else {
            checkedItemsCountViewWrapper.visibility = View.INVISIBLE
        }
    }

    private fun initCheckTasksRecyclerView(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.single_note_fragment_checked_tasks_recycler_view)

        checkedTaskRecyclerViewAdapter = CheckedTasksRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = checkedTaskRecyclerViewAdapter
        currentTasksRecyclerView.layoutManager = LinearLayoutManager(view.context)

        checkedTaskRecyclerViewAdapter.setData(
            listOf(
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff"
            )
        )

    }

    private fun initAddItem(view: View?) {

    }

    private fun initCurrentTasksRecyclerView(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.single_note_fragment_current_tasks_recycler_view)

        currentTaskRecyclerViewAdapter = CurrentTasksRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = currentTaskRecyclerViewAdapter
        currentTasksRecyclerView.layoutManager = LinearLayoutManager(view.context)

        currentTaskRecyclerViewAdapter.setData(
            listOf(
                "aaaaaaaa",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "bbbbbbbb",
                "cccccccc"
            )
        )

    }


    private fun initTaskTopActionsBar(view: View) {
        taskTopActionsBar = view.findViewById(R.id.single_note_fragment_search_bar_view)
        taskTopActionsBar.setBackArrowOnClickListener(
            View.OnClickListener { presenter.onBackArrowAction() }
        )
        taskTopActionsBar.setPinnedOnClickListener(
            View.OnClickListener { presenter.onPinUnPinClick(taskTopActionsBar) }
        )
    }

    override fun removeItemFromCurrent(taskItem: TaskItem) {
        currentTaskRecyclerViewAdapter.removeItem(taskItem)
    }

    override fun moveItemToChecked(taskItem: TaskItem) {
        removeItemFromCurrent(taskItem)
        checkedTaskRecyclerViewAdapter.addSingleTaskItem(taskItem.taskName)
        updateCheckedItemsCount()
    }

    override fun removeItemFromChecked(taskItem: TaskItem) {
        checkedTaskRecyclerViewAdapter.removeItem(taskItem)
        updateCheckedItemsCount()
    }

    override fun moveItemFromCheckedToCurrent(taskItem: TaskItem) {
        removeItemFromChecked(taskItem)
        currentTaskRecyclerViewAdapter.addSingleTaskItem(taskItem.taskName)
        updateCheckedItemsCount()
    }

    override fun goToPreviousFragment() {
        App.notesManager.add(createNote())
        println(App.notesManager.getAll().size)
        findNavController().navigate(R.id.single_note_to_notes_list_action)
    }

    private fun createNote(): Note {
        return Note(
            id = noteId,
            title = title.text.toString(),
            currentTasks = currentTaskRecyclerViewAdapter.getTasks(),
            checkedTasks = checkedTaskRecyclerViewAdapter.getTasks(),
            pinned = taskTopActionsBar.isPinned()
        )
    }

    override fun pinnedStatusChanged(taskTopActionsBar: TaskTopActionsBarCustomVIew) {
        taskTopActionsBar.changePinnedStatus()
    }
}