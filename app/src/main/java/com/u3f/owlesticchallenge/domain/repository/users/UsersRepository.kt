package com.u3f.owlesticchallenge.domain.repository.users

import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass


interface UsersRepository {
    suspend fun getFollowers(username:String): List<FollowerDataClass>
    suspend fun getFollowing(username:String): List<FollowerDataClass>

}