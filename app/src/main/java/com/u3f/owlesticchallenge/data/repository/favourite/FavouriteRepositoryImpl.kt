package com.u3f.owlesticchallenge.data.repository.favourite

import com.u3f.owlesticchallenge.data.local.dao.users.UsersDao
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.repository.favourite.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val reposDao: UsersDao
) : FavouriteRepository {
    override suspend fun getAllFavUsers(): List<ProfileModel> {
        return reposDao.getAllFavUsers()
    }


}