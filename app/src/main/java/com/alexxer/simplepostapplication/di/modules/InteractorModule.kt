package com.alexxer.simplepostapplication.di.modules

import com.alexxer.simplepostapplication.domain.interactor.PostsInteractor
import com.alexxer.simplepostapplication.domain.interactor.PostsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindPostsUseCase(impl: PostsInteractorImpl): PostsInteractor
}