package com.u3f.owlesticchallenge.presentation.users.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.u3f.owlesticchallenge.R
import com.u3f.owlesticchallenge.base.delegate.viewBinding
import com.u3f.owlesticchallenge.base.extension.toast
import com.u3f.owlesticchallenge.databinding.FragmentFollowerBinding
import com.u3f.owlesticchallenge.presentation.extension.observe
import com.u3f.owlesticchallenge.presentation.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import com.u3f.owlesticchallenge.presentation.navigation.NavManager
import com.u3f.owlesticchallenge.presentation.users.following.FollowingState
import com.u3f.owlesticchallenge.presentation.users.following.FollowingViewModel
import com.u3f.owlesticchallenge.presentation.users.list.FollowersAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FollowersFragment(val username: String) : Fragment(R.layout.fragment_follower) {

    private val binding: FragmentFollowerBinding by viewBinding()
    private val viewModel: FollowersViewModel by viewModels()

    private val followersAdapter: FollowersAdapter by lazy { FollowersAdapter() }

    @Inject
    lateinit var navManager: NavManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.state, stateObserver)
        initView()
        viewModel.getFollowers(username)


    }

    private val stateObserver = Observer<FollowersState> { state ->
        binding.shimmer.visible = state is FollowersState.Loading
        when (state) {
            is FollowersState.FollowersFetched -> {
                followersAdapter.users = state.users
                followersAdapter.notifyDataSetChanged()
            }

            is FollowersState.Error -> {
                toast(state.error.message)
            }

            else -> Unit
        }
    }

    private fun initView() {

        binding.rvRepos.apply {
            adapter = followersAdapter
        }
    }



}