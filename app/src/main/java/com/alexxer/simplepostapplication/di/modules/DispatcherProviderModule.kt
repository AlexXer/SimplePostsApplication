package com.alexxer.simplepostapplication.di.modules

import com.alexxer.simplepostapplication.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersProviderModule {

    @Provides
    fun providesDispatchersProvider():DispatchersProvider =
        object : DispatchersProvider {
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }
}