package com.u3f.owlesticchallenge.data.remote.retrofit

import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.model.search.UserDataClass
import com.u3f.owlesticchallenge.domain.model.users.FollowerDataClass
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppRetrofitService {

    @GET("users/{username}")
    suspend fun getProfile(@Path("username") username: String): ProfileModel
    @GET("search/users")
    suspend fun getUsers(@Query("q") username: String): UserDataClass
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<FollowerDataClass>
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): List<FollowerDataClass>

}