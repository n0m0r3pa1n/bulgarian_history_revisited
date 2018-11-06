package com.nmp90.bghistory.myapplication.topics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.events.EventsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class TopicsFragment : Fragment(), TopicsAdapter.TopicClickListener {

    private val topicsRepoository: TopicsRepository by inject()
    private lateinit var rvTopics: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)
        rvTopics = view.findViewById(R.id.rv_topics)
        rvTopics.layoutManager = LinearLayoutManager(requireContext())

        topicsRepoository.getTopics()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ topics ->
                val adapter = TopicsAdapter(topics, this)
                rvTopics.adapter = adapter
            })
        return view
    }

    override fun onTopicClick(topic: Topic) {
        val intent = EventsActivity.newIntent(requireContext(), topic)
        startActivity(intent)
    }
}