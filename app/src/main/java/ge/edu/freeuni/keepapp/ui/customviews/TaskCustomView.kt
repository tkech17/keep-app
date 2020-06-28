package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class TaskCustomView(context: Context?, attr: AttributeSet) : ConstraintLayout(context, attr) {


    init {
        inflate(context, R.layout.task_item_custom_view, this)
    }
}