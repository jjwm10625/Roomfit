package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.textfield2
import com.gdg.kakaobank.presentation.navigator.RoomNav
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun GoogleMapScreen(navController: NavController) {
    // 초기 카메라 위치 (학교)
    val initialLocation = LatLng(37.5469, 126.9646)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, 15f)
    }

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var clickedLocationInfo by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { clickedLatLng ->
                markerPosition = clickedLatLng
                clickedLocationInfo =
                    "위치: (${clickedLatLng.latitude}, ${clickedLatLng.longitude})"
            }
        ) {
            markerPosition?.let {
                Marker(
                    state = com.google.maps.android.compose.MarkerState(position = it),
                    title = "선택된 위치",
                    snippet = "(${it.latitude}, ${it.longitude})"
                )
            }
        }

        if (clickedLocationInfo.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(OffWhite, shape = RoundedCornerShape(24.dp))
                        .padding(8.dp)
                        .height(36.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = clickedLocationInfo,
                        style = textfield2,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate(RoomNav.Write.route) },
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BtnBlack)
                ) {
                    Text(
                        text = "OK",
                        style = textfield2
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGoogleMapScreen() {
    GoogleMapScreen(navController = rememberNavController())
}