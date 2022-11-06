package com.nmp90.bghistory.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.R
import com.nmp90.bghistory.databinding.FragmentEventsBinding
import com.nmp90.bghistory.eventDetails.EventDetailsFragment
import com.nmp90.bghistory.extensions.observeViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventsFragment : Fragment(), EventsAdapter.EventClickListener {

    private val viewModel: EventsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    companion object {

        private val ARG_TOPIC_ID = "topic"

        fun newInstance(topicId: Int): EventsFragment {
            val eventsFragment = EventsFragment()
            val args = Bundle()
            args.putInt(ARG_TOPIC_ID, topicId)
            eventsFragment.arguments = args
            return eventsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentEventsBinding.inflate(LayoutInflater.from(activity), container, false)
        binding.viewModel = viewModel

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvEvents.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvEvents.context,
            linearLayoutManager.orientation
        )
        binding.rvEvents.addItemDecoration(dividerItemDecoration)

        val topicId = arguments!!.getInt(ARG_TOPIC_ID)
        viewModel.getEvents(topicId)
        observeViewState(viewModel.uiState) { uiState ->
            binding.displayedChildId?.set(uiState.displayedChildId)
            when (uiState) {
                EventsViewModel.UiState.Empty -> Unit
                is EventsViewModel.UiState.Failure -> errorHandler.handleError(requireContext(), uiState.throwable)
                is EventsViewModel.UiState.Success -> {
                    binding.rvEvents.adapter = EventsAdapter(uiState.events, this)
                }
            }
        }

        return binding.root
    }

    override fun onEventClick(event: Event) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, EventDetailsFragment.newInstance(event.id))
            .addToBackStack(EventDetailsFragment::class.java.name)
            .commit()
    }
}
