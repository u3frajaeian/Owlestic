package com.u3f.owlesticchallenge.domain.repository.search

import com.u3f.owlesticchallenge.domain.model.search.UserDataClass


interface SearchRepository {
    suspend fun getUsers(username:String): UserDataClass

}