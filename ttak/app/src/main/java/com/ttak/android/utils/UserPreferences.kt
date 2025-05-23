package com.ttak.android.utils

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // 앱을 처음 실행했는지 유무를 확인하는 flag
    var isFirstLaunch: Boolean
        get() = preferences.getBoolean(IS_FIRST_LAUNCH_KEY, true)
        set(value) = preferences.edit().putBoolean(IS_FIRST_LAUNCH_KEY, value).apply()

    companion object {
        private const val PREFS_NAME = "ttak_prefs"
        private const val IS_FIRST_LAUNCH_KEY = "is_first_launch"
    }

    // 백에서 받아온 유저 아이디 저장
    fun saveUserId(userId: String) {
        preferences.edit().putString("user_id", userId).apply()
    }

    // 백에서 받아온 유저 아이디 가져오기
    fun getUserId(): String? {
        return preferences.getString("user_id", null)
    }

    // 백에서 받아온 유저 아이디 삭제
    fun clearUserId() {
        preferences.edit().remove("user_id").apply()
    }

    // 사용자 닉네임 설정
    fun saveNickname(nickname: String) {
        preferences.edit().putString("nickname", nickname).apply()
    }

    // 사용자 닉네임 가져오기
    fun getNickname(): String? {
        return preferences.getString("nickname", null)
    }

    // 저장한 사용자 닉네임 삭제
    fun clearNickname() {
        preferences.edit().remove("nickname").apply()
    }
}