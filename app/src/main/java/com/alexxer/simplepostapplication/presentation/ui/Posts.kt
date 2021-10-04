package com.alexxer.simplepostapplication.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.alexxer.simplepostapplication.R
import com.alexxer.simplepostapplication.domain.model.UserPost
import com.alexxer.simplepostapplication.presentation.ui.viewmodel.PostsViewModel
import com.alexxer.simplepostapplication.ui.theme.OldPaper
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Posts(viewModel: PostsViewModel) {
    val isRefreshState = viewModel.isLoading.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshState.value),
        onRefresh = { viewModel.refreshPosts() }
    ) { Body(viewModel = viewModel) }
}

@Composable
fun Body(viewModel: PostsViewModel) {
    val allPosts = viewModel.allPosts.collectAsState()
    val isDialogOpenState = viewModel.isDialogOpen.collectAsState()

    Error(
        isDialogOpenState,
        { viewModel.onDialogHide() },
        { viewModel.refreshPosts() }
    )

    Success(
        posts = allPosts.value
    )
}

@Composable
fun Success(posts: List<UserPost>) {
    LazyColumn {
        items(posts) {
            Card(
                shape = RoundedCornerShape(6.dp),
                backgroundColor = OldPaper,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column()
                {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.ic_user_circle),
                            contentDescription = "User icon",
                            modifier = Modifier
                                .padding(top = 8.dp, start = 8.dp)
                                .size(50.dp)
                        )
                        Text(
                            text = it.name,
                            Modifier
                                .padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.Black
                        )
                    }

                    Text(
                        text = it.title,
                        Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun Error(
    isDialogOpenState: State<Boolean>,
    onDismiss: () -> Unit,
    onConfirmButtonClickListener: () -> Unit
) {

    if (isDialogOpenState.value)

        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = stringResource(id = R.string.error_dialog_title),
                    color = Color.Black
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.error_dialog_message),
                    color = Color.Black
                )
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text(
                        text = stringResource(id = R.string.hide_button_text),
                        color = Color.Black
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButtonClickListener()
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.retry_button_text),
                        color = Color.Black
                    )
                }
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        )
}