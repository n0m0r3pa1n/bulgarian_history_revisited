package com.nmp90.bghistory.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.nmp90.bghistory.myapplication.capitals.CapitalsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.get


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val capitalsRepository: CapitalsRepository = get()
        val disposable2 = capitalsRepository.getCapitals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {list -> print("${list.size} ${list.get(0).name}")}

//        val eventsRepository: EventsRepository = get()
//        val disposable = eventsRepository.getEvents()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {list -> print("${list.size}")}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
