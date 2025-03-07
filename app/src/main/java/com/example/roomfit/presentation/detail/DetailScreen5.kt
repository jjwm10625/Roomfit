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
import com.example.roomfit.presentation.viewmodel.ScrapViewModel
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle

// 방을 구해요에서 이어지는 사진 없는 게시물 상세 화면
@Composable
fun DetailScreen5(navController: NavController, scrapViewModel: ScrapViewModel) {
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

        DetailMateCard5(
            navController = navController,
            scrapViewModel = scrapViewModel,
            modifier = Modifier.padding(16.dp),
            userName = "전도연",
            postTitle = "서울대입구역 도보 5분이내 방 구합니다",
            postContent = "기숙사 모집에 떨어져서 글 남깁니다. " +
                    "\n3월 1일 입주 희망합니다. 계약 시기에 따라 일정은 조율 가능해요.",
            profileImageRes = R.drawable.dum_profile_5
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen5() {
    DetailScreen5(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel()
    )
}