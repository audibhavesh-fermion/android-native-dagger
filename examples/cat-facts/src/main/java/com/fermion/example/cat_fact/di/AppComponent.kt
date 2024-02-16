package com.fermion.example.cat_fact.di

import android.app.Application
import com.fermion.android.base.di.BaseAppModule
import com.fermion.android.dagger_processor.DaggerAppComponent
import com.fermion.example.cat_fact.di.generated.CommonBuilderModule
import com.fermion.example.cat_fact.network.CatNetworkModule
import com.fermion.example.cat_fact.ui.application.CatFactsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        BaseAppModule::class,
        CatNetworkModule::class,
        CommonBuilderModule::class,
    ],
)

@DaggerAppComponent
interface AppComponent : AndroidInjector<CatFactsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): AppComponent

    }
}