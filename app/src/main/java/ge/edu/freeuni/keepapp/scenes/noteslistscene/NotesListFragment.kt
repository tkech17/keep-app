package ge.edu.freeuni.keepapp.scenes.noteslistscene

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.freeuni.keepapp.R
import ge.edu.freeuni.keepapp.model.Note
import ge.edu.freeuni.keepapp.scenes.noteslistscene.adapter.NotesRecyclerViewAdapter

class NotesListFragment : Fragment(), NotesList.View {

    private lateinit var presenter: NotesList.Presenter
    private lateinit var pinnedNotesAdapter: NotesRecyclerViewAdapter
    private lateinit var unPinnedNotesAdapter: NotesRecyclerViewAdapter

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.notes_list_fragment, null)
        presenter = NotesListPresenterImpl()

        initPinnedTask(view)
        initOtherTasks(view)
        initNextTastTextView(view)

        return view
    }

    private fun initPinnedTask(view: View) {
        val currentTasksRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_pinned_tasks_recycler_view)

        pinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        currentTasksRecyclerView.adapter = pinnedNotesAdapter
        currentTasksRecyclerView.layoutManager = GridLayoutManager(view.context, 2)

        pinnedNotesAdapter.setData(
            listOf(
                Note(
                    title = "DDD",
                    pinned = true
                ),
                Note(
                    title = "EEE",
                    pinned = true
                ),
                Note(
                    title = "FFF",
                    pinned = true
                )
            )
        )
    }

    private fun initOtherTasks(view: View) {
        val unpinnedRecyclerView: RecyclerView = view.findViewById(R.id.notes_list_fragment_unpinned_items_recycler_view)

        unPinnedNotesAdapter = NotesRecyclerViewAdapter(presenter)

        unpinnedRecyclerView.adapter = unPinnedNotesAdapter
        unpinnedRecyclerView.layoutManager = GridLayoutManager(view.context, 2)

        unPinnedNotesAdapter.setData(
            listOf(
                Note(
                    title = "AAA",
                    pinned = true
                ),
                Note(
                    title = "BBB",
                    pinned = true
                ),
                Note(
                    title = "CCC",
                    pinned = true
                )
            )
        )
    }

    private fun initNextTastTextView(view: View) {
        val newTaskTextView: TextView = view.findViewById(R.id.notes_list_fragment_take_new_note_text_view)

        newTaskTextView.setOnClickListener {
            findNavController()
                .navigate(R.id.note_list_to_single_note)
        }
    }

}