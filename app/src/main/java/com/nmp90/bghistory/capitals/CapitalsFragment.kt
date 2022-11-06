package com.nmp90.bghistory.capitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.R
import com.nmp90.bghistory.capitalDetails.CapitalDetailsActivity
import com.nmp90.bghistory.extensions.observeViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CapitalsFragment : Fragment(), CapitalsAdapter.CapitalClickListener {
    private val viewModel: CapitalsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    private lateinit var rvCapitals: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_capitals, container, false)
        rvCapitals = view.findViewById(R.id.rv_capitals)
        rvCapitals.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        loadCapitals()
        return view
    }

    private fun loadCapitals() {
        observeViewState(viewModel.uiState) {
            when (it) {
                is CapitalsViewModel.UiState.Error -> {
                    errorHandler.handleError(requireContext(), it.throwable)
                }
                is CapitalsViewModel.UiState.Success -> {
                    val adapter = CapitalsAdapter(it.capitals, this)
                    rvCapitals.adapter = adapter
                }
            }
        }
    }

    override fun onCapitalClick(capital: Capital) {
        val intent = CapitalDetailsActivity.newIntent(requireContext(), capital.id)
        startActivity(intent)
    }
}
