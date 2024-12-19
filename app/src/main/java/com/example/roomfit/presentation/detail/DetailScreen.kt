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
import com.example.roomfit.presentation.components.DetailMateCard
import com.example.roomfit.presentation.components.ImageGallery
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle
import com.example.roomfit.ScrapViewModel

@Composable
fun DetailScreen(navController: NavController, scrapViewModel: ScrapViewModel) {
    val imageList = listOf(
        R.drawable.roomimage,
        R.drawable.roomimage,
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
                onClick = { navController.popBackStack() },
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

        DetailMateCard(
            navController = navController,
            modifier = Modifier.padding(16.dp),
            userName = "김채현",
            postTitle = "17평형 숙대 정문 근처 룸 쉐어 구합니다",
            postContent = "저는 고양이를 키우고 있어서 털 알러지 없는 분들로 받겠습니다!",
            profileImageRes = R.drawable.dum_profile_1,
            scrapViewModel = scrapViewModel
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel()
    )
}