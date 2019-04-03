package com.nmp90.bghistory.events

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nmp90.bghistory.R
import com.nmp90.bghistory.topics.Topic

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

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .add(R.id.container,
                EventsFragment.newInstance(
                    intent.getIntExtra(
                        ARG_TOPIC_ID,
                        0
                    )
                )
            )
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
