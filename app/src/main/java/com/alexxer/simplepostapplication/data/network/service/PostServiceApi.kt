package com.alexxer.simplepostapplication.data.network.service

import com.alexxer.simplepostapplication.data.network.model.PostDTO
import retrofit2.http.GET

interface PostServiceApi {
    @GET("/posts")
    suspend fun getAllPosts(): List<PostDTO>
}