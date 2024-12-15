package com.example.roomfit.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.R
import com.example.roomfit.presentation.components.LoginButton
import com.example.roomfit.presentation.components.LoginTextField
import com.example.roomfit.ui.theme.*

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Log In", style = loginTitle)

            Spacer(modifier = Modifier.height(50.dp))

            LoginTextField(
                value = username,
                onValueChange = { username = it },
                label = "Email"
            )

            Spacer(modifier = Modifier.height(18.dp))

            LoginTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(25.dp))

            LoginButton(
                text = "LOG IN",
                onClick = {
                    navController.navigate("home")
                },
                buttonColor = BtnBlack,
                textColor = Color.White,
                enabled = username.trim().isNotEmpty() && password.trim().isNotEmpty()
            )


            Spacer(modifier = Modifier.height(15.dp))

            LoginButton(
                text = "SIGN UP",
                onClick = {
                    // 회원가입 로직 추가
                    navController.navigate("sign_up")
                },
                buttonColor = BtnBeige,
                textColor = BtnBlack
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Forgot your password?",
                style = bodyWriting,
                color = BtnBlack,
                modifier = Modifier.clickable {
                    navController.navigate("find_pw")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}