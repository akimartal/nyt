package com.aakimov.nyt.ui.stories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.aakimov.nyt.R
import com.aakimov.nyt.entity.Story


class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {
    private var items: List<Story> = emptyList()

    fun setItems(items: List<Story>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): StoriesAdapter.StoryViewHolder {
        return StoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout
                .story_list_item,
                parent, false))
    }

    override fun onBindViewHolder(vh: StoriesAdapter.StoryViewHolder, pos: Int) {
        vh.title.text = items[pos].story!!.title
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)

    }
}
