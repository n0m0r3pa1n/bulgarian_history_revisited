package com.nmp90.bghistory.myapplication.capitals

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.capitalDetails.CapitalDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class CapitalsFragment : Fragment(), CapitalsAdapter.CapitalClickListener {
    private val capitalsRepository: CapitalsRepository by inject()

    private lateinit var rvCapitals: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_capitals, container, false)
        rvCapitals = view.findViewById(R.id.rv_capitals)
        rvCapitals.layoutManager = GridLayoutManager(context, 2, VERTICAL, false)

        loadCapitals()
        return view
    }

    private fun loadCapitals() {
        capitalsRepository.getCapitals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                val adpater = CapitalsAdapter(it.toMutableList(), this)
                rvCapitals.adapter = adpater
            }
    }

    override fun onCapitalClick(capital: Capital) {
        val intent = CapitalDetailsActivity.newIntent(requireContext(), capital.id)
        startActivity(intent)
    }
}
