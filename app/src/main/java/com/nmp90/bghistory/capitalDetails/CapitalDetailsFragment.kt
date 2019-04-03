package com.nmp90.bghistory.capitalDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nmp90.bghistory.databinding.FragmentCapitalDetailsBinding
import com.nmp90.reactivelivedata2.subscribeSingle
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

        val capitalId = arguments!!.getString(ARG_CAPITAL_ID)
        capitalDetailsViewModel.getCapital(capitalId!!)
            .subscribeSingle(this, onSuccess = {
                binding.capital = it
                binding.btnCapitalDetailsLocation.setOnClickListener { _ ->
                    val intent = Intent(
                        android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=${it.lat},${it.lng}")
                    )

                    startActivity(intent)
                }
            })
        return binding.root
    }
}
