package com.example.roomfit.presentation.mate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomfit.R
import com.example.roomfit.ui.theme.Black
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.ComponentBeige
import com.example.roomfit.ui.theme.Gray
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.bodyDetail
import com.example.roomfit.ui.theme.bodyWriting

@Preview
@Composable
fun PostText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Title Field
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) // Adjust height for the title field
                    .background(OffWhite)
                    //.border(1.dp, color = BtnBeige)
                    .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners
                    .padding(8.dp)
            ) {
                BasicTextField(
                    value = "",
                    onValueChange = { /* TODO: Handle title input */ },
                    textStyle = bodyDetail,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if ("".isEmpty()) {
                        Text(
                            text = "제목을 작성해 주세요.",
                            style = bodyDetail,
                            color = ComponentBeige
                        )
                    }
                }
            }

            // Content Field
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Adjust height for the content field
                    .background(OffWhite)
                    //.border(1.dp, color = BtnBeige)
                    .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners
                    .padding(8.dp)
            ) {
                BasicTextField(
                    value = "",
                    onValueChange = { /* TODO: Handle content input */ },
                    textStyle = bodyWriting,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if ("".isEmpty()) {
                        Text(
                            text = "글을 작성해 주세요.",
                            style = bodyWriting,
                            color = ComponentBeige
                        )
                    }
                }
            }

                Spacer(modifier = Modifier.height(8.dp))

            // Location Field
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(OffWhite)
                    //.border(1.dp, color = BtnBeige)
                    .clip(RoundedCornerShape(8.dp)) // Optional: rounded corners
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location), // Replace with your actual location.png
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(20.dp) // Adjust the size of the icon
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text

                    Text(
                        text = "위치 입력하기",
                        style = bodyWriting,
                        color = Black
                    )
                }
            }
        }
    }
}
