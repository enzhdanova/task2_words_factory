package com.example.task.wordsfactory.ui.Utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ItemMeaningWordBinding
import com.example.task.wordsfactory.ui.entity.MeaningUI

class WordMeaningAdapter : ListAdapter<MeaningUI, WordMeaningAdapter.ViewHolder>(DIFF){

    private companion object {
        val DIFF = object  : DiffUtil.ItemCallback<MeaningUI>(){
            override fun areItemsTheSame(oldItem: MeaningUI, newItem: MeaningUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MeaningUI, newItem: MeaningUI): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_meaning_word, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            private val example_color = "#8bbfef"
            private val example_str = "Example: "
        }

        private val binding = ItemMeaningWordBinding.bind(view)

        fun setData(data: MeaningUI) = with(binding) {
            println("bind $data")
            textviewWordMeaningContent.text = data.definition
            if (data.example == "") textviewExample.setVisibility(View.GONE)

            textviewExample.text  = HtmlCompat.fromHtml("<font color='$example_color'>$example_str</font> ${data.example}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}