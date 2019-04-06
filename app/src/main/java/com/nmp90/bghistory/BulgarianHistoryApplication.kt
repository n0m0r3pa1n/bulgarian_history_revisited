package com.nmp90.bghistory

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
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
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class BulgarianHistoryApplication : MultiDexApplication() {
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
        single { ErrorHandler() }

        viewModel { CapitalDetailsViewModel(get()) }
        viewModel { EventsViewModel(get()) }
        viewModel { TopicsViewModel(get()) }
        viewModel { YearsViewModel(get()) }
        viewModel { CapitalsViewModel(get()) }
        viewModel { EventDetailsViewModel(get()) }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
        Fabric.with(this, Crashlytics(), Answers())
    }
}
