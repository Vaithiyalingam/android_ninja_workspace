package com.vaithidroid.appone.designstringtask.network

import com.vaithidroid.appone.designstringtask.models.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("/api/users")
    suspend fun getUsers(
        @Query("page")
        pageNumber: Int = 1,
    ): Response<Users>

}