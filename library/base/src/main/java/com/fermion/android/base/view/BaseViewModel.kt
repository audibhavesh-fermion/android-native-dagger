package com.fermion.android.base.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fermion.android.base.constants.ProgressState
import javax.inject.Inject
import com.fermion.android.dagger_processor.DaggerBaseViewModel

/**
 * Created by Bhavesh Auodichya.
 *
 * BaseViewModel extends ViewModel
 *
 * **Note** : this class provides  view model with basic functionality to your custom view model.
 *
 * Extend this class with your view model for dagger injection
 *
 * **Info** : While defining your view model annotate it with InjectViewModel to
 * generate view-model injection code
 *
 * @property showProgress use this observable to handle progress eg (api, data processing etc)
 * @property showError use this observable to handle error
 **
 *@since 1.0.0
 */
@DaggerBaseViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    val showProgress: MutableLiveData<ProgressState> = MutableLiveData()
    val showError: MutableLiveData<Throwable> = MutableLiveData()

}