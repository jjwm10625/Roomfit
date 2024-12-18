package com.example.roomfit.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.roomfit.R
import com.example.roomfit.presentation.components.UserCard
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.UserTitle
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.textfield
import com.example.roomfit.util.PreferencesManager

@Composable
fun UserScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = remember { PreferencesManager(context) }
    val username = prefs.username ?: "Unknown"
    val gender = prefs.gender ?: "Unknown"
    val school = prefs.school ?: "Unknown"
    val budget = prefs.budget ?: "Unknown"
    val houseType = prefs.houseType ?: "Unknown"
    val numberOfResidents = prefs.numberOfResidents ?: "Unknown"
    val durationOfStay = prefs.durationOfStay ?: "Unknown"
    val lifestyle = prefs.lifestyle ?: "Unknown"
    val smoking = prefs.smoking ?: "Unknown"

    val profileImageUri = remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        profileImageUri.value = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = school,
                style = UserTitle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .clickable { galleryLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            profileImageUri.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = "Profile Image",
                    modifier = Modifier.size(100.dp)
                        .clip(CircleShape) // 이미지를 원형으로 자름
                )
            } ?: run {
                Image(
                    painter = painterResource(id = R.drawable.user_profile),
                    contentDescription = "Default Profile Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape) // 이미지를 원형으로 자름
                )
            }
        }

        // 카메라 버튼
        IconButton(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .size(40.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(20.dp))
                .offset(x = 32.dp, y = (-33).dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera),
                contentDescription = "Camera Icon",
                tint = Color.Unspecified, // 원본 색상 유지
                modifier = Modifier.size(40.dp) // 아이콘 크기 조정
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(username, style = bodyDetail)
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = if (gender == "남성") R.drawable.male else R.drawable.female),
                contentDescription = "Gender Icon",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        UserCard(
            navController = navController,
            username = username,
            gender = gender,
            school = school,
            budget = budget,
            houseType = houseType,
            numberOfResidents = numberOfResidents,
            durationOfStay = durationOfStay,
            lifestyle = lifestyle,
            smoking = smoking
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = { navController.navigate("my_post") },
            colors = ButtonDefaults.buttonColors(containerColor = OffWhite),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp)
                .background(OffWhite, shape = RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "내 게시글 관리",
                    style = textfield,
                    color = BtnBlack
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { navController.navigate("scrap") },
            colors = ButtonDefaults.buttonColors(containerColor = OffWhite),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp)
                .background(OffWhite, shape = RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "내 찜 목록",
                    style = textfield,
                    color = BtnBlack
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    // NavController를 전달할 수 없으므로 빈 함수로 대체
    UserScreen(navController = NavController(LocalContext.current))
}