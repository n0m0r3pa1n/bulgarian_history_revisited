package com.nmp90.bghistory.events

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nmp90.bghistory.R

class EventsAdapter(private val eventsList: List<Event>, private val eventClickListener: EventClickListener)
    : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_event, viewGroup, false)
        val viewHolder = ViewHolder(view)
        view.findViewById<TextView>(R.id.tv_event_name).setOnClickListener { _ ->
            if(viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                eventClickListener.onEventClick(eventsList[viewHolder.adapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount() = eventsList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(eventsList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvEventName : TextView = view.findViewById(R.id.tv_event_name)

        fun bind(event: Event) {
            tvEventName.text = event.title
        }
    }

    interface EventClickListener {
        fun onEventClick(event: Event)
    }
}
