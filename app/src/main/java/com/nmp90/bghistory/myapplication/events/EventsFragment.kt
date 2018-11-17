package com.nmp90.bghistory.myapplication.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.eventDetails.EventDetailsFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject


class EventsFragment : Fragment(), EventsAdapter.EventClickListener {

    private val eventsRepository: EventsRepository by inject()

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
        val view = inflater.inflate(R.layout.fragment_events, container, false)
        val rvEvents: RecyclerView = view.findViewById(R.id.rv_events)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        rvEvents.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            rvEvents.context,
            linearLayoutManager.orientation
        )
        rvEvents.addItemDecoration(dividerItemDecoration)

        val topicId = arguments!!.getInt(ARG_TOPIC_ID)
        eventsRepository.getEvents(topicId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ eventsList ->
                rvEvents.adapter = EventsAdapter(eventsList, this)
            })
        return view
    }

    override fun onEventClick(event: Event) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, EventDetailsFragment.newInstance(event.id))
            .addToBackStack(EventDetailsFragment::class.java.name)
            .commit()
    }
}
