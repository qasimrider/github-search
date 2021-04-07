package com.dawn.featuregithubsearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseViewModel
import com.dawn.dtos.gitHubSearch.GitHubRepoView

class GitHubReposListViewModel(private val getGitHubRepoUsecase: GetGitHubRepoUsecase) :
    BaseViewModel() {


    //region Live Data
    private val _searchReposList = MutableLiveData<GitHubRepoView>()
    private val searchReposList: LiveData<GitHubRepoView>
        get() = _searchReposList
    //endregion

    fun getRepoSearchResult(params: GetGitHubRepoUsecase.Params) {

        getGitHubRepoUsecase(viewModelScope, params) { response ->
            response.either(::handleFailure) { success ->
                _searchReposList.apply {
                    this.value = success
                }

            }

        }


    }

}