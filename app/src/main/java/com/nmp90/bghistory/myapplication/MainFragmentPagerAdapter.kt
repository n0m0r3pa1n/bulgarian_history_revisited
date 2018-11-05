package com.nmp90.bghistory.myapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.nmp90.bghistory.myapplication.capitals.CapitalsFragment
import com.nmp90.bghistory.myapplication.topics.TopicsFragment
import com.nmp90.bghistory.myapplication.years.YearsFragment

private const val NUM_PAGES = 3

class MainFragmentPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm)  {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> TopicsFragment()
        1 -> YearsFragment()
        2 -> CapitalsFragment()
        else -> TopicsFragment()
    }

    override fun getCount(): Int = NUM_PAGES

}