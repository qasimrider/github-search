package com.dawn.repositories.datasource

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import com.dawn.dtos.gitHubSearch.GitHubRepoView

interface GithubDataSource {

    fun getGitHubRepos(query: String): Either<ErrorEntity, GitHubRepoView>

}