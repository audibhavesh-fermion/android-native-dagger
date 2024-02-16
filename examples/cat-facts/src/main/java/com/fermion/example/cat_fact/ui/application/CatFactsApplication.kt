package com.fermion.example.cat_fact.ui.application

import com.fermion.android.base.helper.CrashReportingTree
import com.fermion.example.cat_fact.BuildConfig
import com.fermion.example.cat_fact.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class CatFactsApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().withApplication(this)
            .build()
    }
}