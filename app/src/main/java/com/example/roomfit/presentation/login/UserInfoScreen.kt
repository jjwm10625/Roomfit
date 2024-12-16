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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.roomfit.util.PreferencesManager
import androidx.compose.ui.zIndex

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
    var smoking by remember { mutableStateOf("") }
    var showUniversityDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .zIndex(1f) // zIndex를 설정하여 다른 요소 위에 위치하도록 함
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
                    IconButton(
                        onClick = { showUniversityDialog = true },
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.school_search),
                            contentDescription = "Search School"
                        )
                    }

                    if (showUniversityDialog) {
                        UniversitySearchDialog(
                            onDismiss = { showUniversityDialog = false },
                            onUniversitySelected = { selectedUniversity ->
                                school = selectedUniversity
                                showUniversityDialog = false
                            }
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                "나의 생활습관은?", style = TextStyle(
                    fontSize = 16.sp,
                    color = Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = { lifestyle = if (lifestyle == "아침형") "저녁형" else "아침형" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (lifestyle.isEmpty()) BtnBeige else BtnBlack
                ),
                modifier = Modifier
                    .fillMaxWidth(0.68f)
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
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = " / ",
                        color = if (lifestyle.isEmpty()) BtnBlack else Gray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "저녁형",
                        color = if (lifestyle == "저녁형") Color.White else if (lifestyle.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { lifestyle = "저녁형" },
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = { smoking = if (smoking == "흡연") "비흡연" else "흡연" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (smoking.isEmpty()) BtnBeige else BtnBlack
                ),
                modifier = Modifier
                    .fillMaxWidth(0.68f)
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
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = " / ",
                        color = if (smoking.isEmpty()) BtnBlack else Gray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "비흡연",
                        color = if (smoking == "비흡연") Color.White else if (smoking.isEmpty()) BtnBlack else Gray,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { smoking = "비흡연" },
                        textAlign = TextAlign.Center
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
                onClick = {
                    val prefs = PreferencesManager(context)
                    prefs.school = school
                    prefs.budget = budget
                    prefs.houseType = houseType
                    prefs.numberOfResidents = numberOfResidents
                    prefs.durationOfStay = durationOfStay
                    prefs.gender = gender
                    prefs.lifestyle = lifestyle
                    prefs.smoking = smoking
                    navController.navigate("home")
                },
                buttonColor = BtnBlack,
                textColor = Color.White,
                enabled = school.isNotEmpty() && budget.isNotEmpty() && houseType.isNotEmpty() &&
                          numberOfResidents.isNotEmpty() && durationOfStay.isNotEmpty() &&
                          gender.isNotEmpty() && lifestyle.isNotEmpty() && smoking.isNotEmpty()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversitySearchDialog(
    onDismiss: () -> Unit,
    onUniversitySelected: (String) -> Unit
) {
    var query by remember { mutableStateOf("") } // 검색어 상태
    val universities = listOf(
        "가야대학교", "가천길대학교", "가천대학교", "가천의과학대학교", "가톨릭관동대학교",
        "가톨릭대학교", "가톨릭상지대학교", "감리교신학대학교", "강남대학교", "강동대학교",
        "강릉영동대학교", "강릉원주대학교", "강원관광대학교", "강원대학교", "강원도립대학교",
        "거제대학교", "건국대학교", "건국대학교(글로컬)", "건양대학교", "건양사이버대학교",
        "경기과학기술대학교", "경기대학교", "경남과학기술대학교", "경남대학교", "경남도립거창대학교",
        "경남도립남해대학교", "경남정보대학교", "경동대학교", "경민대학교", "경복대학교",
        "경북과학대학교", "경북대학교", "경북도립대학교", "경북외국어대학교", "경북전문대학교",
        "경산대학교", "경상대학교", "경성대학교", "경운대학교", "경운대학교(산업대)",
        "경원전문대학교", "경인교육대학교", "경인여자대학교", "경일대학교", "경주대학교",
        "경희대학교", "경희사이버대학교", "계명대학교", "계명문화대학교", "계원예술대학교",
        "고구려대학교", "고려대학교", "고려대학교(세종)", "고려사이버대학교", "고신대학교",
        "공주교육대학교", "공주대학교", "광신대학교", "광양보건대학교", "광운대학교",
        "광주가톨릭대학교", "광주교육대학교", "광주대학교", "광주대학교(산업대)", "광주보건대학교",
        "광주여자대학교", "구미대학교", "구세군사관학교", "국민대학교", "국제대학교",
        "국제사이버대학교", "군산간호대학교", "군산대학교", "군장대학교", "그리스도대학교",
        "극동대학교", "글로벌사이버대학교", "금강대학교", "금오공과대학교", "기독간호대학교",
        "김천과학대학교", "김천대학교", "김포대학교", "김해대학교", "꽃동네대학교",
        "나사렛대학교", "남부대학교", "남서울대학교", "남서울대학교(산업대)", "농협대학교",
        "단국대학교", "대경대학교", "대구가톨릭대학교", "대구공업대학교", "대구과학대학교",
        "대구교육대학교", "대구대학교", "대구미래대학교", "대구보건대학교", "대구사이버대학교",
        "대구예술대학교", "대구외국어대학교", "대구한의대학교", "대덕대학교", "대동대학교",
        "대림대학교", "대신대학교", "대원대학교", "대전가톨릭대학교", "대전대학교",
        "대전보건대학교", "대전신학교", "대전신학대학교", "대진대학교", "덕성여자대학교",
        "동강대학교", "동국대학교", "동국대학교(경주)", "동남보건대학교", "동덕여자대학교",
        "동명대학교", "동명정보대학교", "동부산대학교", "동서대학교", "동서울대학교",
        "동신대학교", "동아대학교", "동아방송예술대학교", "동아인재대학교", "동양대학교",
        "동양미래대학교", "동우대학교", "동원과학기술대학교", "동원대학교", "동의과학대학교",
        "동의대학교", "동주대학교", "두원공과대학교", "디지털서울문화예술대학교", "루터대학교",
        "마산대학교", "명지대학교", "명지전문대학교", "목원대학교", "목포가톨릭대학교",
        "목포과학대학교", "목포대학교", "목포해양대학교", "문경대학교", "배재대학교",
        "배화여자대학교", "백석대학교", "백석문화대학교", "백제예술대학교", "벽성대학교",
        "국립부경대학교", "부산가톨릭대학교", "부산경상대학교", "부산과학기술대학교", "부산교육대학교",
        "부산대학교", "부산디지털대학교", "부산여자대학교", "부산예술대학교", "부산외국어대학교",
        "부산장신대학교", "부천대학교", "사이버한국외국어대학교", "삼육대학교", "삼육보건대학교",
        "삼육의명대학교", "상명대학교", "상명대학교(천안)", "상주대학교", "상지대학교",
        "상지영서대학교", "서강대학교", "서경대학교", "서남대학교", "서라벌대학교",
        "서영대학교", "서울과학기술대학교", "서울과학기술대학교(산업대)", "서울교육대학교", "서울기독대학교",
        "서울대학교", "서울디지털대학교", "서울보건대학교", "서울사이버대학교", "서울시립대학교",
        "서울신학대학교", "서울여자간호대학교", "서울여자대학교", "서울예술대학교", "서울장신대학교",
        "서원대학교", "서일대학교", "서정대학교", "서해대학교", "선린대학교",
        "선문대학교", "성결대학교", "성공회대학교", "성균관대학교", "성덕대학교",
        "성신여자대학교", "성심외국어대학교", "세경대학교", "세명대학교", "세종대학교",
        "세종사이버대학교", "세한대학교", "송곡대학교", "송원대학교", "송호대학교",
        "수성대학교", "수원가톨릭대학교", "수원과학대학교", "수원대학교", "수원여자대학교",
        "숙명여자대학교", "순복음총회신학교", "순천대학교", "순천제일대학교", "순천향대학교",
        "숭실대학교", "숭실사이버대학교", "숭의여자대학교", "신경대학교", "신구대학교",
        "신라대학교", "신성대학교", "신안산대학교", "신흥대학교", "아세아연합신학대학교",
        "아주대학교", "아주자동차대학교", "안동과학대학교", "안동대학교", "안산대학교",
        "안양대학교", "여주대학교", "연성대학교", "연세대학교", "연세대학교(원주)",
        "연암공과대학교", "열린사이버대학교", "영남대학교", "영남신학대학교", "영남외국어대학교",
        "영남이공대학교", "영산대학교", "영산대학교(산업대)", "영산선학대학교", "영진사이버대학교",
        "영진전문대학교", "예수대학교", "예원예술대학교", "오산대학교", "용인대학교",
        "용인송담대학교", "우석대학교", "우송공업대학교", "우송대학교", "우송대학교(산업대)",
        "우송정보대학교", "울산과학대학교", "울산대학교", "웅지세무대학교", "원광대학교",
        "원광디지털대학교", "원광보건대학교", "원주대학교", "위덕대학교", "유원대학교",
        "유한대학교", "을지대학교", "이화여자대학교", "인덕대학교", "인제대학교",
        "인천가톨릭대학교", "인천대학교", "인천재능대학교", "인천전문대학교", "인하공업전문대학교",
        "인하대학교", "장로회신학대학교", "장안대학교", "적십자간호대학교", "전남과학대학교",
        "전남대학교", "전남도립대학교", "전북과학대학교", "전북대학교", "전주교육대학교",
        "전주기전대학교", "전주대학교", "전주비전대학교", "정석대학교", "제주관광대학교",
        "제주교육대학교", "제주국제대학교", "제주대학교", "제주산업정보대학교", "제주한라대학교",
        "조선간호대학교", "조선대학교", "조선이공대학교", "중부대학교", "중앙대학교",
        "중앙대학교(안성)", "중앙승가대학교", "중원대학교", "진주교육대학교", "진주보건대학교",
        "진주산업대학교(산업대)", "차의과학대학교", "창신대학교", "창원대학교", "창원문성대학교",
        "천안연암대학교", "청강문화산업대학교", "청암대학교", "청운대학교", "청주교육대학교",
        "청주대학교", "초당대학교", "초당대학교(산업대)", "총신대학교", "추계예술대학교",
        "춘천교육대학교", "춘해보건대학교", "충남대학교", "충남도립청양대학교", "충북대학교",
        "충북도립대학교", "충북보건과학대학교", "충청대학교", "침례신학대학교", "칼빈대학교",
        "탐라대학교", "평택대학교", "포항대학교", "한경대학교", "한경대학교(산업대)",
        "한국골프대학교", "한국관광대학교", "한국교원대학교", "한국교통대학교", "한국교통대학교(산업대)",
        "한국국제대학교", "한국기술교육대학교", "한국농수산대학교", "한국방송통신대학교", "한국복지대학교",
        "한국복지사이버대학교", "한국산업기술대학교", "한국산업기술대학교(산업대)", "한국성서대학교", "한국승강기대학교",
        "한국영상대학교", "한국예술종합학교", "한국외국어대학교", "한국전통문화대학교", "한국정보통신기능대학교",
        "한국철도대학교"
    )

    Dialog(onDismissRequest = onDismiss) { // Dialog 구성
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                // 제목
                Text(
                    text = "대학 검색",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // 검색 입력 필드
                TextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("대학 이름을 입력하세요") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(OffWhite, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = OffWhite,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 검색 결과 리스트
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    items(universities.filter { it.contains(query, ignoreCase = true) }) { university ->
                        Text(
                            text = university,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onUniversitySelected(university) // 선택한 대학 전달
                                }
                                .padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 취소 버튼
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "닫기", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoScreenPreview() {
    val navController = rememberNavController()
    UserInfoScreen(navController = navController)
}