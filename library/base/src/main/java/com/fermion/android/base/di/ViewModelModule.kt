package com.fermion.android.base.di

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fermion.android.base.view.BaseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Bhavesh Auodichya.
 *
 * Define Base ViewModel and other view-model injectors here
 *
 *@since 1.0.0
 */

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @NonNull
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    abstract fun bindBaseViewModule(mainViewModel: BaseViewModel): ViewModel
}