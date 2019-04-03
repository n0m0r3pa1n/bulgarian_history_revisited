package com.nmp90.bghistory.myapplication.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.databinding.FragmentEventsBinding
import com.nmp90.bghistory.myapplication.eventDetails.EventDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventsFragment : Fragment(), EventsAdapter.EventClickListener {

    private val eventsViewModel: EventsViewModel by viewModel()

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEventsBinding.inflate(LayoutInflater.from(activity), container, false)
        binding.viewModel = eventsViewModel

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvEvents.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvEvents.context,
            linearLayoutManager.orientation
        )
        binding.rvEvents.addItemDecoration(dividerItemDecoration)

        val topicId = arguments!!.getInt(ARG_TOPIC_ID)
        eventsViewModel.getEvents(topicId)
            .observe(this, Observer {
                binding.rvEvents.adapter = EventsAdapter(it, this)
            })

        return binding.root
    }

    override fun onEventClick(event: Event) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, EventDetailsFragment.newInstance(event.id))
            .addToBackStack(EventDetailsFragment::class.java.name)
            .commit()
    }
}
