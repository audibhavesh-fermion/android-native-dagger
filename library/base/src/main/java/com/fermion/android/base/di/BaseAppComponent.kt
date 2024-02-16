package com.fermion.android.base.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by Bhavesh Auodichya.
 *
 * BaseAppComponent for injecting Module and SubComponent to dagger
 * This template can be used in other module for injection of dagger graph
 *@since 1.0.0
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        BaseAppModule::class
    ]
)
interface BaseAppComponent : AndroidInjector<BaseDaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder

        fun build(): BaseAppComponent
    }


}
