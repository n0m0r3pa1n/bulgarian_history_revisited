package com.nmp90.bghistory.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.databinding.FragmentTopicsBinding
import com.nmp90.bghistory.events.EventsActivity
import com.nmp90.reactivelivedata2.subscribeSingle
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : Fragment(), TopicsAdapter.TopicClickListener {

    private val topicsViewModel: TopicsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTopicsBinding.inflate(LayoutInflater.from(activity), container, false)
        binding.viewModel = topicsViewModel
        binding.rvTopics.layoutManager = LinearLayoutManager(requireContext())

        topicsViewModel.loadTopics().subscribeSingle(this,
            onSuccess = {
                val adapter = TopicsAdapter(it, this)
                binding.rvTopics.adapter = adapter
            },
            onError = { errorHandler.handleError(requireContext(), it) })

        return binding.root
    }

    override fun onTopicClick(topic: Topic) {
        val intent = EventsActivity.newIntent(requireContext(), topic)
        startActivity(intent)
    }
}
