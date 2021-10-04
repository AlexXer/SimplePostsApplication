package com.alexxer.simplepostapplication.domain.interactor

import com.alexxer.simplepostapplication.core.dispatchers.DispatchersProvider
import com.alexxer.simplepostapplication.data.repository.PostsRepositoryImpl
import com.alexxer.simplepostapplication.domain.model.UserPost
import com.alexxer.simplepostapplication.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsInteractorImpl @Inject constructor(
    private val repository: PostsRepository,
    private val dispatchers: DispatchersProvider
):PostsInteractor {

    override suspend fun getPosts(): Flow<Result<List<UserPost>>> =
        withContext(dispatchers.io) { repository.getAllPosts() }

    override suspend fun refreshPosts() {
        withContext(dispatchers.io) { repository.refreshPosts()}
    }
}