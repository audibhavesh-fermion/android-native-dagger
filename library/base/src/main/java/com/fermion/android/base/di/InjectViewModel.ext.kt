package com.fermion.android.base.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner


/**
 * Created by Bhavesh Auodichya.
 *
 * Inline method which provides view-model of FragmentActivity
 *
 *@since 1.0.0
 *@param factory: ViewModelProvider.Factory
 *@return ViewModel of type T
 */

@Suppress("unused")
inline fun <reified T : ViewModel> FragmentActivity.provideViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}


/**
 * Created by Bhavesh Auodichya.
 *
 * Inline method which provides view-model of Fragment
 *
 *@since 1.0.0
 *@param factory: ViewModelProvider.Factory
 *@return ViewModel of type T
 */
@Suppress("unused")
inline fun <reified T : ViewModel> Fragment.provideViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}


/**
 * Created by Bhavesh Auodichya.
 *
 * Inline method which provides view-model of AppCompatActivity
 *
 *@since 1.0.0
 *@param factory: ViewModelProvider.Factory
 *@return ViewModel of type T
 */
@Suppress("unused")
inline fun <reified T : ViewModel> AppCompatActivity.provideViewModel(
    factory: ViewModelProvider.Factory,
    owner: ViewModelStoreOwner = this
): T {
    return ViewModelProvider(owner, factory)[T::class.java]
}
