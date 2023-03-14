package com.u3f.owlesticchallenge.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.base.common.Response
import com.u3f.owlesticchallenge.domain.model.search.UserModel
import com.u3f.owlesticchallenge.domain.usecase.search.GetUsersUseCase
import com.u3f.owlesticchallenge.presentation.extension.asLiveData
import com.u3f.owlesticchallenge.presentation.navigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val navManager: NavManager,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<SearchState>()
    val state = _state.asLiveData()

    fun searchUser( username:String){
        getUsersUseCase.execute(username).onEach {
            when (it) {
                is Response.Error -> {
                    _state.postValue(
                        SearchState.Error(error = it.error)
                    )
                }
                is Response.Loading -> {
                    _state.postValue(SearchState.Loading)
                }
                is Response.Success -> {
                    _state.postValue(SearchState.UserFetched(it.data))
                }

            }

        }.launchIn(viewModelScope)
    }


    fun navigateToProfilePage(user: UserModel) {
        navManager.navigate(SearchFragmentDirections.actionFragmentHomeToProfileFragment(user))
    }


}