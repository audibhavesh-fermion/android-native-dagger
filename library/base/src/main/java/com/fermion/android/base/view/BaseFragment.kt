package com.fermion.android.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.fermion.android.base.constants.ProgressState
import com.fermion.android.base.helper.BaseProgressBarProvider
import com.fermion.android.dagger_processor.DaggerBaseView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Created by Bhavesh Auodichya.
 *BaseFragment extends DaggerFragment class.
 *
 * **Note**: This provides both view-binding and view-model injection.
 *
 *Extend this class with your dialog fragment class to reduce boiler plate code.
 *
 * **Info** : While defining your fragment annotate it with InjectView to
 * generate fragment injection code.
 *
 * @property binding use this to access view of fragment.
 * @property viewModel use this to access view model.
 *
 *@since 1.0.0
 */
@DaggerBaseView
abstract class BaseFragment<B : ViewBinding, V : BaseViewModel> constructor(private var customLoader: Boolean = false) :
    DaggerFragment(), BaseProgressBarProvider {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    @Inject
    lateinit var viewModel: V

    private var _binding: B? = null

    protected val binding get() = requireNotNull(_binding)

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )
        return binding.root
    }


    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showProgress.observe(viewLifecycleOwner) {
            if (it.equals(ProgressState.SHOW)) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        }
    }


    override fun hideProgressBar() {
        if (!customLoader) {
            (requireActivity() as BaseActivity<*, *>).hideProgressBar()
        }
    }

    override fun showProgressBar() {
        if (!customLoader) {
            (requireActivity() as BaseActivity<*, *>).showProgressBar()
        }
    }


}