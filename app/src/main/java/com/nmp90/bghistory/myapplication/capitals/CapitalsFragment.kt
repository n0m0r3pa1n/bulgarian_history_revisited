package com.nmp90.bghistory.myapplication.capitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.myapplication.R
import com.nmp90.bghistory.myapplication.capitalDetails.CapitalDetailsActivity
import com.nmp90.reactivelivedata2.subscribeSingle
import org.koin.androidx.viewmodel.ext.android.viewModel

class CapitalsFragment : Fragment(), CapitalsAdapter.CapitalClickListener {
    private val capitalsViewModel: CapitalsViewModel by viewModel()

    private lateinit var rvCapitals: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_capitals, container, false)
        rvCapitals = view.findViewById(R.id.rv_capitals)
        rvCapitals.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        loadCapitals()
        return view
    }

    private fun loadCapitals() {
        capitalsViewModel.getCapitals().subscribeSingle(this, onSuccess =  {
            val adapter = CapitalsAdapter(it.toMutableList(), this)
            rvCapitals.adapter = adapter
        })
    }

    override fun onCapitalClick(capital: Capital) {
        val intent = CapitalDetailsActivity.newIntent(requireContext(), capital.id)
        startActivity(intent)
    }
}
