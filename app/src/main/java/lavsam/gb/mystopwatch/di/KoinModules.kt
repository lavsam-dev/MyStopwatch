package lavsam.gb.mystopwatch.di

import lavsam.gb.mystopwatch.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityViewModel = module {
    viewModel { MainActivityViewModel() }
}