package com.nmp90.bghistory.myapplication.capitalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nmp90.bghistory.myapplication.databinding.FragmentCapitalDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CapitalDetailsFragment : Fragment() {
    private val capitalDetailsViewModel: CapitalDetailsViewModel by viewModel()

    companion object {

        private val ARG_CAPITAL_ID = "capital"

        fun newInstance(capitalId: String): CapitalDetailsFragment {
            val capitalDetailsFragment = CapitalDetailsFragment()
            val args = Bundle()
            args.putString(ARG_CAPITAL_ID, capitalId)
            capitalDetailsFragment.arguments = args
            return capitalDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCapitalDetailsBinding.inflate(inflater, container, false)

        val capitalId = arguments!!.getString(CapitalDetailsFragment.ARG_CAPITAL_ID)
        capitalDetailsViewModel.getCapital(capitalId!!)
            .observe(this, Observer { binding.capital = it })
        return binding.root
    }
}
