package com.u3f.owlesticchallenge.presentation.users.following

import com.u3f.owlesticchallenge.domain.model.error.NetworkError
import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass

sealed class FollowingState {
    class FollowingFetched(val users: List<FollowerDataClass>) : FollowingState()
    class Error(val error: NetworkError) : FollowingState()
    object Loading : FollowingState()
}
