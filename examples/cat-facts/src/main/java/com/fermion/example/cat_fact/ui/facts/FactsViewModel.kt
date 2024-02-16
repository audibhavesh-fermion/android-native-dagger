package com.fermion.example.cat_fact.ui.facts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fermion.android.base.constants.ProgressState
import com.fermion.android.base.network.NetworkResult
import com.fermion.android.base.view.BaseViewModel
import com.fermion.android.dagger_processor.InjectViewModel
import com.fermion.example.cat_fact.network.CatFactRepository
import com.fermion.example.cat_fact.ui.facts.models.CatFactModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@InjectViewModel
class FactsViewModel @Inject constructor(private val catFactRepository: CatFactRepository) :
    BaseViewModel() {

    val catFactListener = MutableLiveData<NetworkResult<CatFactModel>>()

    fun getCatFact() {
        viewModelScope.launch {
            try {
                showProgress.value = ProgressState.SHOW
                val response = catFactRepository.getFact()
                showProgress.value = ProgressState.HIDE
                catFactListener.value = response
            } catch (e: Exception) {
                showProgress.value = ProgressState.HIDE
                Timber.e(e)

            }
        }
    }
}