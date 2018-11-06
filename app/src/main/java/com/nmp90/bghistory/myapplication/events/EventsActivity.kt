package com.nmp90.bghistory.myapplication.events

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.topics.Topic

class EventsActivity : AppCompatActivity() {

    companion object {

        private val ARG_TOPIC_ID = "topic"

        fun newIntent(context: Context, topic: Topic) : Intent {
            val intent = Intent(context, EventsActivity::class.java)
            intent.putExtra(ARG_TOPIC_ID, topic.id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, EventsFragment.newInstance(intent.getIntExtra(ARG_TOPIC_ID, 0)))
            .commit()
    }
}