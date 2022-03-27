package com.example.task.wordsfactory.ui.Utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.task.wordsfactory.R

class MeaningWordItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val margin4 = parent.context.resources.getDimensionPixelSize(R.dimen.margin_4)
        val margin16 = parent.context.resources.getDimensionPixelSize(R.dimen.margin_16)
        val margin20 = parent.context.resources.getDimensionPixelSize(R.dimen.margin_16)


        val newRect = when(position) {
            0 -> Rect(margin20, margin16, margin20, margin4)
            state.itemCount - 1 -> Rect(margin20, margin4, margin20, margin16)
            else -> Rect(margin20, margin4, margin20, margin4)
        }

        outRect.apply {
            left = newRect.left
            right = newRect.right
            top = newRect.top
            bottom = newRect.bottom
        }
    }
}