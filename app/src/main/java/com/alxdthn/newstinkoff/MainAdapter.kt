package com.alxdthn.newstinkoff

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainAdapter(var items: List<Payload>, val callback: Callback) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val newsText = itemView.findViewById<TextView>(R.id.newsLine)

        fun bind(item: Payload) {
            newsText.text = item.text
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Payload)
    }
}