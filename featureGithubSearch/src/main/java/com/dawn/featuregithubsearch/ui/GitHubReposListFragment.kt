package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseFragment
import com.dawn.common.base.GeneralAdapter
import com.dawn.common.extensions.fault
import com.dawn.common.extensions.observe
import com.dawn.common.extensions.sharedGraphViewModel
import com.dawn.dtos.gitHubSearch.RepoDetailsView
import com.dawn.featuregithubsearch.BR
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.databinding.GitHubReposListFragmentBinding
import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel

class GitHubReposListFragment : BaseFragment() {


    //region Props
    override var shouldBindData: Boolean = true
    private val adapter =  GeneralAdapter(BR.repo,R.layout.repos_item,RepoDetailsView.DIFF_CALLBACK)
    //endregion

    //region Koin Injects
    private val viewModel by sharedGraphViewModel<GitHubReposListViewModel>(R.id.githubSearchNavigation)
    //endregion

    //region Overrides
    override fun layoutResourceId(): Int = R.layout.git_hub_repos_list_fragment

    override fun initialize(savedInstanceState: Bundle?) {

        attachObservers()

       val viewBinding =  binding as GitHubReposListFragmentBinding
        viewBinding.viewModel = viewModel
        viewBinding.adapter = adapter
//        viewBinding.reposRv.configureVerticalList(adapter)

        viewModel.getRepoSearchResult(GetGitHubRepoUsecase.Params("qasd"))
    }

    override fun attachListeners() {
        super.attachListeners()

        adapter.clickListener = {repo,view ->


        }


    }

    //endregion


    //region Observers
    fun attachObservers() {
        viewModel.run {
            observe(searchReposList) {

            }
            fault(errorEntity, ::handleFailure)

        }
    }
    //endregion


}