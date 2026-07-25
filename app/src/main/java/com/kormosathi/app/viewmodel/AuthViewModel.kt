package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import com.kormosathi.app.auth.FirebaseAuthManager

class AuthViewModel : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return FirebaseAuthManager.isLoggedIn()
    }

    fun logout() {
        FirebaseAuthManager.signOut()
    }
}