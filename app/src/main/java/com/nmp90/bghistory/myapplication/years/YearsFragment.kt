package com.nmp90.bghistory.myapplication.years

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.myapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class YearsFragment : Fragment() {

    private lateinit var rvYears: RecyclerView
    private val yearsViewModel: YearsViewModel by viewModel()
    private var adapter: YearsAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.fragment_years, container, false)
        rvYears = view.findViewById(R.id.rv_events_years)

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvYears.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            rvYears.context,
            layoutManager.orientation
        )
        rvYears.addItemDecoration(dividerItemDecoration)

        loadEvents()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.action_search)
        searchItem.isVisible = true
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchEvents(query ?: "")
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchEvents(query ?: "")
                return true
            }
        })
    }

    private fun loadEvents() {
        yearsViewModel.getYears().observe(this, Observer {
            adapter = YearsAdapter(it.toMutableList())
            rvYears.adapter = adapter
        })
    }

    private fun searchEvents(query: String) {
        yearsViewModel.searchYears(query).observe(this, Observer {
            adapter?.setData(it)
            adapter?.notifyDataSetChanged()
        })
    }
}
