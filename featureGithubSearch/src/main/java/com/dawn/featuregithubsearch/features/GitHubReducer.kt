package com.dawn.featuregithubsearch.features

import androidx.lifecycle.MutableLiveData
import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import com.dawn.dtos.gitHubSearch.GitHubRepoView
import com.dawn.dtos.gitHubSearch.RepoDetailsView


fun Either<ErrorEntity, GitHubRepoView>.reduce(repoList: MutableLiveData<List<RepoDetailsView>>): GitHubState {
    return when (this) {
        is Either.Left -> GitHubState.Error(error)
        is Either.Right -> {
            repoList.postValue(reponse.repoList)
            GitHubState.ResultSearch(reponse.repoList!!)
        }
    }
}