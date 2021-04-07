package com.dawn.repositories.github

import com.dawn.repositories.datasource.GithubRemoteDataSource
import com.dawn.repositories.datasource.GithubRemoteDataSourceImpl

class GitHubRepositoryImpl(private val remoteDataSource: GithubRemoteDataSourceImpl) : GitHubRepository {
    override fun getGitHubRepos(query: String) = remoteDataSource.getGitHubRepos(query)
}