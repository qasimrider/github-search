package com.dawn.featuregithubsearch.ui

import android.os.Bundle
import com.dawn.common.base.BaseFragment
import com.dawn.featuregithubsearch.R
import com.dawn.featuregithubsearch.viewModel.GitHubReposListViewModel

class GitHubReposListFragment : BaseFragment() {

    companion object {
        fun newInstance() = GitHubReposListFragment()
    }

    private lateinit var viewModel: GitHubReposListViewModel
    override fun layoutResourceId(): Int= R.layout.git_hub_repos_list_fragment

    override fun initialize(savedInstanceState: Bundle?) {
    }


}