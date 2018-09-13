package com.aakimov.nyt.ui.stories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aakimov.nyt.App
import com.aakimov.nyt.R
import com.aakimov.nyt.di.NytViewModelFactory
import com.aakimov.nyt.ui.base.BaseFragment
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_stories.*
import javax.inject.Inject


class StoriesFragment : BaseFragment(), StoriesView {

    @Inject
    lateinit var viewModeFactory: NytViewModelFactory

    @Inject
    lateinit var adapter: StoriesAdapter

    companion object {
        private const val TOPIC_KEY = "TOPIC_KEY"

        fun newInstance(topic: String): Fragment {
            val f = StoriesFragment()
            val args = Bundle()
            args.putString(TOPIC_KEY, topic)
            f.arguments = args
            return f
        }
    }

    private val loadStoriesSubject = PublishSubject.create<String>()

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
        viewModel.observeLoadStories(loadStoriesSubject)

        viewModel.state.observe(this, Observer { state -> render(state!!) })
        loadStoriesSubject.onNext(getStringArg(TOPIC_KEY))
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        refresh.setOnRefreshListener {
            loadStoriesSubject.onNext(getStringArg(TOPIC_KEY))
        }
    }

    override fun render(state: StoriesViewState) {
        refresh.isRefreshing = state.isLoading
        adapter.setItems(state.stories)
        if (state.errorText.isNotEmpty()) {
            inform(R.id.refresh, state.errorText)
        }
    }
}