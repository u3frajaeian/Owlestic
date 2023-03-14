package com.u3f.owlesticchallenge.data.repository.profile

import android.provider.ContactsContract.Profile
import com.u3f.owlesticchallenge.data.local.dao.users.UsersDao
import com.u3f.owlesticchallenge.data.remote.retrofit.AppRetrofitService
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
private val appRetrofitService: AppRetrofitService,
private val usersDao: UsersDao
): ProfileRepository {
    override suspend fun getProfile(username:String): ProfileModel {
        return appRetrofitService.getProfile(username)
    }

    override suspend fun insertFavs(user: ProfileModel): Long {
       return usersDao.insertFav(user)
    }
}