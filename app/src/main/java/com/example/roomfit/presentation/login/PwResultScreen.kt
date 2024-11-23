// app/src/main/java/com/example/roomfit/presentation/login/FindPwScreen.kt
package com.example.roomfit.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.bodyWriting
import com.example.roomfit.ui.theme.loginTitle

@Composable
fun PwResultScreen(navController: NavController) {
    var password by remember { mutableStateOf("Password Result") } // 예시 패스워드

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
            .padding(bottom = 100.dp)
    ) {
        IconButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Back Button"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your Password is...", style = loginTitle)

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(55.dp)
                    .background(BtnBeige, RoundedCornerShape(70.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = password,
                    color = BtnBlack,
                    style = bodyWriting
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PwResultPreview() {
    val navController = rememberNavController()
    PwResultScreen(navController = navController)
}