package com.alexxer.simplepostapplication.domain.repository

import com.alexxer.simplepostapplication.domain.model.UserPost
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getAllPosts(): Flow<Result<List<UserPost>>>

    suspend fun refreshPosts()
}