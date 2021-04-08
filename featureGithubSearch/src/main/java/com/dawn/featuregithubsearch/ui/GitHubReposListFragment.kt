package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.dawn.business.githubrepo.usecase.GetGitHubRepoUsecase
import com.dawn.common.base.BaseFragment
import com.dawn.common.base.BaseViewModel
import com.dawn.common.base.GeneralAdapter
import com.dawn.common.extensions.*
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
    private val adapter =
        GeneralAdapter(BR.repo, R.layout.repos_item, RepoDetailsView.DIFF_CALLBACK)
    //endregion

    //region Koin Injects
    private val viewModel by sharedGraphViewModel<GitHubReposListViewModel>(R.id.githubSearchNavigation)
    //endregion

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

        viewBinding.searchEditText.doOnTextChanged { text, _, _, _ ->
            Log.d(TAG, "attachListeners: ")
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

        adapter.clickListener = { repo, view ->

            exitTransition = MaterialElevationScale(false).apply {
                duration = 400L
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 400L
            }
            val repoItemDetailTransition = getString(R.string.repo_item_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to repoItemDetailTransition)
            findNavController().navigate(
                GitHubReposListFragmentDirections.toGitHubDetail(repo),
                extras
            )
        }
    }

    //endregion

    //region Observers
    private fun attachObservers() {
        viewModel.run {
//            observe(searchReposList) {
//
//            }
            fault(errorEntity, ::handleFailure)

            observe(state) { state ->

                showProgress(state is GitHubState.Loading, state is GitHubState.Loading)

                when (state) {

                    is GitHubState.ResultSearch -> {
                        adapter.submitList(state.repoList)
                    }
                    is GitHubState.Error -> handleFailure(state.error)
                }
            }

        }
    }
    //endregion

}