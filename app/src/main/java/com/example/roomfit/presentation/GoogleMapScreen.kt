package com.example.roomfit.presentation

import android.content.Context
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
import com.example.roomfit.presentation.viewmodel.PostViewModel
import com.example.roomfit.ui.theme.BtnBlack
import com.example.roomfit.ui.theme.OffWhite
import com.example.roomfit.ui.theme.textfield2
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import android.location.Geocoder
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

@Composable
fun GoogleMapScreen(
    navController: NavController,
    postViewModel: PostViewModel = viewModel(),
    initialLocationString: String? = null
) {
    val context = LocalContext.current
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
    var addressText by remember { mutableStateOf("") }
    var updatedClickedLatLng by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(initialLocationString) {
        if (initialLocationString != null) {
            markerPosition = initialLatLng
            locationText = "위치: (${initialLatLng.latitude}, ${initialLatLng.longitude})"
            addressText = getAddressFromLatLng(context, initialLatLng.latitude, initialLatLng.longitude)
            postViewModel.location = addressText // 초기 주소 저장
        }
    }

    LaunchedEffect(updatedClickedLatLng) {
        updatedClickedLatLng?.let {
            addressText = getAddressFromLatLng(context, it.latitude, it.longitude)
            postViewModel.location = addressText // 주소 업데이트
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
                    updatedClickedLatLng = clickedLatLng
                }
            }
        ) {
            markerPosition?.let {
                Marker(
                    state = com.google.maps.android.compose.MarkerState(position = it),
                    title = "선택된 위치",
                    snippet = addressText.ifEmpty { "주소를 가져오는 중..." }
                )
            }
        }

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
                        text = "$locationText\n$addressText",
                        style = textfield2,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

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

suspend fun getAddressFromLatLng(context: Context, latitude: Double, longitude: Double): String {
    return withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses?.isNotEmpty() == true) {
                addresses[0]?.getAddressLine(0) ?: "주소를 가져올 수 없습니다"
            } else {
                "주소를 가져올 수 없습니다"
            }
        } catch (e: Exception) {
            "주소를 가져오는 중 오류 발생"
        }
    }
}