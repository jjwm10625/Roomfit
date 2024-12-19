package com.example.roomfit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.PostItem
import com.example.roomfit.PostViewModel
import com.example.roomfit.R
import com.example.roomfit.presentation.components.MyPostCard
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle
import com.gdg.kakaobank.presentation.navigator.RoomNav

@Composable
fun MyPostScreen(
    navController: NavController,
    postViewModel: PostViewModel = viewModel()
) {
    val posts = postViewModel.posts

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        // 상단 제목 및 뒤로가기 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(OffWhite)
                .padding(vertical = 8.dp)
        ) {
            IconButton(
                onClick = { navController.navigate(RoomNav.User.route) },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backbutton),
                    contentDescription = "Back Button"
                )
            }
            Text(
                text = "내 게시글",
                style = UserTitle,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // 내 게시글 목록
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundBeige)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            posts.forEach { post: PostItem ->
                MyPostCard(
                    navController = navController,
                    mateorroomText = post.mateorroom,
                    imageUri = post.imageUri,
                    titleText = post.title,
                    contentText = post.content,
                    locationText = post.location,
                    onDelete = { postViewModel.deletePost(post) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun MyPostScreenPreview() {
    MyPostScreen(navController = rememberNavController())
}