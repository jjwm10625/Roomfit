package com.example.roomfit.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun GoogleMapScreen() {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = clickedLocationInfo,
                    modifier = Modifier
                        .background(androidx.compose.ui.graphics.Color.White)
                        .padding(8.dp)
                )
            }
        }
    }
}
