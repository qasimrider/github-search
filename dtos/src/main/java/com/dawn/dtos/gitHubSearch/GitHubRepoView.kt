package com.dawn.dtos.gitHubSearch

import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepoView(
    val repoList: List<RepoDetailsView>?,
    val totalCount: Int?
)


@Serializable
data class RepoDetailsView(val fullName: String?
, val description: String?
) {

}

@Serializable
data class OwnerView(val avatar: String?) {

}