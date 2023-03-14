package com.u3f.owlesticchallenge.data.repository.users

import com.u3f.owlesticchallenge.data.remote.retrofit.AppRetrofitService
import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass
import com.u3f.owlesticchallenge.domain.repository.users.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val appRetrofitService: AppRetrofitService,
) : UsersRepository {
    //    override suspend fun getRepos(): List<ReposModel> {
//        val repos = appRetrofitService.getRepos("jadijadi")
//        val faves = reposDao.getAllFavs()
//        repos.forEach {
//            faves.forEach { favItem ->
//                if (favItem.id == it.id)
//                    it.fav = true
//            }
//
//
//        }
//        return repos
//
//    }


    override suspend fun getFollowers(username: String): List<FollowerDataClass> {
        return appRetrofitService.getFollowers(username)
    }

    override suspend fun getFollowing(username: String): List<FollowerDataClass> {
        return appRetrofitService.getFollowing(username)
    }


}