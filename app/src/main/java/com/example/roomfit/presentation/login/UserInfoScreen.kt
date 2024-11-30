package com.example.roomfit.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.roomfit.R
import com.example.roomfit.presentation.components.LoginButton
import com.example.roomfit.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(navController: NavController) {
    var school by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var houseType by remember { mutableStateOf("") }
    var numberOfResidents by remember { mutableStateOf("") }
    var durationOfStay by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var lifestyle by remember { mutableStateOf("") }
    var smoking by remember { mutableStateOf("") } // 흡연 상태 추가

    val houseTypes = listOf("아파트", "빌라", "원룸")
    val residentNumbers = listOf("2명", "3명", "4명 이상")
    val stayDurations = listOf("3개월~6개월", "6개월~12개월", "1년 이상")
    val genders = listOf("남성", "여성")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBeige)
    ) {
        IconButton(
            onClick = { navController.navigate("sign_up") },
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Back Button"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Text("Find Your Roommate", style = loginTitle2)

            Spacer(modifier = Modifier.height(50.dp))

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
                "나의 생활습관은?", style = bodyDetail
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = { lifestyle = if (lifestyle == "아침형") "저녁형" else "아침형" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (lifestyle.isEmpty()) BtnBeige else BtnBlack
                ),
                modifier = Modifier.fillMaxWidth(0.68f)
                    .height(48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = if (lifestyle.isNotEmpty()) R.drawable.daynight else R.drawable.daynight2),
                        contentDescription = "Day Night Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "아침형",
                        color = if (lifestyle == "아침형") Color.White else if (lifestyle.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { lifestyle = "아침형" },
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                    Text(
                        text = " / ",
                        color = if (lifestyle.isEmpty()) BtnBlack else Gray,
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                    Text(
                        text = "저녁형",
                        color = if (lifestyle == "저녁형") Color.White else if (lifestyle.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { lifestyle = "저녁형" },
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { smoking = if (smoking == "흡연") "비흡연" else "흡연" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (smoking.isEmpty()) BtnBeige else BtnBlack
                ),
                modifier = Modifier.fillMaxWidth(0.68f)
                    .height(48.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = if (smoking.isNotEmpty()) R.drawable.smoker else R.drawable.smoker2),
                        contentDescription = "Smoker Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = "흡연",
                        color = if (smoking == "흡연") Color.White else if (smoking.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { smoking = "흡연" },
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                    Text(
                        text = " / ",
                        color = if (smoking.isEmpty()) BtnBlack else Gray,
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                    Text(
                        text = "비흡연",
                        color = if (smoking == "비흡연") Color.White else if (smoking.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { smoking = "비흡연" },
                        textAlign = TextAlign.Center // 텍스트 중앙 정렬
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

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
                onClick = { navController.navigate("home") },
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
fun UserInfoScreenPreview() {
    val navController = rememberNavController()
    UserInfoScreen(navController = navController)
}