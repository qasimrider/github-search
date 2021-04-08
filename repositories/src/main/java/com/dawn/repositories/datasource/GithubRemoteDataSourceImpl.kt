package com.dawn.repositories.datasource

import com.dawn.network.requestBlocking
import com.dawn.repositories.GitHubSearchApiService

class GithubRemoteDataSourceImpl(private val gitHubSearchApiService: GitHubSearchApiService) :
    GithubRemoteDataSource {
    override fun getGitHubRepos(query: String) =
        gitHubSearchApiService.getRepoSearch(query).requestBlocking { it.toView() }

}