package com.dawn.repositories.github

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import com.dawn.dtos.gitHubSearch.GitHubRepoView

interface GitHubRepository {

    suspend fun getGitHubRepos(query: String): Either<ErrorEntity, GitHubRepoView>
}