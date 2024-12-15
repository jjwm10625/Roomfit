// app/src/main/java/com/example/roomfit/presentation/components/LoginButton.kt
package com.example.roomfit.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColor: Color,
    textColor: Color,
    enabled: Boolean = true // enabled 매개변수 추가

) {
    Button(
        onClick = onClick,
        enabled = enabled, // Button의 enabled 상태 설정
        modifier = modifier.width(300.dp).height(55.dp),
        shape = RoundedCornerShape(70.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
    ) {
        Text(text = text, style = com.example.roomfit.ui.theme.LoginButton)
    }
}