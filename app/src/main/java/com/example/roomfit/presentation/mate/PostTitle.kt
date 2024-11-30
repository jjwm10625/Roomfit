package com.example.roomfit.presentation.mate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.bodyDetail


@Composable
fun PostTitle(title: String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = bodyDetail,
            color = ComponentBeige
        )
    }
}

@Preview
@Composable
fun PostTitlePreview() {
    PostTitle(title = "17평형 정문 근처 룸 쉐어 구합니다")
}