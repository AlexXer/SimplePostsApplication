package com.alexxer.simplepostapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.alexxer.simplepostapplication.app.App
import com.alexxer.simplepostapplication.presentation.ui.viewmodel.PostsViewModel
import com.alexxer.simplepostapplication.presentation.ui.viewmodel.fragmentViewModelProvider
import com.alexxer.simplepostapplication.ui.theme.SimplePostApplicationTheme

class PostsFragment : Fragment() {

    private val viewModel by fragmentViewModelProvider {
        (requireActivity().applicationContext as App).appComponent
            .run { PostsViewModel(postsInteractor) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        ComposeView(requireContext())
            .apply {
                setContent {
                    SimplePostApplicationTheme {
                        Surface {
                            Posts(viewModel = viewModel)
                        }
                    }
                }
            }
}