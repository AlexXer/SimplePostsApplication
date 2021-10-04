package com.alexxer.simplepostapplication.di.component

import android.app.Application
import android.content.Context
import com.alexxer.simplepostapplication.di.modules.DatabaseModule
import com.alexxer.simplepostapplication.di.modules.InteractorModule
import com.alexxer.simplepostapplication.di.modules.NetworkModule
import com.alexxer.simplepostapplication.di.modules.RepositoryModule
import com.alexxer.simplepostapplication.domain.interactor.PostsInteractor
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        InteractorModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(application: Application)

    val postsInteractor: PostsInteractor

    @Component.Factory
    interface Builder {
        fun build(@BindsInstance context: Context): AppComponent
    }

    companion object {
        operator fun invoke(context: Context): AppComponent =
            DaggerAppComponent.factory().build(context)
    }
}