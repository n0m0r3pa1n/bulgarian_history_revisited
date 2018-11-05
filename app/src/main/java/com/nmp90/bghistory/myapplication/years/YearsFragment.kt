package com.nmp90.bghistory.myapplication.years

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.nmp90.bghistory.myapplication.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class YearsFragment : Fragment() {

    private lateinit var rvYears: RecyclerView
    private val yearsRepository: YearsRepository by inject()
    private var adapter: YearsAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_years, container, false)
        rvYears = view.findViewById(R.id.rv_events_years)
        rvYears.layoutManager = LinearLayoutManager(activity)

        loadEvents()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.action_search)
        searchItem.isVisible = true
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchEvents(query ?: "")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean  = false
        })
    }

    private fun loadEvents() {
        yearsRepository.getYears()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                adapter = YearsAdapter(it.toMutableList())
                rvYears.adapter = adapter
            }
    }

    private fun searchEvents(query: String) {
        yearsRepository.searchYears(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                adapter?.setData(it)
                adapter?.notifyDataSetChanged()
            }
    }
}