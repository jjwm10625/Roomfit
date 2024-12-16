package com.example.roomfit.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState

@Composable
fun GoogleMapScreen() {
    // 서울의 기본 위치
    val seoul = LatLng(37.5665, 126.9780)

    // 카메라 상태
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoul, 10f)
    }

    // GoogleMap 컴포넌트
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // 마커 추가
        Marker(
            state = MarkerState(position = seoul),
            title = "서울",
            snippet = "여기는 서울입니다."
        )
    }
}

@Preview
@Composable
fun GoogleMapScreenPreview() {
    GoogleMapScreen()
}