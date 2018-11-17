package com.nmp90.bghistory.myapplication.capitalDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.nmp90.bghistory.myapplication.R

class CapitalDetailsActivity : AppCompatActivity() {
    companion object {

        private val ARG_CAPITAL_ID = "capital"

        fun newIntent(context: Context, capitalId: String): Intent {
            val intent = Intent(context, CapitalDetailsActivity::class.java)
            intent.putExtra(ARG_CAPITAL_ID, capitalId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, CapitalDetailsFragment.newInstance(intent.getStringExtra(ARG_CAPITAL_ID)))
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
