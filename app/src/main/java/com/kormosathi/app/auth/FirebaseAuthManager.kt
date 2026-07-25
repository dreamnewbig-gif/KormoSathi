package com.kormosathi.app.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {

    private val auth = FirebaseAuth.getInstance()

    fun getAuth(): FirebaseAuth {
        return auth
    }

    fun getCurrentUser() = auth.currentUser

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }
}