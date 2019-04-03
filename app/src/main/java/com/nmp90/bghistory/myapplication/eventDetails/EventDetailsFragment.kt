package com.nmp90.bghistory.myapplication.eventDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.binding.setDisplayedChildId
import com.nmp90.bghistory.myapplication.databinding.FragmentEventDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment() {

    private val eventsViewModel: EventDetailsViewModel by viewModel()
    private var oldTitle: CharSequence? = null

    companion object {

        private val ARG_EVENT_ID = "eventID"

        fun newInstance(eventId: String): EventDetailsFragment {
            val eventDetailsFragment = EventDetailsFragment()
            val args = Bundle()
            args.putString(ARG_EVENT_ID, eventId)
            eventDetailsFragment.arguments = args
            return eventDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        binding.rootContainer.setDisplayedChildId(R.id.loader)
        eventsViewModel.getEvent(arguments!!.getString(ARG_EVENT_ID)!!).observe(this, Observer { event ->
            oldTitle = activity?.title
            activity?.title = event.title
            binding.event = event
            binding.rootContainer.setDisplayedChildId(R.id.content)
        })

        return binding.root
    }

    override fun onDestroyView() {
        activity?.title = oldTitle
        super.onDestroyView()
    }
}
