package com.nmp90.bghistory.myapplication

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.nmp90.bghistory.myapplication.topics.TopicsFragment

private const val NUM_PAGES = 3

class MainFragmentPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm)  {

    override fun getItem(p0: Int): Fragment = TopicsFragment()

    override fun getCount(): Int = NUM_PAGES

}