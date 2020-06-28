package ge.edu.freeuni.keepapp.ui.scenes.singlenotescene

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.App
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.ui.customviews.AddItemCustomView
import ge.edu.freeuni.keepapp.ui.customviews.CheckedItemsCustomView
import ge.edu.freeuni.keepapp.ui.customviews.TaskTopActionsBarCustomVIew
import ge.edu.freeuni.keepapp.ui.scenes.singlenotescene.adapter.CheckedTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.ui.scenes.singlenotescene.adapter.CurrentTasksRecyclerViewAdapter
import ge.edu.freeuni.keepapp.ui.scenes.singlenotescene.adapter.TaskItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SingleNoteFragment : Fragment(), SingleNote.View {

    private lateinit var presenter: SingleNote.Presenter
    private lateinit var taskTopActionsBar: TaskTopActionsBarCustomVIew
    private lateinit var title: EditText
    private lateinit var currentTaskRecyclerViewAdapter: CurrentTasksRecyclerViewAdapter
    private lateinit var checkedTaskRecyclerViewAdapter: CheckedTasksRecyclerViewAdapter
    private lateinit var checkedItemsCountView: TextView
    private lateinit var checkedItemsCountViewWrapper: CheckedItemsCustomView
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = arguments?.get("data") as Note?
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.single_note_fragment, null)
        presenter = SingleNotePresenterImpl(this);

        title = view.findViewById(R.id.single_note_fragment_title)
        note?.let { title.setText(it.title) }


        initTaskTopActionsBar(view)
        initCurrentTasksRecyclerView(view)
        initAddItem(view)
        initCheckTasksRecyclerView(view)

        checkedItemsCountView = view.findViewById(R.id.checked_items_custom_view_items_count)
        checkedItemsCountViewWrapper = view.findViewById(R.id.single_note_fragment_checked_items_count)

        updateCheckedItemsCount()

        return view
    }

    @SuppressLint("SetTextI18n")
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

        note?.let {
            checkedTaskRecyclerViewAdapter.setData(it.checkedTasks)
        }
    }

    private fun initAddItem(view: View) {
        val addItem: AddItemCustomView = view.findViewById(R.id.single_note_fragment_add_items)

        addItem.setPlusImageClickListener(
            View.OnClickListener { currentTaskRecyclerViewAdapter.addSingleTaskItem(addItem.getTextAndClear()) }
        )
    }

    private fun initCurrentTasksRecyclerView(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.single_note_fragment_current_tasks_recycler_view)

        currentTaskRecyclerViewAdapter = CurrentTasksRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = currentTaskRecyclerViewAdapter
        currentTasksRecyclerView.layoutManager = LinearLayoutManager(view.context)

        note?.let {
            currentTaskRecyclerViewAdapter.setData(it.currentTasks)
        }
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
        runBlocking {
            lifecycleScope.launch(Dispatchers.IO) {
                val userNote: Note = createNote()
                if (userNote.id != 0 && userNote.currentTasks.isEmpty()) {
                    App.notesManager.remove(userNote)
                } else if (userNote.id == 0 && userNote.currentTasks.isNotEmpty()) {
                    App.notesManager.add(userNote)
                }
            }.join()
        }
        findNavController().navigate(R.id.single_note_to_notes_list_action)
    }

    private fun createNote(): Note {
        val noteToSave = Note(
            title = title.text.toString(),
            currentTasks = currentTaskRecyclerViewAdapter.getTasks(),
            checkedTasks = checkedTaskRecyclerViewAdapter.getTasks(),
            pinned = taskTopActionsBar.isPinned()
        )
        note?.let { noteToSave.id = it.id }
        return noteToSave
    }

    override fun pinnedStatusChanged(taskTopActionsBar: TaskTopActionsBarCustomVIew) {
        taskTopActionsBar.changePinnedStatus()
    }
}