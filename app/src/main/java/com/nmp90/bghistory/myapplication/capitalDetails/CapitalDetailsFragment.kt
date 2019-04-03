package com.nmp90.bghistory.myapplication.capitalDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import com.nmp90.bghistory.myapplication.databinding.FragmentCapitalDetailsBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class CapitalDetailsFragment : Fragment() {
    private val capitalsRepository: CapitalsRepository by inject()

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
        capitalsRepository.getCapital(capitalId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ capital ->
                binding.capital = capital
            })
        return binding.root
    }
}
