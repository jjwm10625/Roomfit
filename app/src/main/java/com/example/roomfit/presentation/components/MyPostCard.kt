package com.example.roomfit.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomfit.presentation.mate.MateButton
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.*

@Composable
fun MyPostCard(
    modifier: Modifier = Modifier,
    postTitle: String,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OffWhite)
            .padding(16.dp)
    ) {
        // Post Title
        Text(
            text = postTitle,
            style = bodyDetail,
            color = ComponentBeige,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        UserInfo()

        Spacer(modifier = Modifier.height(16.dp))

        MateButton(
            text = "수정하기",
            onClick = {
                Toast.makeText(context, "수정하기", Toast.LENGTH_SHORT).show()
            }
        )
    }
}



@Preview
@Composable
fun MyPostCardPreview() {
    MyPostCard(
        postTitle = "17평형 정문 근처 룸 쉐어 구합니다"
    )
}