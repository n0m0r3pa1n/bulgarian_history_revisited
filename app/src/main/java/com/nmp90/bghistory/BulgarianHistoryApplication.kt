package com.nmp90.bghistory

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.nmp90.bghistory.capitalDetails.CapitalDetailsViewModel
import com.nmp90.bghistory.capitals.CapitalMapper
import com.nmp90.bghistory.capitals.CapitalsRepository
import com.nmp90.bghistory.capitals.CapitalsViewModel
import com.nmp90.bghistory.eventDetails.EventDetailsViewModel
import com.nmp90.bghistory.events.EventMapper
import com.nmp90.bghistory.events.EventsRepository
import com.nmp90.bghistory.events.EventsViewModel
import com.nmp90.bghistory.topics.TopicMapper
import com.nmp90.bghistory.topics.TopicsRepository
import com.nmp90.bghistory.topics.TopicsViewModel
import com.nmp90.bghistory.years.YearMapper
import com.nmp90.bghistory.years.YearsRepository
import com.nmp90.bghistory.years.YearsViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class BulgarianHistoryApplication : MultiDexApplication() {
    private val myModule by lazy {
        module {

            factory { CapitalMapper() }
            factory { TopicMapper() }
            factory { EventMapper() }
            factory { YearMapper() }

            single { FirebaseFirestore.getInstance() }
            single { YearsRepository(get(), get()) }
            single { EventsRepository(get(), get()) }
            single { CapitalsRepository(get(), get()) }
            single { TopicsRepository(get(), get()) }
            single { ErrorHandler() }

            viewModel { CapitalDetailsViewModel(get(), get()) }
            viewModel { EventsViewModel(get(), get()) }
            viewModel { TopicsViewModel(get()) }
            viewModel { YearsViewModel(get()) }
            viewModel { CapitalsViewModel(get()) }
            viewModel { EventDetailsViewModel(get(), get()) }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            modules(listOf(myModule))
        }
    }
}
