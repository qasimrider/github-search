package com.dawn.dtos.gitHubSearch

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.dawn.dtos.serverObjects.Owner
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepoView(
    val repoList: List<RepoDetailsView>?,
    val totalCount: Int?
)


@Serializable
data class RepoDetailsView(val id : Int, val fullName: String?, val description: String? , val owner : OwnerView)
{

        companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<RepoDetailsView> =
            object : DiffUtil.ItemCallback<RepoDetailsView>() {
                override fun areItemsTheSame(
                    @NonNull oldItem: RepoDetailsView,
                    @NonNull newItem: RepoDetailsView
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    @NonNull oldItem: RepoDetailsView,
                    @NonNull newItem: RepoDetailsView
                ): Boolean {
                    return oldItem == newItem
                }
            }



    }
}

@Serializable
data class OwnerView(val avatar: String?)