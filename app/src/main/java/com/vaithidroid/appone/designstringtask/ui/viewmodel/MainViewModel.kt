package com.vaithidroid.appone.designstringtask.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vaithidroid.appone.designstringtask.data.UsersRepository
import com.vaithidroid.appone.designstringtask.models.Users
import com.vaithidroid.appone.designstringtask.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    application: Application
): AndroidViewModel(application) {

    var userResponse : MutableLiveData<NetworkResult<Users>> = MutableLiveData()
    private var users : Users? = null


    fun getUsers(pageNumber: Int) = viewModelScope.launch {
        val response = usersRepository.remote.getUsers(pageNumber)
        userResponse.value = handleUserResponse(response)
    }

    private fun handleUserResponse(response: Response<Users>): NetworkResult<Users> {
        if (response.isSuccessful){
            response.body()?.let {
                if (users == null){
                    users = it
                }
                else{
                    val oldUsers = users?.data
                    val newUsers = it.data
                    oldUsers?.addAll(newUsers)
                }
                return NetworkResult.Success(users ?: it)
            }
        }
        return NetworkResult.Error(response.message())
    }

}