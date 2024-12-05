package com.example.roomfit.presentation.mate

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.OffWhite

@Preview
@Composable
fun RoomMateCard() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = OffWhite)
            .border(1.dp, color = BtnBeige, RoundedCornerShape(16.dp))
            .padding(16.dp)
        ) {
        Spacer(modifier = Modifier.height(6.dp))

        UserProfile()

        Spacer(modifier = Modifier.height(16.dp))

        PostTitle(
            title = "17평형 정문 근처 룸 쉐어 구합니다"
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = BtnBeige
        )

        Spacer(modifier = Modifier.height(8.dp))

        UserInfo()

        Spacer(modifier = Modifier.height(8.dp))

        MateButton(
            text = "mate?",
            onClick = {
                Toast.makeText(context, "mate를 신청했습니다!", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}