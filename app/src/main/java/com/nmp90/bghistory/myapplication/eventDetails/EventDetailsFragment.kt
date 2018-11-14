package com.nmp90.bghistory.myapplication.eventDetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmp90.bghistory.myapplication.databinding.FragmentEventDetailsBinding
import com.nmp90.bghistory.myapplication.events.EventsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class EventDetailsFragment : Fragment() {

    private val eventsRepository: EventsRepository by inject()

    companion object {

        private val ARG_EVENT_ID = "eventID"

        fun newInstance(eventId: String) : EventDetailsFragment {
            val eventDetailsFragment = EventDetailsFragment()
            val args = Bundle()
            args.putString(ARG_EVENT_ID, eventId)
            eventDetailsFragment.arguments = args
            return eventDetailsFragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        eventsRepository.getEvent(arguments!!.getString(ARG_EVENT_ID)!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({event ->
                binding.event = event
            })

        return binding.root
    }
}
