package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.dawn.common.base.BaseFragment
import com.dawn.common.base.BaseViewModel
import com.dawn.common.extensions.popBackStack
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.databinding.GitHubRepoDetailFragmentBinding
import com.dawn.featuregithubsearch.BR
import com.dawn.featuregithubsearch.features.GitHubAction
import com.dawn.featuregithubsearch.features.GitHubState
import com.dawn.featuregithubsearch.features.GithubIntent

class GitHubRepoDetailFragment : BaseFragment<GithubIntent, GitHubAction, GitHubState>() {

    //region Props
    private lateinit var viewBinding: GitHubRepoDetailFragmentBinding
    private val args: GitHubRepoDetailFragmentArgs by navArgs()


    //endregion

    //region Overrides

    override fun getViewModel(): BaseViewModel<GithubIntent, GitHubAction, GitHubState> {
        TODO("Not yet implemented")
    }
    override fun layoutResourceId() = R.layout.git_hub_repo_detail_fragment

    override fun initialize(savedInstanceState: Bundle?) {
        viewBinding = binding as GitHubRepoDetailFragmentBinding
        args.also {
            viewBinding.apply {

                setVariable(BR.repo, it.repoDetails)
//                repo = it.repoDetails
            }

        }
    }

    override fun attachListeners() {
        super.attachListeners()
        viewBinding.apply {
            backBtn.setOnClickListener {
                popBackStack()
            }
        }
    }


    //endregion


}