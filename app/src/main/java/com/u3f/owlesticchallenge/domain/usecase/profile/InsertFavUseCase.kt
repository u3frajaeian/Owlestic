package com.u3f.owlesticchallenge.domain.usecase.profile


import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.base.util.ErrorConverter
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertFavUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    fun execute(username:ProfileModel): Flow<Response<Long>> = flow {
        try {
            emit(Response.Loading)
            val userId = profileRepository.insertFavs(username)
            emit(Response.Success(userId))
        } catch (e: HttpException) {
            emit(Response.Error(ErrorConverter.castAPIError(e)))
        } catch (e: IOException) {
            emit(Response.Error(ErrorConverter.castIOError(e)))
        }
    }
}