package com.dawn.featuregithubsearch.features

import com.dawn.common.mvi.ViewIntent


/**
 * MVI (Intent)
 *
 * It provides the intention of the GithubIntent intents like Search Characters
 */
sealed class GithubIntent : ViewIntent {

    data class SearchCharacter(val query: String) : GithubIntent()

}