package com.dawn.featuregithubsearch.features

import com.dawn.common.error.ErrorEntity
import com.dawn.common.mvi.ViewState
import com.dawn.dtos.gitHubSearch.GitHubRepoView

/**
 * This Defines the state of the our GitHubState, by reading this class we get to know that what will
 * be the state of our View
 */
sealed class GitHubState : ViewState {

    object Loading : GitHubState()
    data class ResultSearch(val reposData: GitHubRepoView) : GitHubState()
    data class Error(val error: ErrorEntity) : GitHubState()

}
