package com.example.roomfit.presentation.components

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.roomfit.R
import com.example.roomfit.ui.theme.*

@Composable
fun MyPostCard(
    modifier: Modifier = Modifier,
    titleText: String,
    contentText: String,
    imageUri: Uri?,
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