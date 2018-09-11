package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.aakimov.nyt.App
import com.aakimov.nyt.R
import com.aakimov.nyt.di.NytViewModelFactory
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_stories.*
import javax.inject.Inject

class StoriesFragment : Fragment(), StoriesView {

    @Inject
    lateinit var viewModeFactory: NytViewModelFactory

    companion object {
        fun newInstance() = StoriesFragment()
    }

    private val eventsSubject = PublishSubject.create<StoriesEvent>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stories, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        App.get().fragmentComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModeFactory).get(StoriesViewModel::class.java)
        viewModel.observeEvents(eventsSubject)

        viewModel.state.observe(this, Observer { state -> render(state!!) })
        button.setOnClickListener {
            eventsSubject.onNext(StoriesEvent.LoadStories)
        }

        Button(activity).setOnClickListener {
            eventsSubject.onNext(StoriesEvent.StoryDetails("!!!"))
        }
    }

    override fun render(state: StoriesViewState) {

    }
}