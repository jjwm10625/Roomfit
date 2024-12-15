// app/src/main/java/com/example/roomfit/util/PreferencesManager.kt
package com.example.roomfit.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_ADDITIONAL_INFO = "additional_info"
        private const val KEY_SCHOOL = "school"
        private const val KEY_BUDGET = "budget"
        private const val KEY_HOUSE_TYPE = "house_type"
        private const val KEY_NUMBER_OF_RESIDENTS = "number_of_residents"
        private const val KEY_DURATION_OF_STAY = "duration_of_stay"
        private const val KEY_GENDER = "gender"
        private const val KEY_LIFESTYLE = "lifestyle"
        private const val KEY_SMOKING = "smoking"
    }

    var username: String?
        get() = prefs.getString(KEY_USERNAME, null)
        set(value) = prefs.edit().putString(KEY_USERNAME, value).apply()

    var email: String?
        get() = prefs.getString(KEY_EMAIL, null)
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    var password: String?
        get() = prefs.getString(KEY_PASSWORD, null)
        set(value) = prefs.edit().putString(KEY_PASSWORD, value).apply()

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    var additionalInfo: String?
        get() = prefs.getString(KEY_ADDITIONAL_INFO, null)
        set(value) = prefs.edit().putString(KEY_ADDITIONAL_INFO, value).apply()

    var school: String?
        get() = prefs.getString(KEY_SCHOOL, null)
        set(value) = prefs.edit().putString(KEY_SCHOOL, value).apply()

    var budget: String?
        get() = prefs.getString(KEY_BUDGET, null)
        set(value) = prefs.edit().putString(KEY_BUDGET, value).apply()

    var houseType: String?
        get() = prefs.getString(KEY_HOUSE_TYPE, null)
        set(value) = prefs.edit().putString(KEY_HOUSE_TYPE, value).apply()

    var numberOfResidents: String?
        get() = prefs.getString(KEY_NUMBER_OF_RESIDENTS, null)
        set(value) = prefs.edit().putString(KEY_NUMBER_OF_RESIDENTS, value).apply()

    var durationOfStay: String?
        get() = prefs.getString(KEY_DURATION_OF_STAY, null)
        set(value) = prefs.edit().putString(KEY_DURATION_OF_STAY, value).apply()

    var gender: String?
        get() = prefs.getString(KEY_GENDER, null)
        set(value) = prefs.edit().putString(KEY_GENDER, value).apply()

    var lifestyle: String?
        get() = prefs.getString(KEY_LIFESTYLE, null)
        set(value) = prefs.edit().putString(KEY_LIFESTYLE, value).apply()

    var smoking: String?
        get() = prefs.getString(KEY_SMOKING, null)
        set(value) = prefs.edit().putString(KEY_SMOKING, value).apply()
}