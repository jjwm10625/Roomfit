package com.example.roomfit.presentation.mate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.buttonSmall

@Composable
fun UserCard(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        UserInfo()

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("user_edit")
            },
            colors = ButtonDefaults.buttonColors(containerColor = BtnBeige),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "내 정보 수정", style = bodyDetail, color = BtnBlack)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    // NavController를 전달할 수 없으므로 빈 함수로 대체
    UserCard(navController = NavController(LocalContext.current))
}