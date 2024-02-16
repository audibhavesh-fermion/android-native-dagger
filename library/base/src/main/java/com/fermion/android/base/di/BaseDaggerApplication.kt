package com.fermion.android.base.di

import com.fermion.android.base.BuildConfig
import com.fermion.android.base.helper.CrashReportingTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


/**
 * Created by Bhavesh Auodichya.
 *
 * This is Base Dagger Application class where dagger is dagger graph
 *
 * This is the template and can be used for android module
 * Create customised dagger application for you module
 *@since 1.0.0
 */
open class BaseDaggerApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        //timber debug tree will be planted in only debug mode else CrashReportingTree
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBaseAppComponent.builder().withApplication(this).build()
    }
}