package com.dawn.repositories

import com.dawn.dtos.serverObjects.GitHubRepos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubSearchApiService {

    @GET("/search/repositories")
     fun getRepoSearch(@Query("q") query: String): Call<GitHubRepos>
}