package com.nmp90.bghistory.eventDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nmp90.bghistory.ErrorHandler
import com.nmp90.bghistory.databinding.FragmentEventDetailsBinding
import com.nmp90.bghistory.extensions.observeViewState
import com.nmp90.reactivelivedata2.subscribeSingle
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsFragment : Fragment() {

    private val viewModel: EventDetailsViewModel by viewModel()
    private val errorHandler: ErrorHandler by inject()
    private var oldTitle: CharSequence? = null

    companion object {

        private val ARG_EVENT_ID = "eventID"

        fun newInstance(eventId: String): EventDetailsFragment {
            val eventDetailsFragment = EventDetailsFragment()
            val args = Bundle()
            args.putString(ARG_EVENT_ID, eventId)
            eventDetailsFragment.arguments = args
            return eventDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentEventDetailsBinding.inflate(inflater, container, false)

        viewModel.getEvent(arguments!!.getString(ARG_EVENT_ID)!!)
        observeViewState(viewModel.uiState) { uiState ->
            binding.childId?.set(uiState.displayedChildId)
            when (uiState) {
                EventDetailsViewModel.UiState.Empty -> Unit
                is EventDetailsViewModel.UiState.Failure -> {
                    errorHandler.handleError(requireContext(), uiState.throwable)
                }
                is EventDetailsViewModel.UiState.Success -> {
                    val event = uiState.event
                    oldTitle = activity?.title
                    activity?.title = event.title
                    binding.event = event
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        activity?.title = oldTitle
        super.onDestroyView()
    }
}
