package com.example.roomfit.presentation.components

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.roomfit.R
import com.example.roomfit.ui.theme.*

@Composable
fun MyPostCard(
    modifier: Modifier = Modifier,
    mateorroomText: String,
    titleText: String,
    contentText: String,
    imageUri: Uri?,
    location: String?,
    onDelete: () -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(OffWhite)
            .padding(16.dp)
    ) {
        // 이미지 표시 부분
        if (imageUri != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = "Post Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = BtnBeige
            )
        }

        // MateOrRoom
        Text(
            text = mateorroomText,
            style = bodyDetail,
            color = BtnBlack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // Post Title
        Text(
            text = titleText,
            style = bodyDetail,
            color = ComponentBeige,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        // Post Content
        Text(
            text = contentText,
            style = bodyWriting,
            color = ComponentBeige,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 위치 보기 버튼
        if (!location.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(OffWhite)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable { /* navController.navigate(저장된 위치) */ }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "위치 보기",
                        style = bodyWriting,
                        color = Black
                    )
                }
            }
        }

        // 삭제하기 버튼
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    onDelete()
                    Toast.makeText(context, "삭제되었습니다!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BtnBlack)
            ) {
                Text(
                    text = "삭제하기",
                    style = LoginButton.copy(fontFamily = mulishBold)
                )
            }
        }
    }
}

@Preview
@Composable
fun MyPostCardPreview() {
    MyPostCard(
        imageUri = Uri.parse("https://via.placeholder.com/250"),
        mateorroomText = "방을 구해요!",
        titleText = "제목",
        contentText = "내용",
        location = "서울"
    )
}