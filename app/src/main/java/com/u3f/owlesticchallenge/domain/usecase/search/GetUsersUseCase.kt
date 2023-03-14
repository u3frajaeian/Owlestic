package com.u3f.owlesticchallenge.domain.usecase.search

import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.base.util.ErrorConverter
import com.u3f.owlesticchallenge.domain.model.search.UserDataClass
import com.u3f.owlesticchallenge.domain.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    fun execute(username:String): Flow<Response<UserDataClass>> = flow {
        try {
            emit(Response.Loading)
            val users = searchRepository.getUsers(username)
            emit(Response.Success(users))
        } catch (e: HttpException) {
            emit(Response.Error(ErrorConverter.castAPIError(e)))
        } catch (e: IOException) {
            emit(Response.Error(ErrorConverter.castIOError(e)))
        }
    }
}