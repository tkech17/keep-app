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
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.customviews.TaskTopActionsBar
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.CheckedTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.CurrentTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.scenes.singlenotescene.adapter.TaskItem

class SingleNoteFragment : Fragment(), SingleNote.View {

    private lateinit var presenter: SingleNote.Presenter
    private lateinit var taskTopActionsBar: TaskTopActionsBar
    private lateinit var title: EditText
    private lateinit var currentTaskRecyclerViewAdapter: CurrentTasksRecyclerViewAdapter
    private lateinit var checkedTaskRecyclerViewAdapter: CheckedTasksRecyclerViewAdapter
    private lateinit var checkedItemsCountView: TextView

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.single_note_fragment, null)
        presenter = SingleNotePresenterImpl(this, Runnable { });

        title = view.findViewById(R.id.single_note_fragment_title)

        initTaskTopActionsBar(view)
        initCurrentTasksRecyclerView(view)
        initAddItem(view)
        initCheckTasksRecyclerView(view)

        checkedItemsCountView = view.findViewById(R.id.checked_items_custom_view_items_count)
        checkedItemsCountView.text = "${checkedTaskRecyclerViewAdapter.itemCount} Checked items"

        return view
    }

    private fun initCheckTasksRecyclerView(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.single_note_fragment_checked_tasks_recycler_view)

        checkedTaskRecyclerViewAdapter = CheckedTasksRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = checkedTaskRecyclerViewAdapter
        currentTasksRecyclerView.layoutManager = LinearLayoutManager(view.context)

        checkedTaskRecyclerViewAdapter.setData(
            listOf(
                "dddddddd",
                "gggggggg",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
                "ffffffff",
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
    }

    override fun removeItemFromChecked(taskItem: TaskItem) {
        checkedTaskRecyclerViewAdapter.removeItem(taskItem)
    }

    override fun moveItemFromCheckedToCurrent(taskItem: TaskItem) {
        removeItemFromChecked(taskItem)
        currentTaskRecyclerViewAdapter.addSingleTaskItem(taskItem.taskName)
    }

    override fun goToPreviousFragment() {
        findNavController().navigate(R.id.single_note_to_notes_list_action)
    }

    override fun pinnedStatusChanged(taskTopActionsBar: TaskTopActionsBar) {
        taskTopActionsBar.changePinnedStatus()
    }
}