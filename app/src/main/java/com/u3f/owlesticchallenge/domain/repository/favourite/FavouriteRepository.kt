package com.u3f.owlesticchallenge.domain.repository.favourite

import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel

interface FavouriteRepository {
    suspend fun getAllFavUsers(): List<ProfileModel>


}