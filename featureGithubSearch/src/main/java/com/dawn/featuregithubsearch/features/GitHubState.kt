package com.dawn.featuregithubsearch.features

import com.dawn.common.mvi.ViewState
import com.dawn.dtos.gitHubSearch.RepoDetailsView

sealed class GitHubState : ViewState {

    object Loading : GitHubState()
    data class ResultSearch(val data: List<RepoDetailsView>) : GitHubState()

}
