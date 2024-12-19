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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.viewmodel.ScrapViewModel
import com.example.roomfit.presentation.components.ScrapMateCard
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle
import com.gdg.kakaobank.presentation.navigator.RoomNav

@Composable
fun ScrapListScreen(navController: NavController, scrapViewModel: ScrapViewModel) {
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
                text = "찜 목록",
                style = UserTitle,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        // 찜한 게시물 목록
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundBeige)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            for (item in scrapViewModel.scrapList) {
                ScrapMateCard(
                    navController = navController,
                    userName = item.userName,
                    postTitle = item.postTitle,
                    postContent = item.postContent,
                    profileImageRes = item.profileImageRes,
                    onDetailClick = {
                        // 여기서 상황에 따라 각기 다른 화면으로 네비게이션
                        when (item.routeKey) {
                            "detailmatecard" -> navController.navigate("home_mate")
                            "detailmatecard2" -> navController.navigate("home_mate2")
                            "detailmatecard4" -> navController.navigate("home_mate4")
                            "detailmatecard5" -> navController.navigate("home_mate5")
                            else -> {}
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun ScrapScreenPreview() {
    ScrapListScreen(
        navController = rememberNavController(),
        scrapViewModel = ScrapViewModel()
    )
}