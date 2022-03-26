package com.example.task.wordsfactory.ui.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.MockeWord
import com.example.task.wordsfactory.databinding.ItemMeaningWordBinding
import com.example.task.wordsfactory.ui.entity.Meaning

class WordMeaningAdapter() : RecyclerView.Adapter<WordMeaningAdapter.ViewHolder>() {

    private val items = mutableListOf<Meaning>()

    init {
        println("MyApp: init adapter")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        println("MyApp: onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder")
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_meaning_word, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("MyApp: bind ${items[position]}")
        holder.setData(items[position])
    }

    override fun getItemCount(): Int {
        println("MyApp: getItemCount(): Int")

        return items.size
    }

    fun setItems(items: List<Meaning>) {
        println("MyApp: setItems $items")
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            private val example_color = "#8bbfef"
            private val example_str = "Example: "
        }

        private val binding = ItemMeaningWordBinding.bind(view)

        fun setData(data: Meaning) = with(binding) {
            println("bind $data")
            textviewWordMeaningContent.text = data.definition
            textviewExample.text  = HtmlCompat.fromHtml("<font color='${example_color}'>$example_str</font> ${data.example}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

}