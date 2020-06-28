package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ge.edu.freeuni.keepapp.App
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.server.manager.NotesManagerImpl
import ge.edu.freeuni.keepapp.server.model.Note
import ge.edu.freeuni.keepapp.ui.scenes.noteslistscene.adapter.NotesRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotesListFragment : Fragment(), NotesList.View {

    private lateinit var presenter: NotesList.Presenter
    private lateinit var pinnedNotesAdapter: NotesRecyclerViewAdapter
    private lateinit var unPinnedNotesAdapter: NotesRecyclerViewAdapter

    private lateinit var pinnedTextView: TextView
    private lateinit var unPinnedTestView: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.notesManager = NotesManagerImpl(context)
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.notes_list_fragment, null)
        presenter = NotesListPresenterImpl()

        pinnedTextView = view.findViewById(R.id.notes_list_fragment_pinned_text_view)
        unPinnedTestView = view.findViewById(R.id.notes_list_fragment_unpinned_text_view)

        initPinnedTask(view)
        initOtherTasks(view)
        initNextTaskTextView(view)

        return view
    }

    private fun initPinnedTask(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_pinned_tasks_recycler_view)

        pinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = pinnedNotesAdapter
        currentTasksRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val checkedNotes: MutableList<Note> = ArrayList()

        runBlocking {
            lifecycleScope.launch(Dispatchers.IO) {
                checkedNotes.addAll(App.notesManager.getCheckedItems())
            }
        }

        if (checkedNotes.isEmpty()) {
            pinnedTextView.visibility = View.GONE
        } else {
            pinnedTextView.visibility = View.VISIBLE
        }

        pinnedNotesAdapter.setData(checkedNotes)
    }

    private fun initOtherTasks(view: View) {
        val unpinnedRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_unpinned_items_recycler_view)

        unPinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        unpinnedRecyclerView.adapter = unPinnedNotesAdapter
        unpinnedRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val currentNotes: MutableList<Note> = ArrayList()

        runBlocking {
            lifecycleScope.launch(Dispatchers.IO) {
                currentNotes.addAll(App.notesManager.getCurrentItems())
            }
        }

        currentNotes.add(
            Note(
                pinned = false,
                title = "ASD",
                currentTasks = listOf("a", "b", "c"),
                checkedTasks = listOf("a", "b", "c")
            )
        )


        if (currentNotes.isEmpty()) {
            unPinnedTestView.visibility = View.GONE
        } else {
            unPinnedTestView.visibility = View.VISIBLE
        }

        unPinnedNotesAdapter.setData(currentNotes)
    }

    private fun initNextTaskTextView(view: View) {
        val newTaskTextView: TextView = view.findViewById(R.id.notes_list_fragment_take_new_note_text_view)

        newTaskTextView.setOnClickListener {
            findNavController()
                .navigate(R.id.note_list_to_single_note)
        }
    }

}