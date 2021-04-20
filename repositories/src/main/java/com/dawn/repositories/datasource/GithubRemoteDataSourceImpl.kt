package com.dawn.repositories.datasource

import com.dawn.network.requestBlocking
import com.dawn.repositories.GitHubSearchApiService

class GithubRemoteDataSourceImpl(private val gitHubSearchApiService: GitHubSearchApiService) :
        GithubDataSource {
    override suspend fun getGitHubRepos(query: String) =
        gitHubSearchApiService.getRepoSearch(query).requestBlocking { it.toView() }

}