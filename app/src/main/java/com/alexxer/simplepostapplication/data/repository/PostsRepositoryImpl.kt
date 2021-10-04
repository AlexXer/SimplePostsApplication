package com.alexxer.simplepostapplication.data.repository

import android.util.Log
import com.alexxer.simplepostapplication.data.db.dao.PostDao
import com.alexxer.simplepostapplication.data.db.dao.UserDao
import com.alexxer.simplepostapplication.data.db.entity.toDomain
import com.alexxer.simplepostapplication.data.network.model.toEntity
import com.alexxer.simplepostapplication.data.network.api.PostServiceApi
import com.alexxer.simplepostapplication.data.network.api.UserServiceApi
import com.alexxer.simplepostapplication.domain.model.UserPost
import com.alexxer.simplepostapplication.domain.repository.PostsRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsDao: PostDao,
    private val userDao: UserDao,
    private val postsServiceApi: PostServiceApi,
    private val usersServiceApi: UserServiceApi
) : PostsRepository {

    @FlowPreview
    override suspend fun getAllPosts(): Flow<Result<List<UserPost>>> =
        postsDao
            .getPosts()
            .map { it.map { userPostEntity -> userPostEntity.toDomain() } }
            .flatMapConcat { posts ->
                flow {
                    if (posts.isEmpty()) {
                        try {
                            refreshPosts()
                        } catch (ex: Exception) {
                            emit(Result.failure<List<UserPost>>(ex))
                        }
                    } else {
                        emit(Result.success(posts))
                    }
                }
            }

    override suspend fun refreshPosts() {
        val posts = postsServiceApi.getAllPosts()
        val userIds = posts.map { postResponse -> postResponse.userId }.toSortedSet()

        userIds
            .map { userId -> usersServiceApi.getUserById(userId) }
            .map { userDTO -> userDTO.toEntity() }
            .toList()
            .also { users -> userDao.insertUsers(users) }

        posts
            .map { postDTO -> postDTO.toEntity() }
            .also { postsDao.insertPosts(it) }
    }
}