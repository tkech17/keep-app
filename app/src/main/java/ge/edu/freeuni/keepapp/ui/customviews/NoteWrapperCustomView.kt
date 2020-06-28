package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class NoteWrapperCustomView(context: Context?, attr: AttributeSet) : ConstraintLayout(context, attr) {

    init {
        inflate(context, R.layout.note_wrapper_custom_view, this)
    }

}