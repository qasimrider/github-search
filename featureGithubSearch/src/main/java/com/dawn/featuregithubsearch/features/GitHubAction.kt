package com.dawn.featuregithubsearch.features

import com.dawn.common.mvi.ViewAction

/**
 * Here we define the name of the the action and its behaviour taken when the intention(Intent) is changed
 * for understanding our intent and action both have same classes.
 */
sealed class GitHubAction : ViewAction {

    data class SearchCharacters(val query: String) : GitHubAction()

}
