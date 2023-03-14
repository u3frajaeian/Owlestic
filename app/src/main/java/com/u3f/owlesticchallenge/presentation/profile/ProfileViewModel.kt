package com.u3f.owlesticchallenge.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.domain.model.profile.ProfileModel
import com.u3f.owlesticchallenge.domain.usecase.profile.InsertFavUseCase
import com.u3f.owlesticchallenge.domain.usecase.profile.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.u3f.owlesticchallenge.presentation.extension.asLiveData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getProfileUseCase: GetProfileUseCase,private val insertFavUseCase: InsertFavUseCase) : ViewModel() {
    private val _state = MutableLiveData<ProfileState>()
    val state = _state.asLiveData()
    fun getUser(username: String) {

        getProfileUseCase.execute(username).onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        ProfileState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(ProfileState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(ProfileState.UserFetched(it.data))
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

    fun addToFavourite(user: ProfileModel) {
        insertFavUseCase.execute(username = user).launchIn(viewModelScope)
    }
}