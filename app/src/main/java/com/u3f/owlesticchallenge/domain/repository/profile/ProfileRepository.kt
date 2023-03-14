package com.u3f.owlesticchallenge.domain.repository.profile

import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel


interface ProfileRepository {
    suspend fun getProfile(username:String): ProfileModel
    suspend fun insertFavs(user:ProfileModel):Long
}