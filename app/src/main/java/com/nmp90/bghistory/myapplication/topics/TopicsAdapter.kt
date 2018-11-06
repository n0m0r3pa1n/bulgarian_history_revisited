package com.nmp90.bghistory.myapplication.topics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nmp90.bghistory.myapplication.R

class TopicsAdapter(private val topicsList: List<Topic>, private val topicClickListener: TopicClickListener)
    : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_topic, viewGroup, false)
        val viewHolder = ViewHolder(view)
        view.findViewById<TextView>(R.id.tv_topic_name).setOnClickListener { _ ->
            if(viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                topicClickListener.onTopicClick(topicsList[viewHolder.adapterPosition])
            }
        }
        return viewHolder
    }

    override fun getItemCount() = topicsList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(topicsList[position])

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTopicName : TextView = view.findViewById(R.id.tv_topic_name)

        fun bind(topic: Topic) {
            tvTopicName.text = topic.name
        }
    }

    interface TopicClickListener {
        fun onTopicClick(topic: Topic)
    }
}