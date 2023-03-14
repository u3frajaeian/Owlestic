package com.u3f.owlesticchallenge.domain.usecase.favourite


import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.base.util.ErrorConverter
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.repository.favourite.FavouriteRepository
import com.u3f.owlesticchallenge.domain.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllFavsUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    fun execute(): Flow<Response<List<ProfileModel>>> = flow {
        try {
            emit(Response.Loading)
            val user = favouriteRepository.getAllFavUsers()
            emit(Response.Success(user))
        } catch (e: HttpException) {
            emit(Response.Error(ErrorConverter.castAPIError(e)))
        } catch (e: IOException) {
            emit(Response.Error(ErrorConverter.castIOError(e)))
        }
    }.flowOn(Dispatchers.IO)
}