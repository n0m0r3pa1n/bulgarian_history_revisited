package com.nmp90.bghistory.events

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nmp90.bghistory.R
import com.nmp90.bghistory.topics.Topic

class EventsActivity : AppCompatActivity() {

    private var topicId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        topicId = savedInstanceState?.getInt(ARG_TOPIC_ID) ?: intent.getIntExtra(ARG_TOPIC_ID, 0)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EventsFragment.newInstance(topicId))
                .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_TOPIC_ID, topicId)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val ARG_TOPIC_ID = "topic"

        fun newIntent(context: Context, topic: Topic) : Intent {
            val intent = Intent(context, EventsActivity::class.java)
            intent.putExtra(ARG_TOPIC_ID, topic.id)
            return intent
        }
    }
}
