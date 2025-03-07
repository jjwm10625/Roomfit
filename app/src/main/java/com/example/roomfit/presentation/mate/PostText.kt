package com.example.roomfit.presentation.mate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.navigation.NavController
import com.example.roomfit.R
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting

@Composable
fun PostText(navController: NavController) {
    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Title Field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Adjust height for the title field
                .background(OffWhite)
                .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners
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
                .clip(RoundedCornerShape(8.dp))
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
                    navController.navigate("map")
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

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun PostTextPreview() {
    PostText(navController = NavController(LocalContext.current))
}