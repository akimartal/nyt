package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Button

class StoriesFragment : Fragment(), StoriesView {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val btn = Button(activity)
        btn.setOnClickListener { view ->
            viewModel.btnClickIntent()
        }

    }
}