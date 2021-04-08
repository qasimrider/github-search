package com.dawn.dtos.gitHubSearch

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class GitHubRepoView(
    val repoList: List<RepoDetailsView>,
    val totalCount: Int?
) : Parcelable


@Parcelize
data class RepoDetailsView(val id : Int, val fullName: String?, val description: String? ,val forks : Int,
                           val issuesCount : Int,val language: String?,val starredCount : Int,
                           val owner : OwnerView) : Parcelable
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

@Parcelize
data class OwnerView(val avatar: String?) : Parcelable