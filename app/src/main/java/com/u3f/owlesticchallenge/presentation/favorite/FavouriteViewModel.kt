package com.u3f.owlesticchallenge.presentation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.usecase.favourite.GetAllFavsUseCase
import com.u3f.owlesticchallenge.domain.usecase.profile.InsertFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.u3f.owlesticchallenge.presentation.extension.asLiveData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavsUseCase: GetAllFavsUseCase, private val insertFavsUseCase: InsertFavUseCase
) : ViewModel() {
    private val _state = MutableLiveData<FavouriteState>()
    val state = _state.asLiveData()
    fun getFavs() {

        getFavsUseCase.execute().onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        FavouriteState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(FavouriteState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(FavouriteState.FavsFetched(it.data))
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavs(user: ProfileModel) {

        insertFavsUseCase.execute(user).onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        FavouriteState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(FavouriteState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(FavouriteState.InsertState(it.data))
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }
}