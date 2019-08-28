package com.alxdthn.newstinkoff.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alxdthn.newstinkoff.R
import com.alxdthn.newstinkoff.data.NewsEntity
import com.alxdthn.newstinkoff.network.getDate
import com.alxdthn.newstinkoff.network.html2Text

class MainAdapter(var items: MutableList<NewsEntity>, val callback: Callback) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
			= MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))

	override fun getItemCount() = items.size

	override fun onBindViewHolder(holder: MainHolder, position: Int) {
		holder.bind(items[position])
	}

    fun updateData(new: MutableList<NewsEntity>){
        this.items = new
    }

	inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val newsLine = itemView.findViewById<TextView>(R.id.newsLine)
		private val newsDate = itemView.findViewById<TextView>(R.id.newsDate)

		fun bind(item: NewsEntity) {
			newsLine.text = html2Text(item.text)
			newsDate.text = getDate(
				item.publicationDate,
				"dd/MM/yyyy hh:mm"
			)
			itemView.setOnClickListener {
				if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
			}
		}
	}

	interface Callback {
		fun onItemClicked(item: NewsEntity)
	}
}