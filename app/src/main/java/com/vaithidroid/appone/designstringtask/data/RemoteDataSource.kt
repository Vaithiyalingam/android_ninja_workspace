package com.vaithidroid.appone.designstringtask.data

import com.vaithidroid.appone.designstringtask.models.Users
import com.vaithidroid.appone.designstringtask.network.UsersApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val usersApi: UsersApi
) {

    suspend fun getUsers(pageNumber: Int): Response<Users>{
        return usersApi.getUsers(pageNumber)
    }

}