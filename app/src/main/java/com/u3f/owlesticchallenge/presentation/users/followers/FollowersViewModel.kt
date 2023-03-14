package com.u3f.owlesticchallenge.presentation.users.followers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.domain.usecase.users.followers.GetFollowersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.u3f.owlesticchallenge.presentation.extension.asLiveData
import com.u3f.owlesticchallenge.presentation.users.following.FollowingState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<FollowersState>()
    val state = _state.asLiveData()
    fun getFollowers(username:String) {

        getFollowersUseCase.execute(username).onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        FollowersState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(FollowersState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(FollowersState.FollowersFetched(it.data))
                }
                else -> Unit
            }
        }.launchIn(viewModelScope)
    }

}