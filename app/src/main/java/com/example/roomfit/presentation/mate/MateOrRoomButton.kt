package com.example.roomfit.presentation.mate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.LoginButton
import com.example.roomfit.ui.theme.White

@Composable
fun MateOrRoomButton() {
    val customLoginButtonStyle = LoginButton.copy(fontSize = 16.sp)

    var selectedButton by remember { mutableStateOf("사람을 구해요!") }

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
            onClick = { selectedButton = "사람을 구해요!" },
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
            onClick = { selectedButton = "방을 구해요!" },
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
}