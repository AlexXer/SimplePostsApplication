package com.alexxer.simplepostapplication.di.modules

import com.alexxer.simplepostapplication.data.repository.PostsRepositoryImpl
import com.alexxer.simplepostapplication.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPostsRepository(impl: PostsRepositoryImpl): PostsRepository
}