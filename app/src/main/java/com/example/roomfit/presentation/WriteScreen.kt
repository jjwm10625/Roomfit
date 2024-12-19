package com.example.roomfit.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.roomfit.PostViewModel
import com.example.roomfit.presentation.components.WriteCard
import com.example.roomfit.ui.theme.BackgroundBeige
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.Gray
import com.example.roomfit.ui.theme.ImageWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.util.PreferencesManager

@Composable
fun WriteScreen(
    navController: NavController,
    postViewModel: PostViewModel = viewModel()
) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

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

    // Launcher to open gallery and get the selected image URI
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                selectedImageUri = it
                Toast.makeText(context, "추가되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
            .padding(16.dp)
            //.verticalScroll(rememberScrollState())
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
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(model = selectedImageUri),
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 글 작성 카드
        WriteCard(
            navController = navController,
            username = username,
            gender = gender,
            school = school,
            budget = budget,
            houseType = houseType,
            numberOfResidents = numberOfResidents,
            durationOfStay = durationOfStay,
            lifestyle = lifestyle,
            smoking = smoking,
            modifier = Modifier,
            onSave = { title, content, location ->
                postViewModel.savePost(title, content, location, selectedImageUri)
                Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
