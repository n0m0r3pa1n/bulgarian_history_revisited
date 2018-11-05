package com.nmp90.bghistory.myapplication

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.nmp90.bghistory.myapplication.capitals.CapitalMapper
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import com.nmp90.bghistory.myapplication.events.EventMapper
import com.nmp90.bghistory.myapplication.events.EventsRepository
import com.nmp90.bghistory.myapplication.topics.TopicMapper
import com.nmp90.bghistory.myapplication.topics.TopicsRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class BulgarianHistoryApplication : Application() {
    val myModule = module {
        factory { CapitalMapper() }
        factory { TopicMapper() }
        factory { EventMapper() }
        single { FirebaseFirestore.getInstance() }
        single { EventsRepository(get(), get()) }
        single { CapitalsRepository(get(), get()) }
        single { TopicsRepository(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(myModule))
    }
}