// this is a autogenerated code
package com.fermion.example.cat_fact.di.generated

import com.fermion.example.cat_fact.ui.facts.FactsFragment
import com.fermion.example.cat_fact.ui.home.HomeFragment
import com.fermion.example.cat_fact.ui.main.MainCatsFactActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlin.Suppress

@Module
@Suppress("unused")
public interface FragmentBuilderModule {
  @ContributesAndroidInjector
  public fun contributeFactsFragmentModule(): FactsFragment

  @ContributesAndroidInjector
  public fun contributeHomeFragmentModule(): HomeFragment

  @ContributesAndroidInjector
  public fun contributeMainCatsFactActivityModule(): MainCatsFactActivity
}