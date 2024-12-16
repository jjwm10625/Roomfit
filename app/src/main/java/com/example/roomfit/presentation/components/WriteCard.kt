package com.example.roomfit.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomfit.R
import com.example.roomfit.presentation.mate.DetailItem
import com.example.roomfit.presentation.mate.MateButton
import com.example.roomfit.presentation.mate.MateOrRoomButton
import com.example.roomfit.presentation.mate.PostText
import com.example.roomfit.presentation.mate.UserInfo
import com.example.roomfit.ui.theme.BtnBeige
import com.example.roomfit.ui.theme.OffWhite

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
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(OffWhite)
        .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MateOrRoomButton()

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = BtnBeige
            )

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

            PostText()

            MateButton(
                text = "작성 완료",
                onClick = {
                    Toast.makeText(context, "저장되었습니다!", Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
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