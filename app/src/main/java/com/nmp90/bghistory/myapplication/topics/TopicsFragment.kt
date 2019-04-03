package com.nmp90.bghistory.myapplication.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.events.EventsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : Fragment(), TopicsAdapter.TopicClickListener {

    private val topicsViewModel: TopicsViewModel by viewModel()
    private lateinit var rvTopics: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_topics, container, false)
        rvTopics = view.findViewById(R.id.rv_topics)
        rvTopics.layoutManager = LinearLayoutManager(requireContext())

        topicsViewModel.getTopics().observe(this, Observer {
            val adapter = TopicsAdapter(it, this)
            rvTopics.adapter = adapter
        })

        return view
    }

    override fun onTopicClick(topic: Topic) {
        val intent = EventsActivity.newIntent(requireContext(), topic)
        startActivity(intent)
    }
}
