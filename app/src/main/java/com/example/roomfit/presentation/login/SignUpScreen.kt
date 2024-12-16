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
import androidx.compose.ui.platform.LocalContext
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
import com.example.roomfit.util.PreferencesManager


@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pwcheck by remember { mutableStateOf("") }
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        Image(
            painter = painterResource(id = R.drawable.sn_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sign Up", style = loginTitle)

            Spacer(modifier = Modifier.height(50.dp))

            LoginTextField(
                value = name,
                onValueChange = { name = it },
                label = "이름"
            )

            Spacer(modifier = Modifier.height(18.dp))

            LoginTextField(
                value = email,
                onValueChange = { email = it },
                label = "이메일"
            )

            Spacer(modifier = Modifier.height(18.dp))

            LoginTextField(
                value = password,
                onValueChange = { password = it },
                label = "비밀번호",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(18.dp))

            LoginTextField(
                value = pwcheck,
                onValueChange = { pwcheck = it },
                label = "비밀번호 확인",
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp) // 고정된 높이 설정
                    .padding(start = 55.dp)
            ) {
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = ErrorText,
                        style = error,
                        modifier = Modifier.padding(start = 8.dp) // 왼쪽 간격 추가
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            LoginButton(
                text = "NEXT",
                onClick = {
                    if (password != pwcheck) {
                        errorMessage = "비밀번호가 일치하지 않습니다."
                    } else {
                        prefs.username = name
                        prefs.email = email
                        prefs.password = password
                        navController.navigate("user_info")
                    }
                },
                buttonColor = BtnBlack,
                textColor = Color.White,
                enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && pwcheck.isNotEmpty()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}