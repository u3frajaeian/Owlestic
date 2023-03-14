package com.u3f.owlesticchallenge.presentation.users.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.u3f.owlesticchallenge.databinding.UsersListItemBinding
import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass
import com.u3f.owlesticchallenge.presentation.extension.load

internal class FollowersAdapter :
    RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    var users: List<FollowerDataClass> = arrayListOf()

    private var onClickListener: ((users: FollowerDataClass) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UsersListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setClickListener(listener: (user: FollowerDataClass) -> Unit) {
        this.onClickListener = listener
    }

    internal inner class ViewHolder(private val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: FollowerDataClass) {
            itemView.setOnClickListener { onClickListener?.invoke(user) }

            binding.tvTitle.text = user.login
            binding.imgAvatar.load(user.avatarUrl)

        }
    }
}


