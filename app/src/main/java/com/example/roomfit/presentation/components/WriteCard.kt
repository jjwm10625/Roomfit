package com.example.roomfit.presentation.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomfit.PostViewModel
import com.example.roomfit.R
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.presentation.mate.MateButton
import com.example.roomfit.presentation.mate.MateOrRoomButton
import com.example.roomfit.presentation.mate.PostText
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.White
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting
import com.example.roomfit.ui.theme.mulishBold
import com.example.roomfit.ui.theme.LoginButton

@Composable
fun WriteCard(
    navController: NavController,
    username: String,
    gender: String,
    school: String,
    budget: String,
    houseType: String,
    numberOfResidents: String,
    durationOfStay: String,
    lifestyle: String,
    smoking: String,
    modifier: Modifier = Modifier,
    onSave: (String, String, String) -> Unit,
    postViewModel: PostViewModel = viewModel()
) {
    val context = LocalContext.current
    val customLoginButtonStyle = LoginButton.copy(fontSize = 16.sp)
    var selectedButton by remember { mutableStateOf("사람을 구해요!") }

    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OffWhite)
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            // MateOrRoomButton
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Button: 사람을 구해요
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = { selectedButton = "사람을 구해요!" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton == "사람을 구해요!") BtnBlack else BtnBeige
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "사람을 구해요!",
                        color = if (selectedButton == "사람을 구해요!") White else Black,
                        style = customLoginButtonStyle)
                }

                // Right Button: 방을 구해요
                Button(
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f),
                    onClick = { selectedButton = "방을 구해요!" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedButton == "방을 구해요!") BtnBlack else BtnBeige
                    ),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "방을 구해요!",
                        color = if (selectedButton == "방을 구해요!") White else Black,
                        style = customLoginButtonStyle
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = BtnBeige
            )

            // User Info
            UserInfo3(
                lifestyle = lifestyle,
                smoking = smoking,
                numberOfResidents = numberOfResidents,
                budget = budget
            )

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = BtnBeige
            )

            // Post Text
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Title Field
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp) // Adjust height for the title field
                        .background(OffWhite)
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    BasicTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        textStyle = bodyDetail.copy(color = ComponentBeige),
                        modifier = Modifier.fillMaxSize()
                    ) { innerTextField ->
                        if (titleText.isEmpty()) {
                            Text(
                                text = "제목을 작성해 주세요.",
                                style = bodyDetail,
                                color = ComponentBeige
                            )
                        }
                        innerTextField()
                    }
                }

                // Content Field
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(OffWhite)
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    BasicTextField(
                        value = contentText,
                        onValueChange = { contentText = it },
                        textStyle = bodyWriting.copy(color = ComponentBeige),
                        modifier = Modifier.fillMaxSize()
                    ) { innerTextField ->
                        if (contentText.isEmpty()) {
                            Text(
                                text = "글을 작성해 주세요.",
                                style = bodyWriting,
                                color = ComponentBeige
                            )
                        }
                        innerTextField()
                    }
                }

                // Location Field
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(OffWhite)
                        .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("geo:0,0?q=seoul") // Example location query
                            }
                            context.startActivity(intent)
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.location), // Replace with your actual location.png
                            contentDescription = "Location Icon",
                            modifier = Modifier.size(18.dp) // Adjust the size of the icon
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "위치 입력하기",
                            style = bodyWriting,
                            color = Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 작성 완료 버튼
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        postViewModel.savePost(titleText, contentText, "위치 정보", null)
                        onSave(titleText, contentText, "위치 정보")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BtnBlack)
                ) {
                    Text(
                        text = "작성 완료",
                        style = LoginButton.copy(fontFamily = mulishBold)
                    )
                }
            }
        }
    }
}

@Composable
fun UserInfo3(
    lifestyle: String,
    smoking: String,
    numberOfResidents: String,
    budget: String
) {
    Spacer(modifier = Modifier.height(8.dp)) // 텍스트와 아이콘 섹션 간 여백

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            DetailItem(
                iconRes = R.drawable.sun,
                iconLabel = lifestyle,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.smoking,
                iconLabel = smoking,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.people,
                iconLabel = numberOfResidents,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
        item {
            DetailItem(
                iconRes = R.drawable.budget,
                iconLabel = budget,
                labelStyle = com.example.roomfit.ui.theme.UserInfo
            )
        }
    }
}

//@Preview
//@Composable
//fun WriteCardPreview() {
//    WriteCard()
//}