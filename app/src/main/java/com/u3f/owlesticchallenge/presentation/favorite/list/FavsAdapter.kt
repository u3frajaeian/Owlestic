package com.u3f.owlesticchallenge.presentation.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.u3f.owlesticchallenge.databinding.FavouriteUsersListItemBinding
import com.u3f.owlesticchallenge.databinding.FavsListItemBinding
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.presentation.extension.load

internal class FavsAdapter :
    RecyclerView.Adapter<FavsAdapter.ViewHolder>() {

    var users: List<ProfileModel> = arrayListOf()

    private var onClickListener: ((users: ProfileModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavouriteUsersListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setClickListener(listener: (users: ProfileModel) -> Unit) {
        this.onClickListener = listener
    }

    internal inner class ViewHolder(private val binding: FavouriteUsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ProfileModel) {
            itemView.setOnClickListener { onClickListener?.invoke(user) }
            binding.tvUsername.text = user.name
            binding.tvLocation.text = user.location
            binding.tvCompany.text=user.company
            binding.imgAvatar.load(user.avatarUrl)
            binding.followerCount.text = user.followers.toString()
            binding.followingCount.text = user.following.toString()
            binding.repositoryCount.text = user.publicRepos.toString()
        }
    }
}
