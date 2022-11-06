package com.nmp90.bghistory.years

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.R
import com.nmp90.bghistory.extensions.observeViewState
import com.nmp90.reactivelivedata2.subscribeSingle
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class YearsFragment : Fragment() {

    private val viewModel: YearsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    private lateinit var rvYears: RecyclerView
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
        adapter = YearsAdapter(mutableListOf())
        rvYears.adapter = adapter
        rvYears.addItemDecoration(dividerItemDecoration)

        observeViewModel()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchItem = menu.findItem(R.id.action_search)
        searchItem.isVisible = true
        val searchView: SearchView = searchItem.actionView as SearchView
        val searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchEditText.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        searchEditText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        searchEditText.setHint(R.string.years_search_hint)
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

    private fun observeViewModel() {
        observeViewState(viewModel.uiState) { uiState ->
            when (uiState) {
                YearsViewModel.UiState.EmptyResult -> adapter?.setData(emptyList())
                is YearsViewModel.UiState.SearchResult -> adapter?.setData(uiState.years)
                is YearsViewModel.UiState.YearsResult -> adapter?.setData(uiState.years)
                is YearsViewModel.UiState.Error -> errorHandler.handleError(requireContext(), uiState.throwable)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    private fun searchEvents(query: String) {
        viewModel.searchYears(query)
    }
}
