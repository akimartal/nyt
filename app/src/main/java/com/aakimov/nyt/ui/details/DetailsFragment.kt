package com.aakimov.nyt.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aakimov.nyt.App
import com.aakimov.nyt.R
import com.aakimov.nyt.entity.Story
import com.aakimov.nyt.ui.base.BaseFragment
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var glide: RequestManager

    companion object {
        private const val STORY = "STORY"

        fun newInstance(story: Story): Fragment {
            val f = DetailsFragment()
            val args = Bundle()
            args.putParcelable(STORY, story)
            f.arguments = args
            return f
        }
    }

    init {
        App.get().fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val story = getParcelableArg<Story>(STORY)
        title.text = story.title
        text.text = story.abstract
        if (story.multimedia.isNotEmpty()) {
            glide.load(story.multimedia[0].url).into(image)
        }
    }

}