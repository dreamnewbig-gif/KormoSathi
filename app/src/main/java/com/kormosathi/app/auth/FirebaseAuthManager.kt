package com.kormosathi.app.auth

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private var verificationId: String = ""
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    fun currentUser() = auth.currentUser

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun signOut() {
        auth.signOut()
    }

    fun sendOtp(
        activity: Activity,
        phoneNumber: String,
        onCodeSent: () -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onError: (String) -> Unit
    ) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(
                    credential: PhoneAuthCredential
                ) {
                    onVerificationCompleted(credential)
                }

                override fun onVerificationFailed(
                    e: FirebaseException
                ) {
                    onError(e.message ?: "OTP পাঠানো যায়নি")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    this@FirebaseAuthManager.verificationId = verificationId
                    resendToken = token
                    onCodeSent()
                }

            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(
        otp: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        try {

            val credential =
                PhoneAuthProvider.getCredential(
                    verificationId,
                    otp
                )

            auth.signInWithCredential(credential)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        onSuccess()

                    } else {

                        onError(
                            it.exception?.message
                                ?: "OTP Verification Failed"
                        )
                    }

                }

        } catch (e: Exception) {

            onError(e.message ?: "Unknown Error")

        }

    }

    fun signInWithCredential(
        credential: PhoneAuthCredential,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener {

                if (it.isSuccessful) {

                    onSuccess()

                } else {

                    onError(
                        it.exception?.message
                            ?: "Login Failed"
                    )

                }

            }

    }

    fun resendOtp(
        activity: Activity,
        phoneNumber: String,
        onCodeSent: () -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onError: (String) -> Unit
    ) {

        val token = resendToken ?: return

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setForceResendingToken(token)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(
                    credential: PhoneAuthCredential
                ) {
                    onVerificationCompleted(credential)
                }

                override fun onVerificationFailed(
                    e: FirebaseException
                ) {
                    onError(e.message ?: "Resend Failed")
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    this@FirebaseAuthManager.verificationId = verificationId
                    resendToken = token
                    onCodeSent()
                }

            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

    }

}