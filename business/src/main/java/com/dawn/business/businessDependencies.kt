package com.dawn.business

import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import org.koin.dsl.module


val useCasesDependencies = module {

    single {
        GetGitHubRepoUsecase(get())
    }
}