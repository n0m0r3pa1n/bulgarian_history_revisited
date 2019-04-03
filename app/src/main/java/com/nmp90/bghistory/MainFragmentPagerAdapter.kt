package com.nmp90.bghistory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.nmp90.bghistory.capitals.CapitalsFragment
import com.nmp90.bghistory.topics.TopicsFragment
import com.nmp90.bghistory.years.YearsFragment

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
