package com.u3f.owlesticchallenge.presentation.profile

import com.u3f.owlesticchallenge.domain.model.error.NetworkError
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel

sealed class ProfileState {
    class UserFetched(val user: ProfileModel) : ProfileState()
    class Error(val error: NetworkError) : ProfileState()
    object Loading : ProfileState()
}
