package com.gdg.kakaobank.presentation.navigator

import androidx.annotation.DrawableRes
import com.example.roomfit.R

sealed class RoomNav(
    val route: String,
    @DrawableRes val icon: Int,
) {
    data object Home: RoomNav(
        route = "home",
        icon = R.drawable.home,
    )

    data object Write: RoomNav(
        route = "write",
        icon = R.drawable.write,
    )
    data object Message: RoomNav(
        route = "message",
        icon = R.drawable.message,
    )
    data object User: RoomNav(
        route = "user",
        icon = R.drawable.user,
    )
}