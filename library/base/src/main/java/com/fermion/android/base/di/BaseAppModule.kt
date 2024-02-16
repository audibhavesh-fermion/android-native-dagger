package com.fermion.android.base.di

import dagger.Module


/**
 * Created by Bhavesh Auodichya.
 *
 * Base App Module where all base modules are injected
 *
 *@since 1.0.0
 */
@Module(includes = [ViewModelModule::class, BaseNetworkModule::class])
interface BaseAppModule