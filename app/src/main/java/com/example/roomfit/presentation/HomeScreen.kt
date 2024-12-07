package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.presentation.components.HomeMateCard
import com.example.roomfit.presentation.components.MateFilter
import com.example.roomfit.presentation.mate.MateOrRoomButton
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.homeTitle

@Composable
fun HomeScreen(
    navController: NavController,
) {
    NavController(LocalContext.current)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundBeige)
                .padding(16.dp)
        ) {
            Text(
                text = "RoomFit",
                style = homeTitle,
                color = Black,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            MateOrRoomButton()

            Spacer(modifier = Modifier.height(8.dp))

            MateFilter()

            Spacer(modifier = Modifier.height(32.dp))

            HomeMateCard(
                navController = navController,
                userName = "김채현",
                postTitle = "17평형 정문 근처 룸 쉐어 구합니다"
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController()
    )
}