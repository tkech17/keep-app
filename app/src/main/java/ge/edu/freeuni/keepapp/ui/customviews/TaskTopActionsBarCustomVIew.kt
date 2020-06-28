package ge.edu.freeuni.keepapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.keepapp.R

class TaskTopActionsBarCustomVIew(context: Context?, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var backArrowImageView: ImageView
    private var pinnedImageView: ImageView
    private var pinned: Boolean = false

    init {
        inflate(context, R.layout.single_note_actions_bar, this)

        backArrowImageView = findViewById(R.id.single_note_actions_bar_arrow)
        pinnedImageView = findViewById(R.id.single_note_actions_bar_pinned)
    }

    fun setBackArrowOnClickListener(listener: OnClickListener) {
        backArrowImageView.setOnClickListener(listener)
    }

    fun setPinnedOnClickListener(listener: OnClickListener) {
        pinnedImageView.setOnClickListener(listener)
    }

    fun changePinnedStatus() {
        pinned = !pinned

        val res = when (pinned) {
            true -> R.drawable.pinned
            false -> R.drawable.not_pinned
        }

        pinnedImageView.setImageResource(res)
    }

    fun isPinned(): Boolean {
        return pinned
    }


}