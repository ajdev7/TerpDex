package dev.marcosfarias.terpdex.di

import dev.marcosfarias.terpdex.ui.dashboard.DashboardViewModel
import dev.marcosfarias.terpdex.ui.generation.GenerationViewModel
import dev.marcosfarias.terpdex.ui.home.HomeViewModel
import dev.marcosfarias.terpdex.ui.terpdex.TerpdexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { GenerationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { TerpdexViewModel(get(), get()) }
}
