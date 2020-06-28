package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class AddItemCustomView(context: Context?, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var plusImageView: ImageView
    private var taskNameView: EditText

    init {
        inflate(context, R.layout.add_item_custom_view, this)
        plusImageView = findViewById(R.id.add_item_custom_view_add_image)
        taskNameView = findViewById(R.id.add_item_custom_view_task_name)
    }

    fun setPlusImageClickListener(listener: OnClickListener) {
        plusImageView.setOnClickListener(listener)
    }

    fun getTextAndClear(): String {
        val taskName = taskNameView.text.toString()
        taskNameView.text.clear()
        return taskName
    }

}