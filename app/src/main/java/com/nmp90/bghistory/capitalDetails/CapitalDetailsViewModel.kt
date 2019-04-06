package com.nmp90.bghistory.capitalDetails

import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.nmp90.bghistory.R
import com.nmp90.bghistory.capitals.Capital
import com.nmp90.bghistory.capitals.CapitalsRepository
import com.nmp90.bghistory.extensions.toReactiveSource
import com.nmp90.bghistory.lifecycle.LifecycleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalDetailsViewModel constructor(private val capitalsRepository: CapitalsRepository) : LifecycleViewModel() {

    val displayedChildId = ObservableInt(R.id.pb_loading)
    val capital: MutableLiveData<Capital> = MutableLiveData()

    fun getCapital(capitalId: String) = capitalsRepository.getCapital(capitalId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { displayedChildId.set(R.id.card_view) }
        .toReactiveSource()

}
