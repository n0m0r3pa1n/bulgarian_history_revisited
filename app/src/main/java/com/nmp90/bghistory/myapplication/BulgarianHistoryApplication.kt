package com.nmp90.bghistory.myapplication

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.nmp90.bghistory.myapplication.capitalDetails.CapitalDetailsViewModel
import com.nmp90.bghistory.myapplication.capitals.CapitalMapper
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import com.nmp90.bghistory.myapplication.capitals.CapitalsViewModel
import com.nmp90.bghistory.myapplication.eventDetails.EventDetailsViewModel
import com.nmp90.bghistory.myapplication.events.EventMapper
import com.nmp90.bghistory.myapplication.events.EventsRepository
import com.nmp90.bghistory.myapplication.events.EventsViewModel
import com.nmp90.bghistory.myapplication.topics.TopicMapper
import com.nmp90.bghistory.myapplication.topics.TopicsRepository
import com.nmp90.bghistory.myapplication.topics.TopicsViewModel
import com.nmp90.bghistory.myapplication.years.YearMapper
import com.nmp90.bghistory.myapplication.years.YearsRepository
import com.nmp90.bghistory.myapplication.years.YearsViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class BulgarianHistoryApplication : Application() {
    val myModule = module {

        factory { CapitalMapper() }
        factory { TopicMapper() }
        factory { EventMapper() }
        factory { YearMapper() }

        single { FirebaseFirestore.getInstance() }
        single { YearsRepository(get(), get()) }
        single { EventsRepository(get(), get()) }
        single { CapitalsRepository(get(), get()) }
        single { TopicsRepository(get(), get()) }

        viewModel { CapitalDetailsViewModel(get()) }
        viewModel { EventsViewModel(get()) }
        viewModel { TopicsViewModel(get()) }
        viewModel { YearsViewModel(get()) }
        viewModel { CapitalsViewModel(get()) }
        viewModel { EventDetailsViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }
}
