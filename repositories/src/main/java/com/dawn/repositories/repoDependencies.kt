package com.dawn.repositories
import com.dawn.repositories.datasource.GithubRemoteDataSourceImpl
import com.dawn.repositories.github.GitHubRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repoDependencies = module {

    //retrofit
    single { get<Retrofit>().create(GitHubSearchApiService::class.java) }

    //repositories
    single { GitHubRepositoryImpl(get()) }

    //dataSource
    single { GithubRemoteDataSourceImpl(get()) }


}

