package com.nmp90.bghistory.myapplication

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.nmp90.bghistory.myapplication.events.EventsRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class BulgarianHistoryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val myModule = module {
            single { FirebaseFirestore.getInstance() }
            single { EventsRepository(get()) }
        }

        startKoin(this, listOf(myModule))
    }
}