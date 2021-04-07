package com.dawn.featuregithubsearch

import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val gitHubDependencies = module {
    viewModel { GitHubReposListViewModel(get()) }
}