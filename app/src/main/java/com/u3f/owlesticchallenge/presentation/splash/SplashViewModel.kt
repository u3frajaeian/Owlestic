package com.u3f.owlesticchallenge.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u3f.owlesticchallenge.presentation.navigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navManager: NavManager
) : ViewModel() {

    fun navigateToMainFragment() {
        navManager.navigate(SplashFragmentDirections.actionSplashFragmentToFragmentHome())
    }


}