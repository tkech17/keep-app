package ge.edu.freeuni.keepapp.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class TaskCustomView(context: Context?, attr: AttributeSet) : ConstraintLayout(context, attr) {


    init {
        View.inflate(context, R.layout.task_item_custom_view, null)
    }

}