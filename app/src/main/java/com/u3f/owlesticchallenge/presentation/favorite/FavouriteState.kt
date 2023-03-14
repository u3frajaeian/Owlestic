package com.u3f.owlesticchallenge.presentation.favorite

import com.u3f.owlesticchallenge.domain.model.error.NetworkError
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel

sealed class FavouriteState {
    class FavsFetched(val users: List<ProfileModel>) : FavouriteState()
    class InsertState(val id: Long) : FavouriteState()
    class Error(val error: NetworkError) : FavouriteState()
    object Loading : FavouriteState()
}
