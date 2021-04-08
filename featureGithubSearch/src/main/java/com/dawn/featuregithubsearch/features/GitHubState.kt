package com.dawn.featuregithubsearch.features

import com.dawn.common.error.ErrorEntity
import com.dawn.common.mvi.ViewState
import com.dawn.dtos.gitHubSearch.RepoDetailsView

sealed class GitHubState : ViewState {

    object Loading : GitHubState()
    data class ResultSearch(val repoList: List<RepoDetailsView>) : GitHubState()
    data class Error(val error: ErrorEntity) : GitHubState()

}
