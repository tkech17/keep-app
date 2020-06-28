package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class CheckedItemsCustomView(context: Context?, attrs: AttributeSet) : ConstraintLayout(context, attrs) {


    init {
        inflate(context, R.layout.checked_items_custom_view, this)
    }

}