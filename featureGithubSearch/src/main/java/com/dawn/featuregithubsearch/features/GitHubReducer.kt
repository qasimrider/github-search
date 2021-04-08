package com.dawn.featuregithubsearch.features

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import com.dawn.dtos.gitHubSearch.GitHubRepoView


fun Either<ErrorEntity, GitHubRepoView>.reduce(): GitHubState {
    return when (this) {
        is Either.Left -> GitHubState.Error(error)
        is Either.Right -> GitHubState.ResultSearch(reponse.repoList!!)
    }
}