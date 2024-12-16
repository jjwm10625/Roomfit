package com.example.roomfit.presentation

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.presentation.mate.MateButton
import com.example.roomfit.presentation.mate.MateOrRoomButton
import com.example.roomfit.presentation.mate.PostText
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.Gray
import com.example.roomfit.ui.theme.ImageWhite
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail

@Composable
fun WriteScreen(navController: NavController) {
    val context = LocalContext.current

    // Launcher to open gallery and get the selected image URI
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                Toast.makeText(context, "Selected Image: $it", Toast.LENGTH_SHORT).show()
                // TODO: Handle the selected image URI here
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
            .padding(16.dp)
    ) {
        // 글 작성하기
        Text(
            text = "글 작성하기",
            style = bodyDetail,
            color = Black,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 사진 들어갈 곳
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ImageWhite)
                .clickable { galleryLauncher.launch("image/*") } // Open gallery on click
        ) {
            Text(
                text = "사진을 추가해 주세요",
                color = Gray,
                style = bodyDetail,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(OffWhite)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                MateOrRoomButton()

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = BtnBeige
                )

                UserInfo()

                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = BtnBeige
                )

                PostText(navController)

                MateButton(
                    text = "작성 완료",
                    onClick = {
                        Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun PreviewWriteScreen() {
    WriteScreen(navController = rememberNavController())
}