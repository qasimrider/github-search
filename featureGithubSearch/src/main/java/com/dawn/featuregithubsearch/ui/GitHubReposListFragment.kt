package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.doOnTextChanged
import androidx.viewbinding.ViewBinding
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseFragment
import com.dawn.common.base.GeneralAdapter
import com.dawn.common.extensions.*
import com.dawn.dtos.gitHubSearch.RepoDetailsView
import com.dawn.featuregithubsearch.BR
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.databinding.GitHubReposListFragmentBinding
import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel

class GitHubReposListFragment : BaseFragment() {


    //region Props
    private lateinit var viewBinding: GitHubReposListFragmentBinding
    override var shouldBindData: Boolean = true
    private val adapter =
        GeneralAdapter(BR.repo, R.layout.repos_item, RepoDetailsView.DIFF_CALLBACK)
    //endregion

    //region Koin Injects
    private val viewModel by sharedGraphViewModel<GitHubReposListViewModel>(R.id.githubSearchNavigation)
    //endregion

    //region Overrides
    override fun layoutResourceId(): Int = R.layout.git_hub_repos_list_fragment

    override fun initialize(savedInstanceState: Bundle?) {

        attachObservers()

        viewBinding = binding as GitHubReposListFragmentBinding
        viewBinding.viewModel = viewModel
        viewBinding.adapter = adapter
//        viewBinding.reposRv.configureVerticalList(adapter)

//        viewModel.getRepoSearchResult(GetGitHubRepoUsecase.Params("qasd"))
    }


    override fun attachListeners() {
        super.attachListeners()


        viewBinding.searchEditText.doOnTextChanged { text, _, _, _ ->
            Log.d(TAG, "attachListeners: ")

            text?.let {
                it.length
                    .requireSize()
                    .runIfTrue { viewModel.getRepoSearchResult(GetGitHubRepoUsecase.Params(text.toString())) }
            }

        }

        adapter.clickListener = { repo, view ->


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