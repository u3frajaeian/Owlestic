package com.u3f.owlesticchallenge.presentation.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.u3f.owlesticchallenge.databinding.UsersListItemBinding
import com.u3f.owlesticchallenge.domain.model.search.UserModel
import com.u3f.owlesticchallenge.presentation.extension.load

internal class UsersAdapter :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var users: List<UserModel> = arrayListOf()

    private var onClickListener: ((users: UserModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UsersListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setClickListener(listener: (user: UserModel) -> Unit) {
        this.onClickListener = listener
    }

    internal inner class ViewHolder(private val binding: UsersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel) {
            itemView.setOnClickListener { onClickListener?.invoke(user) }

            binding.tvTitle.text = user.username
            binding.imgAvatar.load(user.avatarUrl)

        }
    }
}
