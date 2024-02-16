package com.fermion.example.cat_fact.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fermion.android.base.view.BaseFragment
import com.fermion.android.base.view.BaseViewModel
import com.fermion.android.dagger_processor.InjectView
import com.fermion.example.cat_fact.databinding.FragmentHomeBinding


@InjectView
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wanCatFact.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFactsFragment())
        }
    }
}