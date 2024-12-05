package com.example.roomfit.presentation.mate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.bodyWriting


@Composable
fun PostContent(
    content: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = content,
            style = bodyWriting.copy(color = ComponentBeige),
            color = ComponentBeige
        )
    }
}

@Preview
@Composable
fun PostContentPreview() {
    PostContent(content = "저는 고양이를 키우고 있어서\n" +
            "털 알러지 없는 분들로 받겠습니다!")
}