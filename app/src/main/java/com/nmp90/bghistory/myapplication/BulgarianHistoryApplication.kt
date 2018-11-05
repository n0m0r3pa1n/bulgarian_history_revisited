package com.nmp90.bghistory.myapplication

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.nmp90.bghistory.myapplication.capitals.CapitalMapper
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import com.nmp90.bghistory.myapplication.events.EventsRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class BulgarianHistoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val myModule = module {
            factory { CapitalMapper() }
            single { FirebaseFirestore.getInstance() }
            single { EventsRepository(get()) }
            single { CapitalsRepository(get(), get()) }
        }

        startKoin(this, listOf(myModule))
    }
}