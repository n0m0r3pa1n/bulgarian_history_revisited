package com.nmp90.bghistory

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var networkBroadcastReceiver: BroadcastReceiver

    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        setupViewPager()

        setTitle(R.string.app_name)
        setSupportActionBar(findViewById(R.id.toolbar))

        networkBroadcastReceiver = NetworkConnectionBroadcastReceiver()
        registerReceiver(networkBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(networkBroadcastReceiver)
        super.onDestroy()
    }

    private fun setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_periods -> viewPager.currentItem = 0
                R.id.action_events -> viewPager.currentItem = 1
                R.id.action_capitals -> viewPager.currentItem = 2
            }
            false
        }
    }

    private fun setupViewPager() {
        val adapter = MainFragmentPagerAdapter(supportFragmentManager)
        viewPager = findViewById(R.id.main_viewpager)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null)
                    prevMenuItem!!.isChecked = false;
                else
                    bottomNavigation.menu.getItem(0).isChecked = false;

                bottomNavigation.menu.getItem(position).isChecked = true;
                prevMenuItem = bottomNavigation.menu.getItem(position);
            }

        })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
