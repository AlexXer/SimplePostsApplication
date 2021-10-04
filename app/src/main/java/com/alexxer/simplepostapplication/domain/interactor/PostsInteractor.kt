package com.alexxer.simplepostapplication.domain.interactor

import com.alexxer.simplepostapplication.domain.model.UserPost
import kotlinx.coroutines.flow.Flow

interface PostsInteractor {
    suspend fun getPosts(): Flow<Result<List<UserPost>>>

    suspend fun refreshPosts()
}