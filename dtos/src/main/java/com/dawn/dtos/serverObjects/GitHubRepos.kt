package com.dawn.dtos.serverObjects

import com.dawn.dtos.gitHubSearch.GitHubRepoView
import com.dawn.dtos.gitHubSearch.RepoDetailsView
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepos(
    val incomplete_results: Boolean?,
    val items: List<RepoDetails>?,
    val total_count: Int?
) {
    fun toView() = GitHubRepoView(items!!.map {
        if (it.description == null) {
            RepoDetailsView(it.full_name, "")
        } else {
            it.toView()
        }
    }, total_count)
}