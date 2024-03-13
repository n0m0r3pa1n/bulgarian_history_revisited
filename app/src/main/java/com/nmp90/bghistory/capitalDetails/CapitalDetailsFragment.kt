package com.nmp90.bghistory.capitalDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.databinding.FragmentCapitalDetailsBinding
import com.nmp90.bghistory.extensions.observeViewState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class CapitalDetailsFragment : Fragment() {
    private val viewModel: CapitalDetailsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentCapitalDetailsBinding.inflate(inflater, container, false)
        val capitalId = arguments?.getString(ARG_CAPITAL_ID) ?: savedInstanceState?.getString(ARG_CAPITAL_ID, "")
        observeViewState(viewModel.uiState) { uiState ->
            when (uiState) {
                CapitalDetailsViewModel.UiState.Empty -> Unit
                is CapitalDetailsViewModel.UiState.Failure -> {
                    errorHandler.handleError(requireContext(), uiState.throwable)
                }
                is CapitalDetailsViewModel.UiState.Success -> {
                    val capital = uiState.capital
                    binding.capital = capital
                    binding.btnCapitalDetailsLocation.setOnClickListener { _ ->
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?q=${capital.lat},${capital.lng}")
                        )

                        startActivity(intent)
                    }
                }
            }
        }

        return binding.root
    }

    companion object {

        private const val ARG_CAPITAL_ID = "capital"

        fun newInstance(capitalId: String): CapitalDetailsFragment {
            val capitalDetailsFragment = CapitalDetailsFragment()
            val args = Bundle()
            args.putString(ARG_CAPITAL_ID, capitalId)
            capitalDetailsFragment.arguments = args
            return capitalDetailsFragment
        }
    }
}
