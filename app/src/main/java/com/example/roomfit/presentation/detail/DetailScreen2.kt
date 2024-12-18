package com.example.roomfit.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.ScrapViewModel
import com.example.roomfit.presentation.components.DetailMateCard2
import com.example.roomfit.presentation.components.ImageGallery
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle

@Composable
fun DetailScreen2(navController: NavController, scrapViewModel: ScrapViewModel) {
    val imageList = listOf(
        R.drawable.roomimage2,
        R.drawable.roomimage2,
    )

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
                onClick = { navController.navigate("home") },
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
                text = "게시물",
                style = UserTitle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ImageGallery(imageList = imageList)

        DetailMateCard2(
            navController = navController,
            scrapViewModel = scrapViewModel,
            modifier = Modifier.padding(16.dp),
            userName = "조영서",
            postTitle = "홍대입구역 5분거리 룸 쉐어 구합니다",
            postContent = "투룸이라 1인실 사용 가능합니다. \n연락 주세요!",
            profileImageRes = R.drawable.dum_profile_2
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen2() {
    DetailScreen2(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel()
    )
}