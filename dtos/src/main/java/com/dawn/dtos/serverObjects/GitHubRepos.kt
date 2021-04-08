package com.dawn.dtos.serverObjects

import com.dawn.dtos.gitHubSearch.GitHubRepoView
import com.dawn.dtos.gitHubSearch.OwnerView
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
            RepoDetailsView(
                it.id!!,
                it.full_name,
                "",
                it.forks!!,
                it.open_issues!!,
                it.language,
                it.stargazers_count!!,
                OwnerView(it.owner?.avatar_url)
            )
        } else {
            it.toView()
        }
    }, total_count)
}