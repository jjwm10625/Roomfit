package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.presentation.components.HomeMateCard4
import com.example.roomfit.presentation.components.MateFilter
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.LoginButton
import com.example.roomfit.ui.theme.White
import com.example.roomfit.ui.theme.homeTitle
import com.gdg.kakaobank.presentation.navigator.RoomNav

@Composable
fun HomeScreen2(
    navController: NavController,
) {
    val customLoginButtonStyle = LoginButton.copy(fontSize = 16.sp)
    var selectedButton by remember { mutableStateOf("방을 구해요!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
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

            // MateOrRoomButton
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Button: 사람을 구해요
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = {
                        selectedButton = "사람을 구해요!"
                        navController.navigate(RoomNav.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton == "사람을 구해요!") BtnBlack else BtnBeige
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "사람을 구해요!",
                        color = if (selectedButton == "사람을 구해요!") White else Black,
                        style = customLoginButtonStyle)
                }

                // Right Button: 방을 구해요
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = {
                        selectedButton = "방을 구해요!"
                        navController.navigate("home2") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton == "방을 구해요!") BtnBlack else BtnBeige
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "방을 구해요!",
                        color = if (selectedButton == "방을 구해요!") White else Black,
                        style = customLoginButtonStyle
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            MateFilter()

            Spacer(modifier = Modifier.height(16.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            HomeMateCard4(
                navController = navController,
                userName = "이희진",
                postTitle = "숙대입구역 근처 방 구합니다"
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}



@Preview
@Composable
fun HomeScreen2Preview() {
    HomeScreen2(
        navController = rememberNavController()
    )
}