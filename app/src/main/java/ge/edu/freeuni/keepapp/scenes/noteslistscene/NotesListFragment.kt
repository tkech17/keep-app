package ge.edu.freeuni.keepapp.scenes.noteslistscene

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ge.edu.freeuni.keepapp.R

class NotesListFragment : Fragment() {

    companion object {

        fun newInstance(): NotesListFragment {
            return NotesListFragment()
        }

    }


    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_list_fragment, null)
        val textView: TextView = view.findViewById(R.id.notes_list_fragment_take_new_note_text_view)
        textView.setOnClickListener {
            findNavController()
                .navigate(R.id.note_list_to_single_note)
        }
        return view
    }

}