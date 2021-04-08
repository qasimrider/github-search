package com.dawn.featuregithubsearch.features

import com.dawn.common.mvi.ViewIntent

sealed class GithubIntent : ViewIntent {

    data class SearchCharacter(val query: String) : GithubIntent()

}