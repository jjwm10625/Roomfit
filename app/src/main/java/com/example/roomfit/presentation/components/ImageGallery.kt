package com.example.roomfit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomfit.R

@Composable
fun ImageGallery(imageList: List<Int>, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        items(imageList.size) { index ->
            Image(
                painter = painterResource(id = imageList[index]),
                contentDescription = "Image Gallery",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .padding(end = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun ImageGalleryPreview() {
    val imageList = listOf(
        R.drawable.roomimage,
        R.drawable.roomimage2,
        R.drawable.roomimage3,
    )
    ImageGallery(imageList = imageList)
}