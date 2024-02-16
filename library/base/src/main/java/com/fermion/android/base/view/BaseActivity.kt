package com.fermion.android.base.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.fermion.android.base.constants.ProgressState
import com.fermion.android.base.helper.BaseProgressBar
import com.fermion.android.base.helper.BaseProgressBarProvider
import com.fermion.android.base.helper.Utility.isOnline
import com.fermion.android.dagger_processor.DaggerBaseView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Bhavesh Auodichya.
 *
 * BaseActivity Class extends DaggerAppCompactActivity
 *
 * **Note**: This provides both view-binding and view-model injection.
 *
 * Extend this class with your activity class to reduce boiler plate code
 *
 * **Info** : While defining your activity annotate it with InjectView to
 * generate activity injection code
 *
 * @property binding use this to access view of activity.
 * @property viewModel use this to access view model.
 *
 * @since 1.0.0
 */
@DaggerBaseView
abstract class BaseActivity<B : ViewBinding, V : BaseViewModel> : DaggerAppCompatActivity(),
    BaseProgressBarProvider {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mProgressDialog: Dialog? = null

    @Inject
    lateinit var viewModel: V

    private var _binding: B? = null

    protected val binding get() = requireNotNull(_binding)

    abstract val bindingInflater: (LayoutInflater) -> B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding?.root)
        viewModel.showProgress.observe(this) {
            if (it.equals(ProgressState.SHOW)) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
            isOnline(applicationContext)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun hideProgressBar() {
        mProgressDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.cancel()
            }
        }
    }

    override fun showProgressBar() {
        hideProgressBar()
        mProgressDialog = BaseProgressBar().showLoading(this)
    }

}
