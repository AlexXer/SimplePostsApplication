package com.alexxer.simplepostapplication.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {

    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
