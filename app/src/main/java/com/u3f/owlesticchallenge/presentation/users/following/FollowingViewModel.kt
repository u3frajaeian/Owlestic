package com.u3f.owlesticchallenge.presentation.users.following

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.domain.usecase.users.following.GetFollowingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.u3f.owlesticchallenge.presentation.extension.asLiveData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val getFollowingUseCase: GetFollowingUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<FollowingState>()
    val state = _state.asLiveData()
    fun getFollowing(username:String) {

        getFollowingUseCase.execute(username).onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        FollowingState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(FollowingState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(FollowingState.FollowingFetched(it.data))
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

}