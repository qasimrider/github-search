package com.dawn.featuregithubsearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseViewModel
import com.dawn.dtos.gitHubSearch.RepoDetailsView
import com.dawn.featuregithubsearch.features.GitHubAction
import com.dawn.featuregithubsearch.features.GitHubState
import com.dawn.featuregithubsearch.features.GithubIntent
import com.dawn.featuregithubsearch.features.reduce

class GitHubReposListViewModel(private val getGitHubRepoUsecase: GetGitHubRepoUsecase) :
    BaseViewModel<GithubIntent, GitHubAction, GitHubState>() {

    //region Live Data
    private val _reposList = MutableLiveData<List<RepoDetailsView>>()
    val reposList: LiveData<List<RepoDetailsView>>
        get() = _reposList
    //endregion

    //region Data Fetch
    private fun getRepoSearchResult(params: GetGitHubRepoUsecase.Params) {
        mState.postValue(GitHubState.Loading)
        getGitHubRepoUsecase(viewModelScope, params) { response ->
            mState.postValue(response.reduce(_reposList))

        }
    }
    //endregion

    //region State Management
    override fun intentToAction(intent: GithubIntent): GitHubAction {
        return when (intent) {
            is GithubIntent.SearchCharacter -> GitHubAction.SearchCharacters(intent.query)
        }
    }

    override fun handleAction(action: GitHubAction) {
        when (action) {
            is GitHubAction.SearchCharacters ->
                getRepoSearchResult(GetGitHubRepoUsecase.Params(action.query))
        }
    }
    //endregion
}