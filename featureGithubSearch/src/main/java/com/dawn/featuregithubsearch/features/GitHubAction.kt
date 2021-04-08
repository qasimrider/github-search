package com.dawn.featuregithubsearch.features

import com.dawn.common.mvi.ViewAction

sealed class GitHubAction : ViewAction {

    data class SearchCharacters(val query: String) : GitHubAction()

}
