package com.example.task.wordsfactory.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ItemMeaningWordBinding
import com.example.task.wordsfactory.data.model.Meaning

class WordMeaningAdapter : ListAdapter<Meaning, WordMeaningAdapter.ViewHolder>(DIFF){

    private companion object {
        val DIFF = object  : DiffUtil.ItemCallback<Meaning>(){
            override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean =
                oldItem.definition == newItem.definition

            override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean =
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

        private val binding = ItemMeaningWordBinding.bind(view)

        fun setData(data: Meaning) = with(binding) {
            textviewWordMeaningContent.text = data.definition
            if (data.example.isEmpty()) textviewExample.visibility = View.GONE

            val color = itemView.context.getColor(R.color.light_blue)
            val example_str = itemView.context.getString(R.string.example)
            textviewExample.text  = HtmlCompat.fromHtml("<font color='$color'>$example_str</font> ${data.example}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}