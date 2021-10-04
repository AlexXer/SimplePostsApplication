package com.alexxer.simplepostapplication.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexxer.simplepostapplication.domain.interactor.PostsInteractor
import com.alexxer.simplepostapplication.domain.model.UserPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostsViewModel(private val interactor: PostsInteractor) : ViewModel() {

    init {
        collectPosts()
    }

    private val _allPosts: MutableStateFlow<List<UserPost>> = MutableStateFlow(emptyList())
    val allPosts: StateFlow<List<UserPost>> = _allPosts.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isDialogOpen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen.asStateFlow()

    fun refreshPosts() {
        viewModelScope.launch {
            _isLoading.emit(true)
            _isDialogOpen.emit(false)
            runCatching { interactor.refreshPosts() }
                .onFailure {
                    _isLoading.emit(false)
                    _isDialogOpen.emit(true)
                }
        }
    }

    fun onDialogHide() {
        viewModelScope.launch {
            _isDialogOpen.emit(false)
        }
    }

    private fun collectPosts() {
        viewModelScope.launch {
            interactor
                .getPosts()
                .catch { _isDialogOpen.emit(true) }
                .collect { result ->
                    result
                        .onSuccess {
                            _isLoading.emit(false)
                            _allPosts.emit(it)
                        }
                        .onFailure {
                            _isLoading.emit(false)
                            _isDialogOpen.emit(true)
                        }
                }
        }
    }
}