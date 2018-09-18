package com.aakimov.nyt.ui.stories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.aakimov.nyt.R
import com.aakimov.nyt.entity.Story
import com.bumptech.glide.RequestManager


class StoriesAdapter constructor(val glide: RequestManager)
    : RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {

    var items: List<Story> = emptyList()
    private var listener: ((Story) -> Unit)? = null

    fun setStories(items: List<Story>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): StoriesAdapter.StoryViewHolder {
        val vh = StoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout
                .story_list_item, parent, false))
        vh.setOnClickListener(listener)
        return vh
    }

    override fun onBindViewHolder(vh: StoriesAdapter.StoryViewHolder, pos: Int) {
        vh.title.text = items[pos].title
        if (items[pos].multimedia.isNotEmpty()) {
            glide.load(items[pos].multimedia[0].url).into(vh.image)
        } else {
            glide.load(R.drawable.ic_menu_camera).into(vh.image)
        }
    }

    fun setItemClickedListener(listener: (Story) -> Unit) {
        this.listener = listener
    }

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setOnClickListener(function: ((Story) -> Unit)?) {
            itemView.setOnClickListener { function?.invoke(items[layoutPosition]) }
        }

        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)
    }
}
