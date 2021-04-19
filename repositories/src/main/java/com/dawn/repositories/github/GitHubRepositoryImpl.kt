package com.dawn.repositories.github

import com.dawn.repositories.datasource.GithubRemoteDataSourceImpl

class GitHubRepositoryImpl(private val remoteDataSource: GithubRemoteDataSourceImpl) :
    GitHubRepository {
    override suspend fun getGitHubRepos(query: String) = remoteDataSource.getGitHubRepos(query)
}