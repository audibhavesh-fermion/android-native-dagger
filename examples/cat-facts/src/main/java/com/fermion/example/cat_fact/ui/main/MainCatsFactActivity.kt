package com.fermion.example.cat_fact.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.fermion.android.base.view.BaseActivity
import com.fermion.android.dagger_processor.InjectView
import com.fermion.example.cat_fact.R
import com.fermion.example.cat_fact.databinding.ActivityMainCatsFactBinding


@InjectView
class MainCatsFactActivity :
    BaseActivity<ActivityMainCatsFactBinding, MainViewModel>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onStart() {
        super.onStart()

    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainCatsFactBinding
        get() = ActivityMainCatsFactBinding::inflate

//    override fun bindingInflater(): ActivityMainCatsFactBinding {
//        return ActivityMainCatsFactBinding.inflate(layoutInflater)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)


    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}