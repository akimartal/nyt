package com.aakimov.nyt.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aakimov.nyt.R
import com.aakimov.nyt.ui.base.BaseFragment
import com.aakimov.nyt.ui.stories.StoriesFragment

class DetailsFragment : BaseFragment() {

    companion object {
        private const val STORY_ID = "STORY_ID"

        fun newInstance(storyId: String): Fragment {
            val f = DetailsFragment()
            val args = Bundle()
            args.putString(STORY_ID, storyId)
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view
    }
}