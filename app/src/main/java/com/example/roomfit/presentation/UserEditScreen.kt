package com.example.roomfit.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.roomfit.R
import com.example.roomfit.presentation.components.LoginButton
import com.example.roomfit.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEditScreen(navController: NavController) {
    var school by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var houseType by remember { mutableStateOf("") }
    var numberOfResidents by remember { mutableStateOf("") }
    var durationOfStay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var lifestyle by remember { mutableStateOf("") }
    var smoking by remember { mutableStateOf("") }
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    val houseTypes = listOf("아파트", "빌라", "원룸")
    val residentNumbers = listOf("2명", "3명", "4명 이상")
    val stayDurations = listOf("3개월~6개월", "6개월~12개월", "1년 이상")
    val genders = listOf("남성", "여성")
    val lifestyles = listOf("아침형", "저녁형")
    val smokingOptions = listOf("흡연", "비흡연")

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 제목 및 뒤로가기 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                IconButton(
                    onClick = { navController.navigate("user") },
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backbutton),
                        contentDescription = "Back Button"
                    )
                }
                Text(
                    text = "내 프로필",
                    style = UserTitle,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // 프로필 사진 변경
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clickable { galleryLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                profileImageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(100.dp)
                    )
                } ?: run {
                    Image(
                        painter = painterResource(id = R.drawable.user_profile),
                        contentDescription = "Default Profile Image",
                        modifier = Modifier.size(100.dp)
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

            Spacer(modifier = Modifier.height(15.dp))

            LoginTextField(
                value = school,
                onValueChange = { school = it },
                label = "나의 대학 찾기",
                trailingIcon = {
                    IconButton(onClick = { /* 아이콘 클릭 시 동작 */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.school_search),
                            contentDescription = "Search School"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "이름",
                style = textfield,
                modifier = Modifier
                    .padding(start = 6.dp, bottom = 2.dp)
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "이름", style = TextStyle(fontSize = 16.sp, color = Color.Gray)) },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(55.dp)
                    .background(OffWhite, shape = RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = OffWhite,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // 성향 드롭다운 메뉴
            CustomDropdownMenu(
                label = "성향",
                options = lifestyles,
                selectedOption = lifestyle,
                onOptionSelected = { lifestyle = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            // 흡연 여부 드롭다운 메뉴
            CustomDropdownMenu(
                label = "흡연 여부",
                options = smokingOptions,
                selectedOption = smoking,
                onOptionSelected = { smoking = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomDropdownMenu(
                label = "예산",
                options = listOf("1000만원~3000만원", "3000만원~5000만원", "5000만원~1억원", "1억원 이상"),
                selectedOption = budget,
                onOptionSelected = { budget = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomDropdownMenu(
                label = "집 유형",
                options = houseTypes,
                selectedOption = houseType,
                onOptionSelected = { houseType = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomDropdownMenu(
                label = "거주 인원",
                options = residentNumbers,
                selectedOption = numberOfResidents,
                onOptionSelected = { numberOfResidents = it }
            )

            Spacer(modifier = Modifier.height(18.dp))

            CustomDropdownMenu(
                label = "거주 기간",
                options = stayDurations,
                selectedOption = durationOfStay,
                onOptionSelected = { durationOfStay = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomDropdownMenu(
                label = "성별",
                options = genders,
                selectedOption = gender,
                onOptionSelected = { gender = it }
            )

            Spacer(modifier = Modifier.height(30.dp))

            LoginButton(
                text = "SIGN UP",
                onClick = { navController.navigate("user") },
                buttonColor = BtnBlack,
                textColor = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth(0.8f)) {
        Text(
            text = label,
            style = textfield,
            modifier = Modifier.padding(start = 6.dp, bottom = 2.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            TextField(
                value = selectedOption,
                onValueChange = {},
                placeholder = { Text(label, style = textfield2, color = Gray, textAlign = TextAlign.Center) },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.dropdown),
                        contentDescription = "Dropdown Icon",
                        modifier = Modifier.clickable { expanded = true }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(OffWhite, shape = RoundedCornerShape(16.dp))
                    .clickable { expanded = true },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = OffWhite,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f) // TextField의 폭과 일치하도록 수정
                    .background(OffWhite, shape = RoundedCornerShape(16.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth(), // DropdownMenuItem의 폭을 맞춤
                        text = { Text(text = option, maxLines = 1, overflow = TextOverflow.Ellipsis) }, // 글씨 잘림 방지
                        contentPadding = PaddingValues(horizontal = 16.dp) // 패딩 조정
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = label, style = TextStyle(fontSize = 16.sp, color = Color.Gray)) },
        trailingIcon = trailingIcon,
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(56.dp)
            .background(OffWhite, shape = RoundedCornerShape(70.dp))
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = OffWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun UserEditScreenPreview() {
    val navController = rememberNavController()
    UserEditScreen(navController = navController)
}