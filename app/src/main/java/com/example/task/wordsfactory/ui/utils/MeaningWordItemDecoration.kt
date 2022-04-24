package com.example.task.wordsfactory.ui.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.task.wordsfactory.R

class MeaningWordItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private val margin4 = context.resources.getDimensionPixelSize(R.dimen.margin_4)
    private val margin16 = context.resources.getDimensionPixelSize(R.dimen.margin_16)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val newRect = when(position) {
            0 -> Rect(margin16, margin16, margin16, margin4)
            state.itemCount - 1 -> Rect(margin16, margin4, margin16, margin16)
            else -> Rect(margin16, margin4, margin16, margin4)
        }

        outRect.apply {
            left = newRect.left
            right = newRect.right
            top = newRect.top
            bottom = newRect.bottom
        }
    }
}