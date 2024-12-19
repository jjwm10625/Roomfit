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
import com.example.roomfit.presentation.components.DetailMateCard4
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle

// 방을 구해요에서 이어지는 사진 없는 게시물 상세 화면
@Composable
fun DetailScreen4(navController: NavController, scrapViewModel: ScrapViewModel) {
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

        DetailMateCard4(
            navController = navController,
            scrapViewModel = scrapViewModel,
            modifier = Modifier.padding(16.dp),
            userName = "김민지",
            postTitle = "숙대입구역 근처 방 구합니다",
            postContent = "안녕하세요! \n숙대입구역 근처 방을 구하고 있습니다. " +
                    "깨끗한 방을 원하며, 제가 숙대생이라 같은 학교 학우분과 쉐어하면 좋겠습니다. " +
                    "연락주세요 :)",
            profileImageRes = R.drawable.dum_profile_4
        )
    }
}

@Preview
@Composable
fun PreviewDetailScreen4() {
    DetailScreen4(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel()
    )
}