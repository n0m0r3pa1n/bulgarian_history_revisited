package com.nmp90.bghistory.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.databinding.FragmentTopicsBinding
import com.nmp90.bghistory.events.EventsActivity
import com.nmp90.bghistory.extensions.observeViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : Fragment(), TopicsAdapter.TopicClickListener {

    private val viewModel: TopicsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTopicsBinding.inflate(LayoutInflater.from(activity), container, false)
        binding.rvTopics.layoutManager = LinearLayoutManager(requireContext())

        observeViewState(viewModel.uiState) { uiState ->
            when (uiState) {
                is TopicsViewModel.UiState.Failure -> {
                    binding.rvTopics.isVisible = false
                    binding.pbLoading.isVisible = true
                    errorHandler.handleError(requireContext(), uiState.throwable)
                }
                is TopicsViewModel.UiState.Success -> {
                    binding.rvTopics.isVisible = true
                    binding.pbLoading.isVisible = false
                    val adapter = TopicsAdapter(uiState.topics, this@TopicsFragment)
                    binding.rvTopics.adapter = adapter
                }
                is TopicsViewModel.UiState.Empty -> binding.pbLoading.isVisible = true
            }
        }

        return binding.root
    }

    override fun onTopicClick(topic: Topic) {
        val intent = EventsActivity.newIntent(requireContext(), topic)
        startActivity(intent)
    }
}
