package com.example.roomfit.presentation.mate

import android.widget.Toast
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.LoginButton
import com.example.roomfit.ui.theme.White

@Composable
fun BookmarkButton() {
    val customLoginButtonStyle = LoginButton.copy(fontSize = 16.sp)
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Left Button: 찜하기
        Button(
            modifier = Modifier
                .height(55.dp)
                .weight(1f),
            onClick = {
                Toast.makeText(context, "찜 되었습니다!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BtnBeige
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "찜하기",
                color = Black,
                style = customLoginButtonStyle
            )
        }

        // Right Button: 채팅하기
        Button(
            modifier = Modifier
                .height(55.dp)
                .weight(1f),
            onClick = {
                /* 채팅 화면으로 이동 */
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BtnBlack
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "채팅하기",
                color = White,
                style = customLoginButtonStyle
            )
        }
    }
}

@Preview
@Composable
fun BookmarkButtonPreview() {
    BookmarkButton()
}