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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.components.LoginButton
import com.example.roomfit.presentation.components.LoginTextField
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.loginTitle

@Composable
fun FindPwScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
            .padding(bottom = 100.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
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
            Text("Forgot Password?", style = loginTitle)

            Spacer(modifier = Modifier.height(50.dp))

            LoginTextField(
                value = username,
                onValueChange = { username = it },
                label = "Email"
            )

            Spacer(modifier = Modifier.height(18.dp))

            LoginButton(
                text = "Find Password",
                onClick = {
                  navController.navigate("result_pw")
                },
                buttonColor = BtnBlack,
                textColor = BtnBeige
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FindPwScreenPreview() {
    val navController = rememberNavController()
    FindPwScreen(navController = navController)
}