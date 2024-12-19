package com.example.roomfit.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

    // 화면 진입 시 location값 초기화
    LaunchedEffect(Unit) {
        navController.currentBackStackEntry?.savedStateHandle?.remove<String>("location")
    }

    var selectedButton by rememberSaveable { mutableStateOf("사람을 구해요!") }
    var titleText by rememberSaveable { mutableStateOf("") }
    var contentText by rememberSaveable { mutableStateOf("") }
    var locationText by rememberSaveable { mutableStateOf("") }
    var selectedImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val boxBaseModifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(ImageWhite)

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                selectedImageUri = it
                Toast.makeText(context, "추가되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // selectedButton을 기준으로 이미지 영역 클릭 가능 여부 결정
    val boxModifier = if (selectedButton == "방을 구해요!") {
        boxBaseModifier
    } else {
        boxBaseModifier.clickable {
            galleryLauncher.launch("image/*")
        }
    }

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<String>("location")?.observe(navController.currentBackStackEntry!!) { location ->
        locationText = location
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
            .padding(16.dp)
    ) {
        // 상단 타이틀
        Text(
            text = "글 작성하기",
            style = bodyDetail,
            color = Black,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // "방을 구해요!"가 아닐 때만 이미지 박스 표시
        if (selectedButton != "방을 구해요!") {
            Box(modifier = boxModifier) {
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
        }

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
            postViewModel = postViewModel,
            onSave = { mateOrRoom, title, content, location ->
                // 게시글 저장
                postViewModel.savePost(mateOrRoom, title, content, location, selectedImageUri)
                Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()

                // 저장 후 상태 초기화
                selectedButton = "사람을 구해요!"
                titleText = ""
                contentText = ""
                locationText = ""
                selectedImageUri = null
            },
            selectedButton = selectedButton,
            onSelectButton = { newSelected ->
                selectedButton = newSelected
                postViewModel.mateorroom = newSelected
            },
            titleText = titleText,
            onTitleChange = { titleText = it },
            contentText = contentText,
            onContentChange = { contentText = it },
            locationText = locationText,
            selectedImageUri = selectedImageUri
        )
    }
}