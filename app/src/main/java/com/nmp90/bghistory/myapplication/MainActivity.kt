package com.nmp90.bghistory.myapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.get


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigation: BottomNavigationView

    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
        setupViewPager()

        setTitle(R.string.app_name)
        setSupportActionBar(findViewById(R.id.toolbar))


        val capitalsRepository: CapitalsRepository = get()
        val disposable2 = capitalsRepository.getCapitals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {list -> print("${list.size} ${list.get(0).name}")}
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
