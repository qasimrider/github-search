package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseFragment
import com.dawn.common.extensions.sharedGraphViewModel
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel

class GitHubReposListFragment : BaseFragment() {

    //region Koin Injects
    private val viewModel by sharedGraphViewModel<GitHubReposListViewModel>(R.id.githubSearchNavigation)

    //endregion

    //region Overrides
    override fun layoutResourceId(): Int= R.layout.git_hub_repos_list_fragment

    override fun initialize(savedInstanceState: Bundle?) {

        viewModel.getRepoSearchResult(GetGitHubRepoUsecase.Params("qasd"))
    }

    //endregion


}