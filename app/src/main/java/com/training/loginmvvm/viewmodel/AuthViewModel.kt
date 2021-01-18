package com.training.loginmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.training.loginmvvm.datasources.remote.Resource
import com.training.loginmvvm.repository.AuthRepository
import com.training.loginmvvm.model.responses.LoginResponse
import kotlinx.coroutines.launch

/****************************************************
 * Created by Indra Muliana (indra.ndra26@gmail.com)
 * On Thursday, 14/01/2021 16.40
 * https://gitlab.com/indra-yana
 ****************************************************/

class AuthViewModel(private val repository: AuthRepository) : BaseViewModel(repository) {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>> get() = _loginResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResponse.value = Resource.Loading
            _loginResponse.value = repository.login(email, password)
        }
    }

    suspend fun saveAuthToken(authToken: String) {
        repository.saveAuthToken(authToken)
    }

}