package ge.edu.freeuni.keepapp.ui.scenes.noteslistscene

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
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


    private lateinit var searchBar: EditText
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
        presenter = NotesListPresenterImpl(this)

        searchBar = view.findViewById(R.id.notes_list_fragment_search_text_view)
        pinnedTextView = view.findViewById(R.id.notes_list_fragment_pinned_text_view)
        unPinnedTestView = view.findViewById(R.id.notes_list_fragment_unpinned_text_view)

        initSearchBar()

        initPinnedTask(view)
        initOtherTasks(view)
        initNextTaskTextView(view)
        setData()


        return view
    }

    private fun initSearchBar() {
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setData()
            }
        })
    }

    private fun setData() {
        val checkedNotes: MutableList<Note> = ArrayList()

        runBlocking {
            lifecycleScope.launch(Dispatchers.IO) {
                val checked: List<Note> = App.notesManager.getItemsFiltered(true, getSearchText())
                checkedNotes.addAll(checked)
            }.join()
        }

        if (checkedNotes.isEmpty()) {
            pinnedTextView.visibility = View.GONE
        } else {
            pinnedTextView.visibility = View.VISIBLE
        }

        pinnedNotesAdapter.setData(checkedNotes)


        //--------------------------------//

        val currentNotes: MutableList<Note> = ArrayList()

        runBlocking {
            lifecycleScope.launch(Dispatchers.IO) {
                val curr: List<Note> = App.notesManager.getItemsFiltered(false, getSearchText())
                println("size = ${curr.size}")
                currentNotes.addAll(curr)
            }.join()
        }

        println(currentNotes.size)
        if (currentNotes.isEmpty()) {
            unPinnedTestView.visibility = View.GONE
        } else {
            unPinnedTestView.visibility = View.VISIBLE
        }

        unPinnedNotesAdapter.setData(currentNotes)
    }

    private fun getSearchText() = searchBar.text.toString()

    private fun initPinnedTask(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_pinned_tasks_recycler_view)

        pinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = pinnedNotesAdapter
        currentTasksRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


    }

    private fun initOtherTasks(view: View) {
        val unpinnedRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_unpinned_items_recycler_view)

        unPinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        unpinnedRecyclerView.adapter = unPinnedNotesAdapter
        unpinnedRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }


    override fun moveToTask(task: Note) {
        moveToSingleNoteFragment(task)
    }

    private fun initNextTaskTextView(view: View) {
        val newTaskTextView: TextView = view.findViewById(R.id.notes_list_fragment_take_new_note_text_view)

        newTaskTextView.setOnClickListener {
            moveToSingleNoteFragment(null)
        }
    }

    private fun moveToSingleNoteFragment(note: Note?) {
        findNavController()
            .navigate(R.id.note_list_to_single_note, bundleOf("data" to note))
    }

}