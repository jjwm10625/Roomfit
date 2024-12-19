package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roomfit.PostViewModel
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.textfield2
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun GoogleMapScreen(
    navController: NavController,
    postViewModel: PostViewModel = viewModel(),
    initialLocationString: String? = null
) {
    val initialLatLng = if (!initialLocationString.isNullOrEmpty()) {
        val coords = initialLocationString
            .replace("위치:", "")
            .replace("(", "")
            .replace(")", "")
            .split(",")
            .map { it.trim() }
        if (coords.size == 2) {
            val lat = coords[0].toDoubleOrNull() ?: 37.5469
            val lng = coords[1].toDoubleOrNull() ?: 126.9646
            LatLng(lat, lng)
        } else {
            LatLng(37.5469, 126.9646)
        }
    } else {
        LatLng(37.5469, 126.9646)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLatLng, 15f)
    }

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }
    var locationText by remember { mutableStateOf("") }

    LaunchedEffect(initialLocationString) {
        if (initialLocationString != null) {
            markerPosition = initialLatLng
            locationText = "위치: (${initialLatLng.latitude}, ${initialLatLng.longitude})"
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { clickedLatLng ->
                if (initialLocationString == null) {
                    markerPosition = clickedLatLng
                    locationText = "위치: (${clickedLatLng.latitude}, ${clickedLatLng.longitude})"
                }
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

        // 위치 정보 상단 표시 로직
        if (locationText.isNotEmpty()) {
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
                        .wrapContentSize()
                        .fillMaxWidth()
                ) {
                    Text(
                        text = locationText,
                        style = textfield2,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // initialLocationString이 null일 때만 OK 버튼 표시(새 위치 선택 모드)
                if (initialLocationString == null) {
                    Button(
                        onClick = {
                            postViewModel.location = locationText
                            navController.previousBackStackEntry?.savedStateHandle?.set("location", locationText)
                            navController.popBackStack()
                        },
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
}