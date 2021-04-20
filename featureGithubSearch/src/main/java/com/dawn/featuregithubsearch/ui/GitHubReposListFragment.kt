package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.dawn.common.base.BaseFragment
import com.dawn.common.base.BaseViewModel
import com.dawn.common.base.GeneralAdapter
import com.dawn.common.extensions.*
import com.dawn.dtos.gitHubSearch.GitHubRepoView
import com.dawn.dtos.gitHubSearch.RepoDetailsView
import com.dawn.featuregithubsearch.BR
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.databinding.GitHubReposListFragmentBinding
import com.dawn.featuregithubsearch.features.GitHubAction
import com.dawn.featuregithubsearch.features.GitHubState
import com.dawn.featuregithubsearch.features.GithubIntent
import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel
import com.google.android.material.transition.MaterialElevationScale

class GitHubReposListFragment : BaseFragment<GithubIntent, GitHubAction, GitHubState>() {


    //region Props
    private var previousText: String = ""
    private lateinit var viewBinding: GitHubReposListFragmentBinding
    private val adapter = GeneralAdapter(BR.repo, R.layout.repos_item, RepoDetailsView.DIFF_CALLBACK)
    //endregion

    //region Koin Injects
    private val viewModel by sharedGraphViewModel<GitHubReposListViewModel>(R.id.githubSearchNavigation)
    //endregion

    //region Overrides
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    override fun layoutResourceId(): Int = R.layout.git_hub_repos_list_fragment

    override fun initialize(savedInstanceState: Bundle?) {
        attachObservers()
        viewBinding = binding as GitHubReposListFragmentBinding
        progressBar = viewBinding.progressBar
        viewBinding.viewModel = viewModel
        viewBinding.adapter = adapter
    }

    override fun getViewModel(): BaseViewModel<GithubIntent, GitHubAction, GitHubState> = viewModel

    override fun attachListeners() {
        super.attachListeners()
        viewBinding.searchEditText.requestFocus()

        viewBinding.searchButton.setOnClickListener {
            viewBinding.searchEditText.run {
                if (text.toString() != previousText) {
                    text?.let {
                        it.length
                                .requireSize()
                                .runIfTrue {
                                    previousText = text.toString()
                                    dispatchIntent(GithubIntent.SearchCharacter(text.toString()))
                                }
                    }
                }
            }
        }

        adapter.clickListener = { repo, view ->

            exitTransition = MaterialElevationScale(true).apply {
                duration = 400L
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 400L
            }
            val repoItemDetailTransition = getString(R.string.repo_item_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to repoItemDetailTransition)
            findNavController().navigate(
                    GitHubReposListFragmentDirections.toGitHubDetail(repo), extras
            )
        }
    }

    //endregion

    //region Observers
    private fun attachObservers() {
        viewModel.run {
            fault(errorEntity, ::handleFailure)

            observe(state) { state ->
                showProgress(state is GitHubState.Loading, state is GitHubState.Loading)
                when (state) {
                    is GitHubState.ResultSearch -> {
                        populateList(state.reposData)
                    }
                    is GitHubState.Error -> handleFailure(state.error)
                    else -> throw IllegalStateException(getString(R.string.state_error))
                }.exhaustive
            }

        }
    }


    //endregion

    //region Recycler View Population
    private fun populateList(reposData: GitHubRepoView) {
        if (reposData.repoList.isNotEmpty()) {
            viewBinding.resultCount.visible()
            viewBinding.resultCount.text =
                    getString(R.string.results, reposData.totalCount.toString())
            viewBinding.typeSearchMessage.gone()
            adapter.submitList(reposData.repoList)
        } else {
            viewBinding.resultCount.gone()
            viewBinding.typeSearchMessage.visible()
            viewBinding.typeSearchMessage.text = getString(R.string.no_match_found)
        }
    }
    //endregion

}