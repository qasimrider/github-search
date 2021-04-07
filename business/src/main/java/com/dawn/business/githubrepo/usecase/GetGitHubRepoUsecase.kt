package com.dawn.business.githubrepo.usecase

import com.dawn.common.base.BaseUseCase
import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import com.dawn.dtos.gitHubSearch.GitHubRepoView
import com.dawn.repositories.github.GitHubRepository
import com.dawn.repositories.github.GitHubRepositoryImpl

class GetGitHubRepoUsecase(val gitHubRepository: GitHubRepositoryImpl) : BaseUseCase<GitHubRepoView,GetGitHubRepoUsecase.Params>() {


    data class Params (val query : String)

    override suspend fun run(param: Params): Either<ErrorEntity, GitHubRepoView> {
        return gitHubRepository.getGitHubRepos(param.query)
    }

}

