package com.kormosathi.app.auth

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

class AuthRepository {
    fun currentUser() = FirebaseAuthManager.currentUser()

    fun isLoggedIn(): Boolean = FirebaseAuthManager.isLoggedIn()

    fun signOut() = FirebaseAuthManager.signOut()

    fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        onCodeSent: () -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseAuthManager.sendOtp(
            activity = activity,
            phoneNumber = phoneNumber,
            onCodeSent = onCodeSent,
            onVerificationCompleted = onVerificationCompleted,
            onError = onError
        )
    }

    fun verifyOtp(otp: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        FirebaseAuthManager.verifyOtp(otp, onSuccess, onError)
    }

    fun signInWithCredential(credential: PhoneAuthCredential, onSuccess: () -> Unit, onError: (String) -> Unit) {
        FirebaseAuthManager.signInWithCredential(credential, onSuccess, onError)
    }
}