package com.nmp90.bghistory.myapplication.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp90.bghistory.myapplication.databinding.FragmentTopicsBinding
import com.nmp90.bghistory.myapplication.events.EventsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : Fragment(), TopicsAdapter.TopicClickListener {

    private val topicsViewModel: TopicsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTopicsBinding.inflate(LayoutInflater.from(activity), container, false)
        binding.viewModel = topicsViewModel
        binding.rvTopics.layoutManager = LinearLayoutManager(requireContext())
        topicsViewModel.getTopics().observe(this, Observer {
            val adapter = TopicsAdapter(it, this)
            binding.rvTopics.adapter = adapter
        })

        return binding.root
    }

    override fun onTopicClick(topic: Topic) {
        val intent = EventsActivity.newIntent(requireContext(), topic)
        startActivity(intent)
    }
}
