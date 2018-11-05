package com.nmp90.bghistory.myapplication.years

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.nmp90.bghistory.myapplication.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class YearsFragment : Fragment() {

    private lateinit var rvYears: RecyclerView
    private val yearsRepository: YearsRepository by inject()
    private var adapter: YearsAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_years, container, false)
        rvYears = view.findViewById(R.id.rv_events_years)
        rvYears.layoutManager = LinearLayoutManager(activity)
        val inputSearch = view.findViewById<EditText>(R.id.et_search_event_year)

        loadEvents()
        inputSearch.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                val query = arg0.toString()
                searchEvents(query)
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}

            override fun afterTextChanged(arg0: Editable) {}
        })

        return view
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