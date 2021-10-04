package com.alexxer.simplepostapplication.data.network.service

import com.alexxer.simplepostapplication.data.network.model.UserDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface UserServiceApi {
    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDTO
}