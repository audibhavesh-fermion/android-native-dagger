package com.fermion.android.base.view

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.fermion.android.dagger_processor.DaggerBaseViewModel
import javax.inject.Inject

/**
 * Created by Bhavesh Auodichya.
 *
 * BaseViewModelDialogFragment extends BaseDialogFragment
 *
 * **Note**:This provides both view binding and view-model injection
 *
 * Extend this class with your dialog fragment class to reduce boiler plate code
 *
 * **Info** : While defining your fragment annotate it with InjectView to
 * generate fragment injection code
 *
 * @property binding use this to access view of fragment
 * @property viewModel use this to access view model.
 *@since 1.0.0
 */
@Suppress("UNCHECKED_CAST")
@DaggerBaseViewModel
abstract class BaseViewModelDialogFragment<B : ViewBinding, V : BaseViewModel> :
    BaseDialogFragment<B>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel()
    }


    private fun obtainViewModel(): V {
        return ViewModelProvider(this, viewModelFactory) as V
    }


    @CallSuper
    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        Navigation.setViewNavController(view, parentFragment?.findNavController())
        super.onViewCreated(view, savedInstanceState)
    }

}