package com.u3f.owlesticchallenge.presentation.users.followers

import com.u3f.owlesticchallenge.domain.model.error.NetworkError
import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass
import com.u3f.owlesticchallenge.presentation.users.following.FollowingState

sealed class FollowersState {
    class FollowersFetched(val users: List<FollowerDataClass>) : FollowersState()
    class Error(val error: NetworkError) : FollowersState()
    object Loading : FollowersState()
}
